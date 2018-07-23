package com.dianbin.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

public class Configurator {
    private static final HashMap<Object,Object> LATTE_CONFIG=new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList();
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();
    private Configurator(){
        LATTE_CONFIG.put(ConfigKeys.CONFIG_READY,false);
    }
    private static final class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }
    public static Configurator getInstance()
    {
        return Holder.INSTANCE;
    }
    final HashMap<Object,Object>getConfigurations()
    {
        return LATTE_CONFIG;
    }
    public void Configure(){
        initIcons();
        LATTE_CONFIG.put(ConfigKeys.CONFIG_READY,true);
    }
    public final Configurator withIcons(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
    public final Configurator withInterceptor(Interceptor interceptor)
    {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIG.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors)
    {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIG.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withApiHost(String host)
    {
        LATTE_CONFIG.put(ConfigKeys.API_HOST,host);
        return this;
    }
    private void initIcons(){
        if(ICONS.size()>0)
        {
            Iconify.IconifyInitializer iconifyInitializer= Iconify.with(ICONS.get(0));
            for (int i = 0; i <ICONS.size() ; i++) {
                iconifyInitializer.with(ICONS.get(i));
            }
        }
    }
    private void checkConfiguration(){
        final boolean isReady=(boolean)LATTE_CONFIG.get(ConfigKeys.CONFIG_READY);
        if(!isReady)
        {
            throw new RuntimeException("Configuration is not ready,please configure");
        }
    }
 /*   final <T>T getConfiguration(Enum<ConfigType>key){
        checkConfiguration();
        return (T)LATTE_CONFIG.get(key.name());
    }*/
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIG.get(key);
        if (value == null) {
            //不能随便抛出异常，这样会导致APP
           // throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIG.get(key);
    }
}
