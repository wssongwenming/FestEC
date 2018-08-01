package com.dianbin.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dianbin.latte.app.Latte;
import com.dianbin.latte.ec.R2;
import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ui.launcher.ScrollLauncherTag;
import com.dianbin.latte.util.storage.LattePreference;
import com.dianbin.latte.util.timer.BaseTimerTask;
import com.dianbin.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer=null;
    private Timer mTimer=null;
    private int mCount=5;
    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer=null;
            checkIsShowScroll();
        }
    }
    private void initTimer(){
        mTimer=new Timer();
        final BaseTimerTask task=new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }
    @Override
    public Object setLayout() {
    return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }
    private void checkIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.WAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登陆了APP
        }

    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                if(mTvTimer!=null)
                {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if(mCount<0){
                        if(mTimer!=null){
                            mTimer.cancel();
                            mTimer=null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}