package com.eli.midialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * View.findViewById(int id)
     */
    public <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * View.findViewById(int id).setVisibility(visibility)
     */
    public <T extends View> T $(int resId, int visibility) {
        T t = $(resId);
        t.setVisibility(visibility);
        return t;
    }

    /**
     * View.findViewById(int id).setOnClickListener(listener)
     */
    public <T extends View> T $(int resId, View.OnClickListener listener) {
        T t = $(resId);
        t.setOnClickListener(listener);
        return t;
    }

    /**
     * View.findViewById(int id).setOnClickListener(listener).setVisibility(int)
     */
    public <T extends View> T $(int resId, View.OnClickListener listener, int visibility) {
        T t = $(resId);
        t.setOnClickListener(listener);
        t.setVisibility(visibility);
        return t;
    }

    /**
     * View.findViewById(int id)
     */
    public <T extends View> T $(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    /**
     * View.findViewById(int id,.setVisibility(visibility))
     */
    public <T extends View> T $(View view, int resId, int visibility) {
        T t = $(view, resId);
        t.setVisibility(visibility);
        return t;
    }

    /**
     * View.findViewById(int id).setOnClickListener(listener)
     */
    public <T extends View> T $(View view, int resId, View.OnClickListener listener) {
        T t = $(view, resId);
        t.setOnClickListener(listener);
        return t;
    }

    /**
     * View.findViewById(int id).setOnClickListener(listener).setVisibility(visibility)
     */
    public <T extends View> T $(View view, int resId, View.OnClickListener listener, int visibility) {
        T t = $(view, resId);
        t.setOnClickListener(listener);
        t.setVisibility(visibility);
        return t;
    }
}
