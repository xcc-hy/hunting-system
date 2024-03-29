package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.client.FileManagerClient;
import com.study.hunting.dao.UserMapper;
import com.study.hunting.domain.User;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.UserService;
import com.study.hunting.util.DateTimeUtils;
import com.study.hunting.util.MD5Utils;
import com.study.hunting.util.TokenUtils;
import com.study.hunting.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private FileManagerClient fileManagerClient;

    public User selectByEmail(String email) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = getOne(queryWrapper);
        return user;
    }

    public ResultVO<User> login(String email, String password) throws Exception {
        ResultVO<User> result = new ResultVO<>();
        User user = selectByEmail(email);
        password = MD5Utils.encrypt(email, password);
        if (user == null) {
            result.setResponseCode(ResponseCode.EMAIL_ERROR);
        } else if (!password.equals(user.getPassword())) {
            result.setResponseCode(ResponseCode.PASSWORD_ERROR);
        } else if ("0".equals(user.getStatus())) {
            result.setResponseCode(ResponseCode.ACCOUNT_FREEZE);
        } else {
            result.setResponseCode(ResponseCode.SUCCESS);
            user.setPassword(null); // 删除密码信息
            result.setData(user);
            result.setToken(TokenUtils.getToken(user.getId()));
        }
        return result;
    }

    public ResultVO<User> register(User user) throws Exception {
        ResultVO<User> result = new ResultVO<>();
        User tmp = selectByEmail(user.getEmail());
        // 已有注册信息
        if (tmp != null) {
            result.setResponseCode(ResponseCode.REGISTRATION_ERROR);
        } else {
            user.setPassword(MD5Utils.encrypt(user.getEmail(), user.getPassword())); // 加密密码
            // 其他基本信息的初始化
            user.setId(null);
            user.setIsManager(null);
            user.setRoleName("求职者");
            user.setStatus("1");
            user.setImgPath(null);
            user.setCompanyId(null);
            user.setEducation(null);
            user.setHometown(null);
            user.setIntroduce(null);
            user.setSkill(null);
            user.setInterest(null);
            user.setCreateTime(DateTimeUtils.nowDateFormat());
            int res = baseMapper.insert(user);
            result.setResponseCode(res == 1 ? ResponseCode.SUCCESS : ResponseCode.FAIL);
        }
        return result;
    }

    @Override
    public ResultVO<User> updateBaseInfo(User user, Integer userId) {
        ResultVO<User> result = new ResultVO<>();
        if (userId == null || !userId.equals(user.getId())) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
            return result;
        }
        // 除了可以设置的基本信息以外的所有信息不予设置
        User newUser = new User();
        newUser.setId(userId);
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEducation(user.getEducation());
        newUser.setHometown(user.getHometown());
        newUser.setInterest(user.getInterest());
        newUser.setIntroduce(user.getIntroduce());
        newUser.setSkill(user.getSkill());
        int res = baseMapper.updateById(newUser);
        if (res == 0) {
            result.setResponseCode(ResponseCode.FAIL);
        } else {
            result.setResponseCode(ResponseCode.SUCCESS);
            user = baseMapper.selectById(user.getId());
            user.setPassword(null); // 删除密码
            result.setData(user);
        }
        return result;
    }

    @Override
    public ResultVO<User> updatePassword(User user, Integer userId, String newPassword) {
        ResultVO<User> result = new ResultVO<>();
        // 参数不合法
        if (newPassword == null || user.getPassword() == null) {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
            return result;
        }
        // 无权限
        if (userId == null || !userId.equals(user.getId())) {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
            return result;
        }
        User curUser = baseMapper.selectById(userId);
        // 用户找不到
        if (curUser == null) {
            result.setResponseCode(ResponseCode.EMAIL_ERROR);
            return result;
        }
        // 查看密码是否正确
        if (curUser.getPassword().equals(MD5Utils.encrypt(user.getEmail(), user.getPassword()))) {
            newPassword = MD5Utils.encrypt(user.getEmail(), newPassword);
            curUser.setPassword(newPassword);
            int res = baseMapper.updateById(curUser);
            result.setResponseCode(res == 1 ? ResponseCode.SUCCESS : ResponseCode.FAIL);
        } else {
            result.setResponseCode(ResponseCode.PASSWORD_ERROR);
        }
        return result;
    }

    @Override
    public ResultVO freeze(Integer userId, Integer targetId) {
        ResultVO result = new ResultVO();
        User user = baseMapper.selectById(userId);
        if (user != null && "管理员".equals(user.getRoleName())) {
            user = baseMapper.selectById(targetId);
            if (user == null) {
                result.setResponseCode(ResponseCode.EMAIL_ERROR);
            } else {
                user.setStatus("0");
                int res = baseMapper.updateById(user);
                result.setResponseCode(res == 1 ? ResponseCode.SUCCESS : ResponseCode.FAIL);
            }
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public ResultVO unfreeze(Integer userId, Integer targetId) {
        ResultVO result = new ResultVO();
        User user = baseMapper.selectById(userId);
        if (user != null && "管理员".equals(user.getRoleName())) {
            user = baseMapper.selectById(targetId);
            if (user == null) {
                result.setResponseCode(ResponseCode.EMAIL_ERROR);
            } else {
                user.setStatus("1");
                int res = baseMapper.updateById(user);
                result.setResponseCode(res == 1 ? ResponseCode.SUCCESS : ResponseCode.FAIL);
            }
        } else {
            result.setResponseCode(ResponseCode.NO_OPERATION_PERMISSION);
        }
        return result;
    }

    @Override
    public Boolean isManager(Integer userId) {
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<User>().select("role_name").eq("id", userId));
        if (objects.size() == 0) return false;
        return "管理员".equals(objects.get(0));
    }

    @Override
    public ResultVO<Boolean> isEnterprise(Integer userId) {
        ResultVO<Boolean> result = new ResultVO<>();
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<User>().select("role_name").eq("id", userId));
        if (objects.size() == 0) {
            result.setData(false);
        }
        result.setData("企业".equals(objects.get(0)));
        return result;
    }

    @Override
    public ResultVO<Integer> getCompanyIdById(Integer userId) {
        ResultVO<Integer> result = new ResultVO<>();
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<User>().eq("id", userId).select("company_id"));
        if (objects == null || objects.size() == 0) {
            result.setResponseCode(ResponseCode.FAIL);
        } else {
            result.setData((Integer) objects.get(0));
        }
        return result;
    }

    @Override
    public ResultVO<String> getNameById(Integer userId) {
        ResultVO<String> result = new ResultVO<>();
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<User>().eq("id", userId).select("name"));
        if (objects == null || objects.size() == 0) {
            result.setResponseCode(ResponseCode.FAIL);
        } else {
            result.setData((String) objects.get(0));
        }
        return result;
    }

    @Override
    public ResultVO<String> getEmailById(Integer userId) {
        ResultVO<String> result = new ResultVO<>();
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<User>().eq("id", userId).select("email"));
        if (objects == null || objects.size() == 0) {
            result.setResponseCode(ResponseCode.FAIL);
        } else {
            result.setData((String) objects.get(0));
        }
        return result;
    }

    @Override
    public Boolean isHunter(Integer userId) {
        List<Object> objects = baseMapper.selectObjs(new QueryWrapper<User>().select("role_name").eq("id", userId));
        if (objects.size() == 0) return false;
        return "求职者".equals(objects.get(0));
    }

    @Override
    public User queryById(Integer id) {
        User user = baseMapper.selectById(id);
        user.setPassword(null);
        user.setCreateTime(null);
        return user;
    }

    @Override
    public ResultVO<String> updateIco(Integer userId, MultipartFile multipartFile) {
        ResultVO<String> result = new ResultVO<>();
        if (baseMapper.selectCount(new QueryWrapper<User>().eq("id", userId)) == 1) {
            ResultVO<String> fileResult = fileManagerClient.saveIco(multipartFile);
            if (fileResult.getCode() == ResponseCode.SUCCESS.getCode()) {
                User user = new User();
                user.setId(userId);
                user.setImgPath(fileResult.getData());
                baseMapper.updateById(user);
                result.setData(fileResult.getData());
            } else {
                return fileResult;
            }
        } else {
            result.setResponseCode(ResponseCode.PARAMETER_ILLEGAL);
        }
        return result;
    }

    @Override
    @Transactional
    public Boolean upgradeEnterprise(Integer userId, Integer id, Boolean isManager, Integer companyId) {
        if (isManager && !isManager(userId)) return false;
        User user = baseMapper.selectById(id);
        if (user == null) return false;
        user.setRoleName("企业");
        if (user.getIsManager() == null || "0".equals(user.getIsManager())) {
            user.setIsManager(isManager ? "1" : "0");
        }
        user.setCompanyId(companyId);
        baseMapper.updateById(user);
//        int a = 1 / 0;
        return true;
    }
}
