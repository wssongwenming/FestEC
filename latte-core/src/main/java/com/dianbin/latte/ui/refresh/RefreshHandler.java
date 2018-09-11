package com.dianbin.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.dianbin.latte.app.Latte;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
    private final SwipeRefreshLayout REFESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFESH_LAYOUT){
        this.REFESH_LAYOUT=REFESH_LAYOUT;
        REFESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh(){
        REFESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //可以进行网络请求，REFESH_LAYOUT.setRefreshing(false);可以放入网络请求的success回调
                REFESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }
    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_LONG).show();

                    }
                })
                .build()
                .get();

    }
    @Override
    public void onRefresh() {
        refresh();

    }
}
