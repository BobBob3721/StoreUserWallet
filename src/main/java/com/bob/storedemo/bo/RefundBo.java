package com.bob.storedemo.bo;

import lombok.Data;

/**
 * 退款的BO
 */
@Data
public class RefundBo {

    // 用户id
    private String userId;
    // 用户姓名
    private String username;
    // 支付密码
    private String payPassword;
    // 动账金额,跟前端约定好传过来的均为正数,代码里去增加
    private Double movingAccountMoney;
    // 动账信息描述
    private String movingAccountMessage;
}
