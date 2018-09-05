package com.dianbin.latte.wechat.templates;

import com.dianbin.latte.activities.ProxyActivity;
import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.wechat.BaseWXEntryActivity;
import com.dianbin.latte.wechat.LatteWeChat;

public class WXEntryTemplate  extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        //0
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstancee().getSignInCallback().onSignInSuccess(userInfo);

    }
}
