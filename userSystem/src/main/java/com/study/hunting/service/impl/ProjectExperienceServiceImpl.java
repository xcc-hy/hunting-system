package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.hunting.domain.EducationExperience;
import com.study.hunting.domain.ProjectExperience;
import com.study.hunting.dao.ProjectExperienceMapper;
import com.study.hunting.domain.WorkExperience;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.ProjectExperienceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcc
 * @since 2023-01-07
 */
@Service
public class ProjectExperienceServiceImpl extends ServiceImpl<ProjectExperienceMapper, ProjectExperience> implements ProjectExperienceService {

    @Override
    public List<ProjectExperience> queryByUserId(Integer userId) {
        return baseMapper.selectList(new QueryWrapper<ProjectExperience>().eq("user_id", userId).orderByDesc("start_date"));
    }

    @Override
    public ResultVO<Integer> create(Integer userId, ProjectExperience projectExperience) {
        ResultVO<Integer> result = new ResultVO<>();
        projectExperience.setId(null);
        projectExperience.setUserId(userId);
        int res = baseMapper.insert(projectExperience);
        if (res == 1) {
            result.setData(projectExperience.getId());
        } else {
            result.setResponseCode(ResponseCode.FAIL);
        }
        return result;
    }

    @Override
    public ResultVO edit(Integer userId, ProjectExperience projectExperience, Integer id) {
        ResultVO result = new ResultVO();
        ProjectExperience oldData = baseMapper.selectById(id);
        if (oldData == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else if (!userId.equals(oldData.getUserId())) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        } else {
            oldData.setProjectName(projectExperience.getProjectName());
            oldData.setRole(projectExperience.getRole());
            oldData.setStartDate(projectExperience.getStartDate());
            oldData.setEndDate(projectExperience.getEndDate());
            oldData.setAchievement(projectExperience.getAchievement());
            oldData.setIntroduction(projectExperience.getIntroduction());
            if (baseMapper.updateById(oldData) == 1) {
                result.setResponseCode(ResponseCode.SUCCESS);
            } else {
                result.setResponseCode(ResponseCode.FAIL);
            }
        }
        return result;
    }

    @Override
    public ResultVO deleteProject(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        if (baseMapper.delete(new QueryWrapper<ProjectExperience>().eq("id", id).eq("user_id", userId)) == 1) {
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }
}
