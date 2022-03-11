package com.eli.midialog;

/**
 * Created by Administrator on 2018/8/27.
 */

public class ListItemGenerator {
    private String displayValue; //显示的值
    private String displayValueInfo; //显示的值解释说明
    private int textColor;//文字的颜色值
    private boolean isClickable; //是否可点击
    private String useValue;//实际调用使用的值
    private boolean showRightBtn = false;//是否显示右边图标

    public ListItemGenerator(String displayValue, int textColor, boolean isClickable) {
        this.displayValue = displayValue;
        this.textColor = textColor;
        this.isClickable = isClickable;
    }

    public ListItemGenerator(String displayValue, String useValue, int textColor, boolean isClickable) {
        this.displayValue = displayValue;
        this.textColor = textColor;
        this.isClickable = isClickable;
        this.useValue = useValue;
    }

    public ListItemGenerator(String displayValue, String displayValueInfo, int textColor, boolean isClickable, String useValue, boolean showRightBtn) {
        this.displayValue = displayValue;
        this.displayValueInfo = displayValueInfo;
        this.textColor = textColor;
        this.isClickable = isClickable;
        this.useValue = useValue;
        this.showRightBtn = showRightBtn;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public void setClickable(boolean clickable) {
        isClickable = clickable;
    }

    public String getUseValue() {
        return useValue;
    }

    public void setUseValue(String useValue) {
        this.useValue = useValue;
    }

    public boolean isShowRightBtn() {
        return showRightBtn;
    }

    public void setShowRightBtn(boolean showRightBtn) {
        this.showRightBtn = showRightBtn;
    }

    public String getDisplayValueInfo() {
        return displayValueInfo;
    }

    public void setDisplayValueInfo(String displayValueInfo) {
        this.displayValueInfo = displayValueInfo;
    }

    @Override
    public String toString() {
        return "ListItemGenerator{" +
                "displayValue='" + displayValue + '\'' +
                ", displayValueInfo='" + displayValueInfo + '\'' +
                ", textColor=" + textColor +
                ", isClickable=" + isClickable +
                ", useValue='" + useValue + '\'' +
                ", showRightBtn=" + showRightBtn +
                '}';
    }
}
