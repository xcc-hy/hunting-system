package com.study.hunting.service;

import com.study.hunting.domain.EducationExperience;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.vo.ResultVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2023-01-07
 */
public interface EducationExperienceService extends IService<EducationExperience> {

    List<EducationExperience> queryByUserId(Integer userId);

    ResultVO<Integer> create(Integer userId, EducationExperience educationExperience);

    ResultVO edit(Integer userId, EducationExperience educationExperience, Integer id);

    ResultVO deleteEducation(Integer userId, Integer id);
}
