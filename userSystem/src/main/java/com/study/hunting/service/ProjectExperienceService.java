package com.study.hunting.service;

import com.study.hunting.domain.ProjectExperience;
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
public interface ProjectExperienceService extends IService<ProjectExperience> {

    List<ProjectExperience> queryByUserId(Integer userId);

    ResultVO<Integer> create(Integer userId, ProjectExperience projectExperience);

    ResultVO edit(Integer userId, ProjectExperience projectExperience, Integer id);

    ResultVO deleteProject(Integer userId, Integer id);
}
