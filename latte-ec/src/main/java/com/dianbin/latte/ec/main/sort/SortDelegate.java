package com.dianbin.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dianbin.latte.delegates.bottom.BottomItemDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ec.main.sort.content.ContentDelegate;
import com.dianbin.latte.ec.main.sort.list.VerticalListDelegate;

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate=new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
        replaceLoadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1),false);



    }
}
