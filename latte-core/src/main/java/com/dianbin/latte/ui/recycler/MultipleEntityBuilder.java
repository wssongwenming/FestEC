package com.dianbin.latte.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

public class MultipleEntityBuilder {
    private static final LinkedHashMap<Object,Object> FIELDS=new LinkedHashMap<>();
    public MultipleEntityBuilder(){
        //先清除之前的数据
        FIELDS.clear();
    }
    public final MultipleEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }
    public final MultipleEntityBuilder setFiled(Object key, Object value){
        FIELDS.put(key,value);
        return this;
    }
    public final MultipleEntityBuilder setFields(WeakHashMap<?,?> map){
        FIELDS.putAll(map);
        return this;
    }
    public final MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }

}
