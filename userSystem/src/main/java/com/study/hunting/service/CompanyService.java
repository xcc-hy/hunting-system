package com.study.hunting.service;

import com.study.hunting.domain.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.vo.ResultVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
public interface CompanyService extends IService<Company> {

    ResultVO<Company> selectByFullName(String fullName);

    ResultVO<List<String>> selectByFuzzy(String fullName);

    Integer create(Company company);

    Integer selectIdByFullName(String fullName);

    ResultVO<List<Integer>> selectIdByOwnerId(Integer userId);

    ResultVO<String> getNameById(Integer id);
}
