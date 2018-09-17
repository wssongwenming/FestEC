package com.dianbin.latte.delegates;

public abstract class LatteDelegate extends PermissionCheckerDelegate {
    public <T extends LatteDelegate> T getParentDelegate(){
        return  (T)getParentFragment();
    }
}
