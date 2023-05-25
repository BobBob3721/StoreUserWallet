package com.bob.storedemo.aop;

import com.bob.storedemo.bo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//标识全局异常的处理
@RestControllerAdvice
//当程序在controller中执行有异常时则会执行通知的具体方法
public class SystemExceptionHandler {


    //当出现某种异常时,会执行该通知方法.
    @ExceptionHandler(RuntimeException.class)
    //@ResponseBody	//返回json信息
    public SysResult sysResult(Exception exception) {
        exception.printStackTrace();
        return SysResult.fail();
    }

}
