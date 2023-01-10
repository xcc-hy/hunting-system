package com.study.hunting.service;

import com.study.hunting.domain.Job;
import com.study.hunting.domain.UserStar;
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
public interface UserStarService extends IService<UserStar> {
    List<Integer> queryMyStar(Integer userId);

    ResultVO addStar(Integer userId, Integer jobId);

    ResultVO removeStar(Integer userId, Integer jobId);

    ;
}
