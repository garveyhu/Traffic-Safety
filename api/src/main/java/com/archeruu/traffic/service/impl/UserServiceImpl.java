package com.archeruu.traffic.service.impl;

import com.archeruu.core.enums.ResultCode;
import com.archeruu.core.exception.CustomException;
import com.archeruu.core.utils.BeanUtil;
import com.archeruu.traffic.service.InvitationCodeService;
import com.archeruu.traffic.vo.RegisterVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.archeruu.traffic.entity.User;
import com.archeruu.traffic.service.UserService;
import com.archeruu.traffic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 针对表【user】的数据库操作
 *
 * @author Archer
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private InvitationCodeService invitationCodeService;

    @Override
    public void insertUser(RegisterVO registerVO) {
        // 验证邀请码
        invitationCodeService.vertifyInvitationCode(registerVO.getInviteCode());

        // 创建用户
        User newUser = BeanUtil.covertBean(registerVO, User.class);
        if (uniqueUsername(newUser.getUsername())) {
            this.save(newUser);
            invitationCodeService.updateInvitationCode(registerVO.getInviteCode());
        } else {
            throw new CustomException(ResultCode.FAILURE, "用户名已存在");
        }
    }

    @Override
    public boolean uniqueUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);
        User res = this.getOne(queryWrapper);

        return res == null;
    }

    @Override
    public User vertifyUser(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword());
        User res = this.getOne(queryWrapper);
        if (res == null) {
            throw new CustomException(ResultCode.FAILURE, "用户名或密码错误");
        }

        res.setPassword("******");
        return res;
    }
}




