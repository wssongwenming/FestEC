package com.dianbin.latte.app;

import com.dianbin.latte.util.storage.LattePreference;

/*管理用户信息*/
public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    //登录后调用的方法,保存用户登录状态
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    //判断是不是已经登录
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
    public static void checkAccount(IUserChecker checker){
        if(isSignIn())
        {
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
