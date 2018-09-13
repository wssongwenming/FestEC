package com.dianbin.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.dianbin.latte.delegates.bottom.BottomItemDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ec.R2;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.ui.recycler.MultipleFields;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;
import com.dianbin.latte.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecylerView=null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout=null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar=null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan=null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView=null;
    private RefreshHandler mRefreshHandler=null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //把mRefreshLayout的刷新交给另外的函数，秉承类的代码尽量少，类可以多点”
//        mRefreshHandler=new RefreshHandler(mRefreshLayout);
//        RestClient.builder()
//                .url("index")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        IndexDataConverter converter=new IndexDataConverter();
//                        converter.setJsonData(response);
//                        final ArrayList<MultipleItemEntity> list=converter.convert();
//                       final String image= list.get(1).getField(MultipleFields.IMAGE_URL);
//                        Toast.makeText(getContext(),image,Toast.LENGTH_LONG).show();
//                    }
//                })
//                .build()
//                .get();
        mRefreshHandler=RefreshHandler.create(mRefreshLayout,mRecylerView,new IndexDataConverter());
    }

    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_red_light
        );
        //
        mRefreshLayout.setProgressViewOffset(true,120,300);
    }

    private void initRecyclerView(){
        final GridLayoutManager manager=new GridLayoutManager(getContext(),4);
        mRecylerView.setLayoutManager(manager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
