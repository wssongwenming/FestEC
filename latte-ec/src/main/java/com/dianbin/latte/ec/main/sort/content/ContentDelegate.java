package com.dianbin.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ec.R2;

import butterknife.BindView;

public class ContentDelegate extends LatteDelegate{
    private static final String ARG_CONTENT_ID="CONTENT_ID";
    private int mContentId=-1;
    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args=getArguments();
        if(args!=null){
            mContentId=args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId){
        final Bundle args=new Bundle();
        args.putInt(ARG_CONTENT_ID,contentId);
        final ContentDelegate delegate=new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager=
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

    }
}
