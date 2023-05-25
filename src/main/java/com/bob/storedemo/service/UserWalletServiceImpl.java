package com.bob.storedemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bob.storedemo.bo.ConsumeBo;
import com.bob.storedemo.bo.RefundBo;
import com.bob.storedemo.mapper.UserWalletDetailMapper;
import com.bob.storedemo.mapper.UserWalletMapper;
import com.bob.storedemo.pojo.UserWallet;
import com.bob.storedemo.pojo.UserWalletDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    private UserWalletMapper userWalletMapper;
    @Autowired
    private UserWalletDetailMapper userWalletDetailMapper;


    @Override
    public Double findWalletBalanceByUserId(String userId) {
        QueryWrapper<UserWallet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        // 使用当前用户的userId查询到唯一一条记录,user_id在tb_user_wallet中是唯一键
        UserWallet userWallet=userWalletMapper.selectOne(queryWrapper);
        return userWallet.getBalance();
    }

    @Transactional
    @Override
    public void consumeAndRecord(ConsumeBo consumeBo) {
       String userId =consumeBo.getUserId();
       String payPassword =consumeBo.getPayPassword();
       Double movingAccountMoney =consumeBo.getMovingAccountMoney();
       if (!StringUtils.isEmpty(userId)){
           // 先进行支付密码校验,余额是否能抵扣动账金额校验
           QueryWrapper<UserWallet> queryWrapper = new QueryWrapper<>();
           queryWrapper.eq("user_id",userId);
           // 使用当前用户的userId查询到唯一一条记录,user_id是唯一键
           UserWallet userWallet=userWalletMapper.selectOne(queryWrapper);
           if (payPassword.equals(userWallet.getPayPassword())){
               if (userWallet.getBalance() >= movingAccountMoney){
                   // 两个校验都成功,进行钱包余额的update
                   // 新的余额
                   Double balanceNew = userWallet.getBalance()-movingAccountMoney;
                   userWallet.setBalance(balanceNew);
                   userWallet.setUpdateTime(new Date());
                   UpdateWrapper<UserWallet> updateWrapper = new UpdateWrapper<>();
                   updateWrapper.eq("user_id",userId);
                   userWalletMapper.update(userWallet, updateWrapper);

                   // 往钱包动账明细表插入一条数据
                   UserWalletDetail walletDetail = new UserWalletDetail();
                   walletDetail.setUserId(userId);
                   walletDetail.setUsername(consumeBo.getUsername());
                   walletDetail.setBalance(balanceNew);
                   walletDetail.setMovingAccountMoney(movingAccountMoney);
                   walletDetail.setMovingAccountMessage("消费支出");
                   walletDetail.setCreateTime(new Date());
                   walletDetail.setUpdateTime(new Date());
                   userWalletDetailMapper.insert(walletDetail);
               }
           }
       }
    }

    @Transactional
    @Override
    public void refundAndRecord(RefundBo refundBo) {
        String userId =refundBo.getUserId();
        String payPassword =refundBo.getPayPassword();
        Double movingAccountMoney =refundBo.getMovingAccountMoney();
        if (!StringUtils.isEmpty(userId)){
            // 先进行支付密码校验
            QueryWrapper<UserWallet> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId);
            // 使用当前用户的userId查询到唯一一条记录,user_id是唯一键
            UserWallet userWallet=userWalletMapper.selectOne(queryWrapper);
            if (payPassword.equals(userWallet.getPayPassword())){
                    // 密码校验成功,进行钱包余额的update
                    // 新的余额
                    Double balanceNew = userWallet.getBalance()+movingAccountMoney;
                    userWallet.setBalance(balanceNew);
                    userWallet.setUpdateTime(new Date());
                    UpdateWrapper<UserWallet> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("user_id",userId);
                    userWalletMapper.update(userWallet, updateWrapper);

                    // 往钱包动账明细表插入一条数据
                    UserWalletDetail walletDetail = new UserWalletDetail();
                    walletDetail.setUserId(userId);
                    walletDetail.setUsername(refundBo.getUsername());
                    walletDetail.setBalance(balanceNew);
                    walletDetail.setMovingAccountMoney(movingAccountMoney);
                    walletDetail.setMovingAccountMessage("退款");
                    walletDetail.setCreateTime(new Date());
                    walletDetail.setUpdateTime(new Date());
                    userWalletDetailMapper.insert(walletDetail);
            }
        }
    }






}
