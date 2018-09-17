package com.dianbin.latte.ec.main.sort.list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dianbin.latte.ec.R;
import com.dianbin.latte.ec.main.sort.SortDelegate;
import com.dianbin.latte.ui.recycler.ItemType;
import com.dianbin.latte.ui.recycler.MultipleFields;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;
import com.dianbin.latte.ui.recycler.MultipleRecyclerAdapter;
import com.dianbin.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE=delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType())
        {
            case ItemType.VERTICAL_MENU_LIST:
                final String text=entity.getField(MultipleFields.TEXT);
                final boolean isClicked=entity.getField(MultipleFields.TAG);
                final AppCompatTextView name=holder.getView(R.id.tv_vertical_item_name);
                final View line=holder.getView(R.id.view_line);
                final View itemView=holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                    }
                });
                if(!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                }
                break;
            default:
               break;

        }
    }
}
