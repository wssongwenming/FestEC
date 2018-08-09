package com.dianbin.latte.ec.sign;

public interface ISignListener {
    //分别登录和注册成功的回调
    void onSignInSuccess();
    void onSignUpSuccess();
}
