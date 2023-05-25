package com.bob.storedemo.service;

import com.bob.storedemo.pojo.UserWalletDetail;

import java.util.List;

/**
 * 用户钱包动账明细表Service
 */
public interface UserWalletDetailService {

    /**
     * 查询用户钱包动账明细,使用userId
     * @param userId
     * @return
     */
    List<UserWalletDetail> findWalletDetailList(String userId);
}
