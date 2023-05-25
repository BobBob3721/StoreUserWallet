package com.bob.storedemo.service;

import com.bob.storedemo.bo.ConsumeBo;
import com.bob.storedemo.bo.RefundBo;

/**
 * 用户钱包表Service
 */
public interface UserWalletService {

    /**
     * 查询用户钱包余额
     * @param userId
     * @return
     */
    Double findWalletBalanceByUserId(String userId);

    /**
     * 消费并记录动账明细
     * @param consumeBo
     */
    void consumeAndRecord(ConsumeBo consumeBo);

    /**
     * 退款并记录动账明细
     * @param refundBo
     */
    void refundAndRecord(RefundBo refundBo);
}
