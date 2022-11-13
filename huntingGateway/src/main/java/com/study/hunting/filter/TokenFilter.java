package com.study.hunting.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.util.TokenUtils;
import com.study.hunting.vo.ResultVO;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Order(-1)
@Component
public class TokenFilter implements GlobalFilter {
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 判断是否需要登录验证
        String path = request.getURI().getPath();
        // 可不登陆访问的服务直接放行
        if (antPathMatcher.match("/**/global/**", path)) return chain.filter(exchange);
        // 登陆验证
        String token = request.getHeaders().getFirst("Authorization");
        if (token == null) {
            // 没有token
            return getMono(response, ResponseCode.TOKEN_MISSION);
        }
        Claims claims;
        try {
            claims = TokenUtils.parseToken(token);
            Map<String, String> map = new HashMap<>();
            map.put("userId", (String) claims.get("id"));
            if (request.getMethod() == HttpMethod.GET || request.getMethod() == HttpMethod.DELETE) {
                return addParameterForGetMethod(exchange, chain, map);
            } else if (request.getMethod() == HttpMethod.PUT){
                return addParameterForPostMethod(exchange, chain, map);
            } else {
                MediaType contentType = request.getHeaders().getContentType();
                if (MediaType.MULTIPART_FORM_DATA.equals(contentType)) {
                    ServerHttpRequest build = request.mutate().header("userId", (String) claims.get("id")).build();
                    return chain.filter(exchange.mutate().request(build).build());
                } else {
                    return addParameterForPostMethod(exchange, chain, map);
                }
            }
        } catch (Exception e) {
            return getMono(response, ResponseCode.TOKEN_INVALID);
        }
    }

    /**
     * write json data to response
     * @param response
     * @param responseCode
     * @return
     */
    private Mono<Void> getMono(ServerHttpResponse response, ResponseCode responseCode) {
        ResultVO result = new ResultVO();
        result.setResponseCode(responseCode);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes());
        return response.writeWith(Flux.just(dataBuffer));
    }

    /**
     * get请求，添加参数
     * {@link org.springframework.cloud.gateway.filter.factory.AddRequestParameterGatewayFilterFactory}
     *
     * @param exchange
     * @param chain
     * @param params
     * @return
     */
    public Mono<Void> addParameterForGetMethod(ServerWebExchange exchange, GatewayFilterChain chain, Map<String, String> params) {
        URI uri = exchange.getRequest().getURI();
        StringBuilder query = new StringBuilder();
        String originalQuery = uri.getQuery();
        if (StringUtils.hasText(originalQuery)) {
            query.append(originalQuery);
            if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                query.append('&');
            }
        }
        for (String key : params.keySet()) {
            query.append(key).append("=").append(params.get(key)).append("&");
        }
        query.deleteCharAt(query.length() - 1);
        try {
            URI newUri = UriComponentsBuilder.fromUri(uri).replaceQuery(query.toString()).build(true).toUri();
            ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(request).build());
        } catch (Exception e) {
            return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().build()).build());
        }
    }

    /**
     * post请求，添加参数
     * {@link org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory}
     *
     * @param exchange
     * @param chain
     * @param params
     * @return
     */
    public Mono<Void> addParameterForPostMethod(ServerWebExchange exchange, GatewayFilterChain chain, Map<String, String> params) {
        ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
        Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                .flatMap(o -> {
                    if (o.startsWith("[")) {
                        // body内容为数组，直接返回
                        return Mono.just(o);
                    }
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        Map map = objectMapper.readValue(o, Map.class);
                        map.putAll(params);
                        return Mono.just(objectMapper.writeValueAsString(map));
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Mono.just(o);
                    }
                });
        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                .then(Mono.defer(() -> {
                    ServerHttpRequest decorator = decorate(exchange, headers, outputMessage);
                    return chain.filter(exchange.mutate().request(decorator).build());
                })).onErrorResume((Function<Throwable, Mono<Void>>) throwable -> Mono.error(throwable));
    }

    protected ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers,
                                                  CachedBodyOutputMessage outputMessage) {
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                long contentLength = headers.getContentLength();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.putAll(headers);
                if (contentLength > 0) {
                    httpHeaders.setContentLength(contentLength);
                } else {
                    // TODO: this causes a 'HTTP/1.1 411 Length Required' // on
                    // httpbin.org
                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                }
                return httpHeaders;
            }

            @Override
            public Flux<DataBuffer> getBody() {
                return outputMessage.getBody();
            }
        };
    }
}
