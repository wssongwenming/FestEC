package com.dianbin.festec.example;

import android.app.Application;

import com.dianbin.latte.app.Latte;
import com.dianbin.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public  class ExampleApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .withIcons(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .Configure();

    }
}
