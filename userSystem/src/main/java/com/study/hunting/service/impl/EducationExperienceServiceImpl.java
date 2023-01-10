package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.hunting.domain.EducationExperience;
import com.study.hunting.dao.EducationExperienceMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.EducationExperienceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.vo.ResultVO;
import io.seata.core.model.Result;
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
public class EducationExperienceServiceImpl extends ServiceImpl<EducationExperienceMapper, EducationExperience> implements EducationExperienceService {

    @Override
    public List<EducationExperience> queryByUserId(Integer userId) {
        return baseMapper.selectList(new QueryWrapper<EducationExperience>().eq("user_id", userId).orderByDesc("start_year"));
    }

    @Override
    public ResultVO<Integer> create(Integer userId, EducationExperience educationExperience) {
        ResultVO<Integer> result = new ResultVO<>();
        educationExperience.setId(null);
        educationExperience.setUserId(userId);
        int res = baseMapper.insert(educationExperience);
        if (res == 1) {
            result.setData(educationExperience.getId());
        } else {
            result.setResponseCode(ResponseCode.FAIL);
        }
        return result;
    }

    @Override
    public ResultVO edit(Integer userId, EducationExperience educationExperience, Integer id) {
        ResultVO result = new ResultVO();
        EducationExperience oldData = baseMapper.selectById(id);
        if (oldData == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        } else if (!userId.equals(oldData.getUserId())) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        } else {
            oldData.setSchoolName(educationExperience.getSchoolName());
            oldData.setEducation(educationExperience.getEducation());
            oldData.setStartYear(educationExperience.getStartYear());
            oldData.setEndYear(educationExperience.getEndYear());
            oldData.setMajor(educationExperience.getMajor());
            oldData.setMajorRank(educationExperience.getMajorRank());
            oldData.setIntroduction(educationExperience.getIntroduction());
            if (baseMapper.updateById(oldData) == 1) {
                result.setResponseCode(ResponseCode.SUCCESS);
            } else {
                result.setResponseCode(ResponseCode.FAIL);
            }
        }
        return result;
    }

    @Override
    public ResultVO deleteEducation(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        if (baseMapper.delete(new QueryWrapper<EducationExperience>().eq("id", id).eq("user_id", userId)) == 1) {
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }
}
