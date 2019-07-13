package com.smartlink.mac.myapplication;

import dagger.Module;
import dagger.Provides;

@Module
public class RefrigeratorModule {

    public RefrigeratorModule() {
    }

    @Provides
    String provideEggs() { return "Eggs"; }

    @Provides
    Integer provideQuantity() {
        return 12;
    }

}
