package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.hunting.client.FileManagerClient;
import com.study.hunting.domain.ProjectExperience;
import com.study.hunting.domain.ResumeUserRelation;
import com.study.hunting.dao.ResumeUserRelationMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.ResumeUserRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.util.DateTimeUtils;
import com.study.hunting.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class ResumeUserRelationServiceImpl extends ServiceImpl<ResumeUserRelationMapper, ResumeUserRelation> implements ResumeUserRelationService {

    @Autowired
    private FileManagerClient fileManagerClient;

    @Override
    public List<ResumeUserRelation> queryByUserId(Integer userId) {
        return baseMapper.selectList(new QueryWrapper<ResumeUserRelation>().eq("user_id", userId).orderByDesc("create_time"));
    }

    @Override
    public ResultVO<ResumeUserRelation> create(Integer userId, MultipartFile multipartFile) {
        ResultVO<ResumeUserRelation> result = new ResultVO<>();
        if (baseMapper.selectCount(new QueryWrapper<ResumeUserRelation>().eq("user_id", userId)) < 3) {
            // 简历附件数小于3个，可以继续添加
            // 先存储文件
            ResultVO<String> res = fileManagerClient.saveResume(multipartFile);
            // 存储成功则在数据库保存数据
            if (res.getCode() == ResponseCode.SUCCESS.getCode()) {
                ResumeUserRelation resumeUserRelation = new ResumeUserRelation();
                resumeUserRelation.setUserId(userId);
                resumeUserRelation.setResumeName(multipartFile.getOriginalFilename());
                resumeUserRelation.setResumePath(res.getData());
                resumeUserRelation.setCreateTime(DateTimeUtils.nowTimeFormat());
                if (baseMapper.insert(resumeUserRelation) == 1) {
                    result.setData(resumeUserRelation);
                } else {
                    result.setResponseCode(ResponseCode.FAIL);
                }
            } else {
                result.setResponseCode(ResponseCode.FAIL);
                result.setMsg(res.getMsg());
            }

        } else {
            result.setResponseCode(ResponseCode.INVALID_OPERATION);
            result.setMsg("简历附件数不能多于3个");
        }
        return result;
    }

    @Override
    public ResultVO deleteResume(Integer userId, Integer id) {
        ResultVO result = new ResultVO();
        ResumeUserRelation resumeUserRelation = baseMapper.selectById(id);
        if (userId.equals(resumeUserRelation.getUserId())) {
            String var = resumeUserRelation.getResumePath();
            String[] split = var.split("/");
            if (split.length == 2) {
                if (fileManagerClient.deleteResume(split[0], split[1]).getCode() == ResponseCode.SUCCESS.getCode()) {
                    baseMapper.deleteById(id);
                    result.setResponseCode(ResponseCode.SUCCESS);
                } else {
                    result.setResponseCode(ResponseCode.FAIL);
                }
            } else {
                result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            }
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public List<ResumeUserRelation> queryById(Integer resumeId) {
        return baseMapper.selectList(new QueryWrapper<ResumeUserRelation>().eq("id", resumeId));
    }
}
