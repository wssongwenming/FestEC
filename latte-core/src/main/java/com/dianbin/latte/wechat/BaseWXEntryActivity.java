package com.dianbin.latte.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.IError;
import com.dianbin.latte.net.callback.IFailure;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.util.log.LatteLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;


public abstract class BaseWXEntryActivity extends BaseWXActivity {
    //用户登录成功后的回调
    protected abstract void onSignInSuccess(String userInfo);
    //当微信发送请求到你的应用，将通过IWXAPIEventHandler接口的onReq方法进行回调，类似的，应用请求微信的响应结果将通过onResp回调。
    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }
    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code=((SendAuth.Resp)baseResp).code;
        //拿到ｃｏｄｅ后就可以进行第一次请求
        final StringBuilder authUrl=new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatteWeChat.APP_ID)
                .append("&secret=")
                .append(LatteWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        LatteLogger.d("authUrl",authUrl.toString());
        getAuth(authUrl.toString());
    }
    private void getAuth(String authUrl){
        RestClient
        .builder()
        .url(authUrl)
        .success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final JSONObject authObj= JSON.parseObject(response);
                final String accessToken=authObj.getString("access_token");
                final String openId=authObj.getString("openid");
                final StringBuilder userInfoUrl=new StringBuilder();
                userInfoUrl
                        .append("https://api.weixin.qq.com/sns/userinfo?access_token")
                        .append(accessToken)
                        .append("&openid")
                        .append(openId)
                        .append("&lang=")
                        .append("zh_CN");
                LatteLogger.d("userInfoUrl",userInfoUrl.toString());
                getUserInfo(userInfoUrl.toString());

            }
        })
        .build()
        .get();
    }
    private void getUserInfo(String userInfoUrl){
        RestClient
                .builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                    //这里表示已经微信登陆成功
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
