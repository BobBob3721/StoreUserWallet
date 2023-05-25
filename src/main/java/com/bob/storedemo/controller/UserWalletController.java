package com.bob.storedemo.controller;

import com.bob.storedemo.bo.ConsumeBo;
import com.bob.storedemo.bo.RefundBo;
import com.bob.storedemo.bo.SysResult;
import com.bob.storedemo.pojo.UserWallet;
import com.bob.storedemo.pojo.UserWalletDetail;
import com.bob.storedemo.service.UserWalletDetailService;
import com.bob.storedemo.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userwallet")
public class UserWalletController {

    @Autowired
    private UserWalletService userWalletService;
    @Autowired
    private UserWalletDetailService walletDetailService;

    /**
     * 查询用户钱包表该用户钱包余额接口,接受get请求
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getBalance",method = RequestMethod.GET)
    public Double getBalance(String userId){
        Double walletBalance=userWalletService.findWalletBalanceByUserId(userId);
        return walletBalance;
    }

    /**
     * 用户钱包消费接口,接受post请求
     * @param consumeBo
     * @return
     */
    @RequestMapping(value = "/consume",method = RequestMethod.POST)
    public SysResult walletConsume(@RequestBody ConsumeBo consumeBo){
        userWalletService.consumeAndRecord(consumeBo);
        return new SysResult(200,"该笔消费成功",null);
    }

    /**
     * 用户钱包退款接口,接受post请求
     * @param refundBo
     * @return
     */
    @RequestMapping(value = "/refund",method = RequestMethod.POST)
    public SysResult walletRefund(@RequestBody RefundBo refundBo){
        userWalletService.refundAndRecord(refundBo);
        return new SysResult(200,"退款成功",null);
    }

    /**
     * 查询用户钱包动账明细表的接口,get请求
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findWalletDetail",method = RequestMethod.GET)
    public List<UserWalletDetail> findWalletDetail(String userId){
        List<UserWalletDetail> walletDetailList = walletDetailService.findWalletDetailList(userId);
        return walletDetailList;
    }


}
