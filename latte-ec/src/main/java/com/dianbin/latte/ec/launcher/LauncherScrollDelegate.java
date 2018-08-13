package com.dianbin.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.dianbin.latte.app.AccountManager;
import com.dianbin.latte.app.IUserChecker;
import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ui.launcher.ILauncherListener;
import com.dianbin.latte.ui.launcher.LauncherHolderCreator;
import com.dianbin.latte.ui.launcher.OnLauncherFinishTag;
import com.dianbin.latte.ui.launcher.ScrollLauncherTag;
import com.dianbin.latte.util.storage.LattePreference;

import java.util.ArrayList;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    //ConvenientBanner<T>是一个泛型类，在本例中要传入的是一个资源文件，
    private ConvenientBanner<Integer>mConvenientBanner=null;
    private ILauncherListener mILauncherListener=null;
    private static final ArrayList<Integer>INTEGERS=new ArrayList<>();
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
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
        mConvenientBanner=new ConvenientBanner<>(getContext());
        return mConvenientBanner;

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if(position==INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.WAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
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
}
