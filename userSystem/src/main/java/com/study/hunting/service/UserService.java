package com.study.hunting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.domain.User;
import com.study.hunting.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
public interface UserService extends IService<User> {

    ResultVO<User> login(String email, String password) throws Exception;

    ResultVO<User> register(User user) throws Exception;

    ResultVO<User> updateBaseInfo(User user, String userId) throws Exception;

    ResultVO<User> updatePassword(User user, String userId, String newPassword) throws Exception;

    ResultVO freeze(String userId, String targetId);

    ResultVO unfreeze(String userId, String targetId);
}
