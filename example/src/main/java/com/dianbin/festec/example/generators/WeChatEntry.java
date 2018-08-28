package com.dianbin.festec.example.generators;

import com.dianbin.latte.annotations.EntryGenerator;
import com.dianbin.latte.wechat.templates.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.dianbin.festec.example",
        entryTemplete = WXEntryTemplate.class
)
public class WeChatEntry {
}
