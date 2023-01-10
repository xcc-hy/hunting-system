package com.study.hunting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.hunting.domain.Job;
import com.study.hunting.domain.UserStar;
import com.study.hunting.dao.UserStarMapper;
import com.study.hunting.enums.ResponseCode;
import com.study.hunting.service.UserStarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.hunting.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xcc
 * @since 2022-10-02
 */
@Service
public class UserStarServiceImpl extends ServiceImpl<UserStarMapper, UserStar> implements UserStarService {

    @Override
    public List<Integer> queryMyStar(Integer userId) {
        return baseMapper.selectObjs(
                new QueryWrapper<UserStar>()
                        .select("job_id")
                        .eq("user_id",userId))
                .stream().map(o -> (Integer) o).collect(Collectors.toList());
    }

    @Override
    public ResultVO addStar(Integer userId, Integer jobId) {
        ResultVO result = new ResultVO();
        if (baseMapper.selectCount(new QueryWrapper<UserStar>().eq("user_id",userId)) >= 50) {
            result.setResponseCode(ResponseCode.FAIL);
            result.setMsg("最大收藏数量为50");
        } else {
            UserStar userStar = new UserStar();
            userStar.setJobId(jobId);
            userStar.setUserId(userId);
            if (baseMapper.insert(userStar) == 1) {
                result.setResponseCode(ResponseCode.SUCCESS);
            } else {
                result.setResponseCode(ResponseCode.FAIL);
                result.setMsg("你已收藏该工作");
            }
        }
        return result;
    }

    @Override
    public ResultVO removeStar(Integer userId, Integer jobId) {
        ResultVO result = new ResultVO();
        if (baseMapper.delete(new QueryWrapper<UserStar>().eq("user_id",userId).eq("job_id",jobId)) == 1) {
            result.setResponseCode(ResponseCode.SUCCESS);
        } else {
            result.setResponseCode(ResponseCode.FAIL);
        }
        return result;
    }
}
