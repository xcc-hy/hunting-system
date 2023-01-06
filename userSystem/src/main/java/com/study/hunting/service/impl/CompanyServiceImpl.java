package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.dao.CompanyMapper;
import com.study.hunting.domain.Company;
import com.study.hunting.domain.User;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.CompanyService;
import com.study.hunting.vo.ResultVO;
import io.seata.core.context.RootContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Override
    public ResultVO<Company> selectByFullName(String fullName) {
        ResultVO<Company> result = new ResultVO<>();
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("full_name", fullName);
        Company company = baseMapper.selectOne(queryWrapper);
        result.setData(company);
        return result;
    }

    @Override
    public ResultVO<List<String>> selectByFuzzy(String fullName) {
        ResultVO<List<String>> result = new ResultVO<>();
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("full_name").like("full_name", "%" + fullName + "%");
        result.setData(baseMapper.selectObjs(queryWrapper)
                .stream()
                .map(o -> (String) o)
                .collect(Collectors.toList()));
        return result;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Integer create(Company company) {
        if (selectIdByFullName(company.getFullName()) != null) return null;
        baseMapper.insert(company);
        return company.getId();
    }

    @Override
    public Integer selectIdByFullName(String fullName) {
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<Company>().select("id").eq("full_name", fullName));
        if (objects.size() == 0) return null;
        return (Integer) objects.get(0);
    }

    @Override
    public ResultVO<List<Integer>> selectIdByOwnerId(Integer userId) {
        ResultVO<List<Integer>> result = new ResultVO<>();
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").eq("owner_id", userId);
        List<Object> objects = baseMapper.selectObjs(queryWrapper);
        if (objects.size() == 0) return result;
        result.setData(objects.stream().map(o -> (Integer) o).collect(Collectors.toList()));
        result.setResponseCode(ResponseCode.SUCCESS);
        return result;
    }

    @Override
    public ResultVO<String> getNameById(Integer id) {
        ResultVO<String> result = new ResultVO<>();
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<Company>().eq("id", id).select("full_name"));
        if (objects == null || objects.size() == 0) {
            result.setResponseCode(ResponseCode.FAIL);
        } else {
            result.setData((String) objects.get(0));
        }
        return result;
    }
}
