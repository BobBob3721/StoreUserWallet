package com.bob.storedemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bob.storedemo.mapper.UserWalletDetailMapper;
import com.bob.storedemo.pojo.UserWalletDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWalletDetailServiceImpl implements UserWalletDetailService {
    @Autowired
    private UserWalletDetailMapper userWalletDetailMapper;


    @Override
    public List<UserWalletDetail> findWalletDetailList(String userId) {
        QueryWrapper<UserWalletDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserWalletDetail> walletDetailList = userWalletDetailMapper.selectList(queryWrapper);
        return walletDetailList;
    }

}
