package com.dianbin.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianbin.latte.app.AccountManager;
import com.dianbin.latte.ec.database.DatabaseManager;
import com.dianbin.latte.ec.database.UserProfile;


public class SignHandler {


    public static void onSignIn(String response,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");
        final long userId=profileJson.getLong("userId");
        final String name=profileJson.getString("name");
        final String avatar=profileJson.getString("avatar");
        final String gender=profileJson.getString("gender");
        final String address=profileJson.getString("address");
        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
        //数据库在APP开始时已经初始化，这时可以调用
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登陆成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");
        final long userId=profileJson.getLong("userId");
        final String name=profileJson.getString("name");
        final String avatar=profileJson.getString("avatar");
        final String gender=profileJson.getString("gender");
        final String address=profileJson.getString("address");
        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
        //数据库在APP开始时已经初始化，这时可以调用
        DatabaseManager.getInstance().getDao().insert(profile);
        //已经注册并登陆成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
