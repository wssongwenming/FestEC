package com.dianbin.latte.wechat;

import android.app.Activity;

import com.dianbin.latte.app.ConfigKeys;
import com.dianbin.latte.app.Latte;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LatteWeChat {
    //要接入微信，首先要在威信平台注册获得APP_ID和APP_SECRET
    static final String APP_ID= Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET=Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    //LatteWeChat懒汉单例模式
    private static final class Holder{
        private static final LatteWeChat INSTANCE=new LatteWeChat();
    }
    public static LatteWeChat getInstancee(){
        return Holder.INSTANCE;
    }
    private LatteWeChat(){
        final Activity activity=Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI= WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }
    public final IWXAPI getWXAPI(){
        return WXAPI;
    }
    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        //威信规定的字符串开发
        req.scope="snsapi_userinfo";
        req.state="random_state";
        WXAPI.sendReq(req);
    }
}
