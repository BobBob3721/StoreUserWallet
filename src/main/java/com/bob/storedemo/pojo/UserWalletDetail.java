package com.bob.storedemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户钱包动账明细表
 */
@Data
@Accessors(chain=true)
@TableName("tb_user_wallet_detail")
public class UserWalletDetail {

    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 钱包余额
     */
    private Double balance;

    /**
     * 动账金额
     */
    @TableField("moving_account_money")
    private Double movingAccountMoney;

    /**
     * 动账信息描述
     */
    @TableField("moving_account_message")
    private String movingAccountMessage;

    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

}
