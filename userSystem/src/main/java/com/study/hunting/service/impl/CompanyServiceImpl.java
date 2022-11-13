package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.dao.CompanyMapper;
import com.study.hunting.domain.Company;
import com.study.hunting.service.CompanyService;
import org.springframework.stereotype.Service;

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

}
