package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.hunting.domain.EducationExperience;
import com.study.hunting.domain.ProjectExperience;
import com.study.hunting.domain.WorkExperience;
import com.study.hunting.dao.WorkExperienceMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.WorkExperienceService;
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
public class WorkExperienceServiceImpl extends ServiceImpl<WorkExperienceMapper, WorkExperience> implements WorkExperienceService {

    @Override
    public List<WorkExperience> queryByUserId(Integer userId) {
        return baseMapper.selectList(new QueryWrapper<WorkExperience>().eq("user_id", userId).orderByDesc("start_date"));
    }

    @Override
    public ResultVO<Integer> create(Integer userId, WorkExperience workExperience) {
        ResultVO<Integer> result = new ResultVO<>();
        workExperience.setId(null);
        workExperience.setUserId(userId);
        int res = baseMapper.insert(workExperience);
        if (res == 1) {
            result.setData(workExperience.getId());
        } else {
            result.setResponseCode(ResponseCode.FAIL);
        }
        return result;
    }

    @Override
    public ResultVO edit(Integer userId, WorkExperience workExperience, Integer id) {
        ResultVO result = new ResultVO();
        WorkExperience oldData = baseMapper.selectById(id);
        if (oldData == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else if (!userId.equals(oldData.getUserId())) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        } else {
            oldData.setCompanyName(workExperience.getCompanyName());
            oldData.setIndustryId(workExperience.getIndustryId());
            oldData.setStartDate(workExperience.getStartDate());
            oldData.setEndDate(workExperience.getEndDate());
            oldData.setAchievement(workExperience.getAchievement());
            oldData.setIsPractice(workExperience.getIsPractice());
            oldData.setWorkContent(workExperience.getWorkContent());
            oldData.setAchievement(workExperience.getAchievement());
            if (baseMapper.updateById(oldData) == 1) {
                result.setResponseCode(ResponseCode.SUCCESS);
            } else {
                result.setResponseCode(ResponseCode.FAIL);
            }
        }
        return result;
    }

    @Override
    public ResultVO deleteWork(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        if (baseMapper.delete(new QueryWrapper<WorkExperience>().eq("id", id).eq("user_id", userId)) == 1) {
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }
}
