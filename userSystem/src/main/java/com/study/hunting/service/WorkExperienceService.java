package com.study.hunting.service;

import com.study.hunting.domain.WorkExperience;
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
public interface WorkExperienceService extends IService<WorkExperience> {

    List<WorkExperience> queryByUserId(Integer userId);

    ResultVO<Integer> create(Integer userId, WorkExperience workExperience);

    ResultVO edit(Integer userId, WorkExperience workExperience, Integer id);

    ResultVO deleteWork(Integer userId, Integer id);
}
