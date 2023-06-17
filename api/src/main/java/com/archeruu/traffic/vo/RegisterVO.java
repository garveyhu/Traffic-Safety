package com.archeruu.traffic.vo;

import lombok.Data;

/**
 * 注册账号实体类
 *
 * @author Archer
 */
@Data
public class RegisterVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邀请码
     */
    private String inviteCode;
}
