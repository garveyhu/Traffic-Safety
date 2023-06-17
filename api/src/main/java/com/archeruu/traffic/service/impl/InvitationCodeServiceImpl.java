package com.archeruu.traffic.service.impl;

import com.archeruu.core.enums.ResultCode;
import com.archeruu.core.exception.CustomException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.archeruu.traffic.entity.InvitationCode;
import com.archeruu.traffic.service.InvitationCodeService;
import com.archeruu.traffic.mapper.InvitationCodeMapper;
import org.springframework.stereotype.Service;

/**
* @author Archer
* @description 针对表【invitation_code(邀请码表)】的数据库操作Service实现
* @createDate 2023-05-05 11:28:24
*/
@Service
public class InvitationCodeServiceImpl extends ServiceImpl<InvitationCodeMapper, InvitationCode> implements InvitationCodeService{

    @Override
    public void vertifyInvitationCode(String invitationCode) {
        LambdaQueryWrapper<InvitationCode> queryWrapper = new LambdaQueryWrapper<InvitationCode>()
                .eq(InvitationCode::getCode, invitationCode)
                .eq(InvitationCode::getIsUsed, 0);
        InvitationCode res = this.getOne(queryWrapper);
        if (res == null) {
            throw new CustomException(ResultCode.FAILURE, "邀请码不存在或已被使用");
        }
    }

    @Override
    public void updateInvitationCode(String invitationCode) {
        LambdaQueryWrapper<InvitationCode> queryWrapper = new LambdaQueryWrapper<InvitationCode>()
                .eq(InvitationCode::getCode, invitationCode);
        InvitationCode res = this.getOne(queryWrapper);
        res.setIsUsed(1);
        this.updateById(res);
    }
}




