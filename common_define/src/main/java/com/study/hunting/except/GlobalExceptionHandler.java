package com.study.hunting.except;

import com.study.hunting.enums.ResponseCode;
import com.study.hunting.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public ResultVO<Exception> globalException(Exception e) {
        ResultVO<Exception> result = new ResultVO<>();
        result.setResponseCode(ResponseCode.UNKNOWN_ERROR);
        result.setData(e);
        return result;
    }
}
