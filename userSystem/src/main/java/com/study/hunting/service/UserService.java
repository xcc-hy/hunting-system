package com.study.hunting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.hunting.domain.User;
import com.study.hunting.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

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

    ResultVO<User> updateBaseInfo(User user, Integer userId) throws Exception;

    ResultVO<User> updatePassword(User user, Integer userId, String newPassword) throws Exception;

    ResultVO freeze(Integer userId, Integer targetId);

    ResultVO unfreeze(Integer userId, Integer targetId);

    Boolean isManager(Integer userId);

    Boolean upgradeEnterprise(Integer userId, Integer id, Boolean isManager, Integer companyId);

    ResultVO<Boolean> isEnterprise(Integer userId);

    ResultVO<Integer> getCompanyIdById(Integer userId);

    ResultVO<String> getNameById(Integer userId);

    ResultVO<String> updateIco(Integer userId, MultipartFile multipartFile);

}
