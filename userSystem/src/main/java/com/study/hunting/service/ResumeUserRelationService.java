package com.study.hunting.service;

import com.study.hunting.domain.ResumeUserRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2023-01-07
 */
public interface ResumeUserRelationService extends IService<ResumeUserRelation> {

    List<ResumeUserRelation> queryByUserId(Integer userId);

    ResultVO<ResumeUserRelation> create(Integer userId, MultipartFile multipartFile);

    ResultVO deleteResume(Integer userId, Integer id);

    List<ResumeUserRelation> queryById(Integer resumeId);
}
