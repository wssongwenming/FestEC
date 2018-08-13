package com.dianbin.latte.ec.launcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dianbin.latte.app.AccountManager;
import com.dianbin.latte.app.IUserChecker;
import com.dianbin.latte.app.Latte;
import com.dianbin.latte.ec.R2;
import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ui.launcher.ILauncherListener;
import com.dianbin.latte.ui.launcher.OnLauncherFinishTag;
import com.dianbin.latte.ui.launcher.ScrollLauncherTag;
import com.dianbin.latte.util.storage.LattePreference;
import com.dianbin.latte.util.timer.BaseTimerTask;
import com.dianbin.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    /*
    Timer和TimerTask
　　Timer是jdk中提供的一个定时器工具，使用的时候会在主线程之外起一个单独的线程执行指定的计划任务，可以指定执行一次或者反复执行多次。
    TimerTask是一个实现了Runnable接口的抽象类，代表一个可以被Timer执行的任务。
    */

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer=null;
    private Timer mTimer=null;
    private int mCount=5;
    private ILauncherListener mILauncherListener=null;
    //
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener)
        {
            mILauncherListener=(ILauncherListener)activity;
        }
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
            AccountManager.checkAccount(new IUserChecker(){
                @Override
                public void onSignIn() {
                    if(mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }
                @Override
                public void onNotSignIn() {
                    if(mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }

                }
            });
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
                    //倒计时数完
                    if(mCount<0){
                        if(mTimer!=null){
                            mTimer.cancel();
                            mTimer=null;
                            //是不是第一次安装，是不是要进入滚动页面,
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
