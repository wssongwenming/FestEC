package com.dianbin.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;

import com.dianbin.latte.delegates.LatteDelegate;

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
