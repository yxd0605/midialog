package com.eli.midialog;

import android.view.Gravity;

/**
 * @author gaoyun@eli-tech.com 2018/05/09
 */
public enum DialogGravity {
    CENTER(Gravity.CENTER),
    RIGHT(Gravity.RIGHT),
    LEFT(Gravity.LEFT);

    private int gravity;

    DialogGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getGravity() {
        return gravity;
    }
}
