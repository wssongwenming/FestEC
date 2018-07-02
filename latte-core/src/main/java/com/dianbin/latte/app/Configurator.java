package com.dianbin.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

public class Configurator {
    private static final HashMap<String,Object> LATTE_CONFIG=new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList();
    private Configurator(){
        LATTE_CONFIG.put(ConfigType.CONFIG_READY.name(),false);
    }
    private static final class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }
    public static Configurator getInstance()
    {
        return Holder.INSTANCE;
    }
    final HashMap<String,Object>getConfigurations()
    {
        return LATTE_CONFIG;
    }
    public void Configure(){
        initIcons();
        LATTE_CONFIG.put(ConfigType.CONFIG_READY.name(),true);
    }
    public final Configurator withIcons(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
    public final Configurator withApiHost(String host)
    {
        LATTE_CONFIG.put(ConfigType.API_HOST.name(),host);
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
        final boolean isReady=(boolean)LATTE_CONFIG.get(ConfigType.CONFIG_READY.name());
        if(!isReady)
        {
            throw new RuntimeException("Configuration is not ready,please configure");
        }
    }
    final <T>T getConfiguration(Enum<ConfigType>key){
        checkConfiguration();
        return (T)LATTE_CONFIG.get(key.name());
    }

}
