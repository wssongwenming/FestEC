package com.dianbin.festec.example.generators;

import com.dianbin.latte.annotations.PayEntryGenerator;
import com.dianbin.latte.wechat.templates.WXPayEntryTemplate;

@PayEntryGenerator(
        packageName = "com.dianbin.festec.example",
        payentryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
