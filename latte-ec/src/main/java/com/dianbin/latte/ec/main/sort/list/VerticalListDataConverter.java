package com.dianbin.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianbin.latte.ui.recycler.DataConverter;
import com.dianbin.latte.ui.recycler.ItemType;
import com.dianbin.latte.ui.recycler.MultipleFields;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList <MultipleItemEntity> dataList=new ArrayList<>();
        final JSONArray dataArray=JSON.
                parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");
        final int size=dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data=dataArray.getJSONObject(i);
            final int id=data.getInteger("id");
            final String name=data.getString("name");
            final MultipleItemEntity entity=MultipleItemEntity.builder()
                    .setFiled(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setFiled(MultipleFields.ID,id)
                    .setFiled(MultipleFields.TEXT,name)
                    //控制点击状态
                    .setFiled(MultipleFields.TAG,false)
                    .build();
            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG,true);
        }
        return dataList;
    }
}
