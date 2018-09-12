package com.dianbin.latte.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dianbin.latte.R;

import java.util.List;

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder>
implements BaseQuickAdapter.SpanSizeLookup{
    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
    }
    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter){
        return new MultipleRecyclerAdapter(converter.convert());
    }

    private void init(){
     //设置不同的item布局
        addItemType(ItemType.TEXT, R.layout.item_mutiple_text);
        addItemType(ItemType.IMAGE,R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE,R.layout.item_multiple_image_text);
        addItemType(ItemType.TEXT_IMAGE,R.layout.item_multiple_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder multipleViewHolder, MultipleItemEntity multipleItemEntity) {

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
