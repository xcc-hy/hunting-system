package com.study.hunting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.hunting.domain.JobUserRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.vo.ResultVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
public interface JobUserRelationService extends IService<JobUserRelation> {

    ResultVO sendResume(Integer jobId, Integer userId, Integer resumeId);

    ResultVO<List<JobUserRelation>> queryMine(Integer userId);

    ResultVO<Page<JobUserRelation>> queryPage(Integer userId, JobUserRelation condition, Integer pageSize, Integer pageNum) throws Exception;

    ResultVO passApplication(Integer userId, Integer id);

    ResultVO refuseApplication(Integer userId, Integer id);

    ResultVO nextProgress(Integer userId, Integer id);

    ResultVO progressFail(Integer userId, Integer id);
}
