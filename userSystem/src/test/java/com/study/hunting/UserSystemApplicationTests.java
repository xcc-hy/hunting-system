package com.study.hunting;

import com.study.hunting.dao.UserMapper;
import com.study.hunting.domain.User;
import com.study.hunting.service.UserService;
import com.study.hunting.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@SpringBootTest
class UserSystemApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() throws IllegalBlockSizeException, BadPaddingException {
        User user = userMapper.selectById(1);
        System.out.println(user.getEducation());
        System.out.println(user.getId());
    }

    @Test
    void test01() throws Exception {
        User user = userService.getById(2);
        System.out.println(user.getId());
        user.setSkill("");
        ResultVO<User> userResultVO = userService.updateBaseInfo(user, 2);
        System.out.println(userResultVO.getMsg());
    }

}
