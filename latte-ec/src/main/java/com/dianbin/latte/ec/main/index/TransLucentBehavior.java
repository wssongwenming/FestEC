package com.dianbin.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.dianbin.latte.app.Latte;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ui.recycler.RgbValue;

public class TransLucentBehavior extends CoordinatorLayout.Behavior<Toolbar>{

    //顶部距离
    private int mDistanceY=0;
    //颜色变化速度
    private static final int SHOW_SPEED=3;
    //定义变化的颜色
    private final RgbValue RGB_VALUE= RgbValue.create(255,124,2);

    private int mOffset=0;
    private static final int MORE=100;
    public TransLucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId()== R.id.rv_index ;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

   @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        mDistanceY+=dy;
        final int targetHeight=child.getBottom();
        if(mDistanceY>0&&mDistanceY<=targetHeight){
            final float scale=(float) mDistanceY/targetHeight;
            final float alpha=scale*255;
            child.setBackgroundColor(Color.argb((int)alpha,RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));

        }else if(mDistanceY>targetHeight)
        {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(),RGB_VALUE.green(),RGB_VALUE.blue()));
        }
    }
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar toolbar, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, toolbar, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        final int startOffset=0;
        final Context context=Latte.getApplicationContext();
        final int endOffset=context.getResources().getDimensionPixelOffset(R.dimen.sp_12)+MORE;
        //增加滑动距离
        mOffset+=dyConsumed;
        if(mOffset<=startOffset){
            toolbar.getBackground().setAlpha(0);
        }else if(mOffset>startOffset&&mOffset<endOffset){
            final float perent=(float) (mOffset-startOffset)/endOffset;
            final int alpha=Math.round(perent*255);
            toolbar.getBackground().setAlpha(alpha);
        }else if(mOffset>endOffset){
            toolbar.getBackground().setAlpha(255);
        }
    }

}
