package com.bob.storedemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户钱包表
 */
@Data
@Accessors(chain=true)
@TableName("tb_user_wallet")
public class UserWallet {


    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 用户Id,唯一键
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
     * 支付密码
     */
    @TableField("pay_password")
    private String payPassword;

    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;


}
