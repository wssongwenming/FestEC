package com.dianbin.festec.example.generators;

import com.dianbin.latte.annotations.AppRegisterGenerator;
import com.dianbin.latte.wechat.templates.AppRegisterTemplate;
import com.dianbin.latte.wechat.templates.WXEntryTemplate;

@AppRegisterGenerator(
        packageName = "com.dianbin.festec.example",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
