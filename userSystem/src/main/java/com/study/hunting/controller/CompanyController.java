package com.study.hunting.controller;


import com.study.hunting.domain.Company;
import com.study.hunting.service.CompanyService;
import com.study.hunting.service.UserService;
import com.study.hunting.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@RestController
@RequestMapping("/company")
@Api("公司管理系统")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @GetMapping("/selectByFullName/{fullName}")
    @ApiOperation("通过公司全称查找公司")
    public ResultVO<Company> selectByFullName(@PathVariable String fullName) {
        return companyService.selectByFullName(fullName);
    }

    @GetMapping("/selectById/{id}")
    @ApiOperation("通过公司id查找公司")
    public ResultVO<Company> selectById(@PathVariable Integer id) {
        Company company = companyService.getById(id);
        ResultVO<Company> result = new ResultVO<>();
        result.setData(company);
        return result;
    }

    @GetMapping("/fuzzy/{fullName}")
    @ApiOperation("通过公司全称模糊查找公司")
    public ResultVO<List<String>> selectByFuzzy(@PathVariable String fullName) {
        return companyService.selectByFuzzy(fullName);
    }

    @GetMapping("/selectIdByFullName/{fullName}")
    public Integer selectIdByFullName(@PathVariable String fullName) {
        return companyService.selectIdByFullName(fullName);
    }

    @PostMapping("/secret/create/{userId}")
    @ApiOperation("新建公司")
    public Integer create(@PathVariable Integer userId, @RequestBody Company company) {
        if (!userService.isManager(userId)) return null;
        return companyService.create(company);
    }

    @GetMapping("/selectIdByOwnerId/{userId}")
    @ApiOperation("通过用户id查找其名下的公司")
    public ResultVO<List<Integer>> selectIdByOwnerId(@PathVariable Integer userId) {
        return companyService.selectIdByOwnerId(userId);
    }

    @GetMapping("/getNameById/{id}")
    @ApiOperation("通过公司id查找公司")
    public ResultVO<String> getNameById(@PathVariable Integer id) {
        return companyService.getNameById(id);
    }

}
