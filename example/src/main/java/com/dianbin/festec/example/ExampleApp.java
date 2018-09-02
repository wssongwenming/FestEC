package com.dianbin.festec.example;

import android.app.Application;
import android.provider.ContactsContract;

import com.dianbin.latte.app.Latte;
import com.dianbin.latte.ec.database.DatabaseManager;
import com.dianbin.latte.ec.icon.FontEcModule;
import com.dianbin.latte.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public  class ExampleApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .withIcons(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                //.withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withInterceptor(new DebugInterceptor("index",R.raw.profile))
                .withWeChatAppId("")//微信登陆初始化AppId
                .withWeChatAppSecret("")//微信登陆人传入Secret
                .Configure();
        //facebook的一个工具，可以把原生的数据映射到web上展现出来，
        initStetho();
        //利用GreenDAO初始化数据库
        DatabaseManager.getInstance().init(this);
    }
    private void initStetho(){
     Stetho.initialize(Stetho.newInitializerBuilder(this)
     .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
     .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
     .build());
    }
}
