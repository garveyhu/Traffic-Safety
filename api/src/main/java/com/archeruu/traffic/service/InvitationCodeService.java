package com.archeruu.traffic.service;

import com.archeruu.traffic.entity.InvitationCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Archer
* @description 针对表【invitation_code(邀请码表)】的数据库操作Service
* @createDate 2023-05-05 11:28:24
*/
public interface InvitationCodeService extends IService<InvitationCode> {
    void vertifyInvitationCode(String invitationCode);

    void updateInvitationCode(String invitationCode);
}
