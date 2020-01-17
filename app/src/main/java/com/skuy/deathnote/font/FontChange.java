package com.skuy.deathnote.font;

import android.app.Application;

import com.skuy.deathnote.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FontChange extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //....
    }
}
