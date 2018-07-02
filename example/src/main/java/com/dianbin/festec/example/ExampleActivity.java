package com.dianbin.festec.example;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.widget.Toast;

import com.dianbin.latte.activities.ProxyActivity;
import com.dianbin.latte.app.Latte;
import com.dianbin.latte.delegates.LatteDelegate;
public  class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
