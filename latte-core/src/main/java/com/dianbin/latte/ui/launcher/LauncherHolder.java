package com.dianbin.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mImageView=null;
    @Override
    public View createView(Context context) {
        mImageView=new AppCompatImageView(context);
        return mImageView;
    }
    //每次滑动时需要更新的图片，这里为了满屏，没有用mImageView的src
    @Override
    public void UpdateUI(Context context, int i, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
