package com.eli.midialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.text.Html;
import android.view.View;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * 小米风格对话框
 *
 * @author gaoyun@eli-tech.com
 * @date 2018/05/09
 */
public class MiDialog {
    protected final Builder builder;
    protected Dialog dialog;

    protected MiDialog(Builder builder) {
        this.builder = builder;
        dialog = DialogInit.init(this);
    }

    public final Builder getBuilder() {
        return this.builder;
    }

    @UiThread
    public void show() {
        if (null != dialog && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @UiThread
    public void dismiss() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Nullable
    public Object getTag() {
        return this.builder.tag;
    }

    public static class Builder {
        protected final Context context;
        protected boolean cancelable;
        protected boolean touchCancelable;
        protected boolean inputable = false;
        protected boolean isDir = true;
        protected boolean hideButton = false; // 隐藏底部所有按钮
        protected boolean confirmMode = true; // 确定 & 取消 模式
        protected boolean showCheckBox = false;
        protected int listSelectedPos = -1;
        /**
         * 标题文字
         */
        protected CharSequence title;
        /**
         * 内容文字
         */
        protected CharSequence content;
        /**
         * 提示文字
         */
        protected CharSequence prompt;
        /**
         * 输入框文字
         */
        protected CharSequence input;
        /**
         * 输入框提示文字
         */
        protected CharSequence hint;
        /**
         * 列表文字
         */
        protected ArrayList<CharSequence> items;
        // protected int[] itemIds;
        /**
         * 自定义列表
         */
        protected ArrayList<ListItemGenerator> itemsList;
        /**
         * 确定按钮文字【右边】
         */
        protected CharSequence positiveText;
        /**
         * 中性按钮文字【中间】
         */
        protected CharSequence neutralText;
        /**
         * 取消按钮文字【左边】
         */
        protected CharSequence negativeText;
        /**
         * 复选框文字
         */
        protected CharSequence checkBoxPrompt;
        /**
         * 密码输入框上用户名显示
         */
        protected CharSequence userName;

        // 字体大小（px）
        protected float titleTextSize;
        protected float contentTextSize;
        protected float hintTextSize;
        protected float promptTextSize;
        protected float inputTextSize;
        protected float itemTextSize;
        protected float positiveTextSize;
        protected float neutralTextSize;
        protected float negativeTextSize;

        protected DialogGravity titleGravity;
        protected DialogGravity contentGravity;
        protected DialogGravity hintGravity;
        protected DialogGravity promptGravity;
        protected DialogGravity buttonsGravity;
        protected DialogGravity itemsGravity;

        protected int widgetColor;
        protected int backgroundColor;
        protected int dividerColor;
        protected int titleTextColor;
        protected int contentTextColor;
        protected int inputTextColor;
        protected int promptTextColor;
        protected int hintTextColor;

        // protected ColorStateList choiceWidgetColor;
        protected ColorStateList positiveTextColor;
        protected ColorStateList negativeTextColor;
        protected ColorStateList neutralTextColor;

        protected RecyclerView.LayoutManager layoutManager;
        // 回调
        protected SingleButtonCallback onPositiveCallback;
        protected ContentCallback onContentCallback;
        protected SingleButtonCallback onNegativeCallback;
        protected SingleButtonCallback onNeutralCallback;
        protected DialogInterface.OnShowListener showListener;
        protected DialogInterface.OnDismissListener dismissListener;
        protected DialogInterface.OnCancelListener cancelListener;
        protected DialogInterface.OnKeyListener keyListener;
        protected InputCallback inputCallback;
        protected InputCallbacks inputCallbacks;
        protected ListCallback listCallback;
        protected CheckBoxCallback checkBoxCallback;
        // protected ListLongCallback listLongCallback;
        // protected ListCallbackSingleChoice listCallbackSingleChoice;
        // protected ListCallbackMultiChoice listCallbackMultiChoice;

        // protected int selectedIndex;
        // protected boolean alwaysCallMultiChoiceCallback;
        // protected boolean alwaysCallSingleChoiceCallback;
        // protected Adapter<?> adapter;

        protected DialogInputType inputType;
        protected boolean inputAllowEmpty;
        protected boolean alwaysCallInputCallback = false;
        protected int inputMinLength;
        protected int inputMaxLength;
        protected int inputRangeErrorColor;

        protected Object tag;

        public  Builder(@NonNull Context context) {
            this.context = context;
            this.cancelable = true;
            this.touchCancelable = true;

            this.titleGravity = DialogGravity.CENTER;
            this.contentGravity = DialogGravity.CENTER;
            this.hintGravity = DialogGravity.CENTER;
            this.promptGravity = DialogGravity.CENTER;
            this.buttonsGravity = DialogGravity.CENTER;
            this.itemsGravity = DialogGravity.LEFT;

            this.widgetColor = context.getResources().getColor(R.color.white);
            this.backgroundColor = context.getResources().getColor(R.color.white);
            this.titleTextColor = context.getResources().getColor(R.color.color_mi_primary_text);
            this.contentTextColor = context.getResources().getColor(R.color.color_mi_primary_text);
            this.hintTextColor = context.getResources().getColor(R.color.color_mi_edittext_hint);
            this.promptTextColor = context.getResources().getColor(R.color.color_mi_second_text);
            this.inputTextColor = context.getResources().getColor(R.color.color_mi_primary_text);
            this.dividerColor = context.getResources().getColor(R.color.color_mi_list_divider);
            this.positiveTextColor = context.getResources().getColorStateList(R.color.selector_button_text_white);
            this.negativeTextColor = context.getResources().getColorStateList(R.color.selector_button_text_black);
            this.neutralTextColor = context.getResources().getColorStateList(R.color.orange);

            this.titleTextSize = context.getResources().getDimension(R.dimen.text_size_16);
            this.contentTextSize = context.getResources().getDimension(R.dimen.text_size_12);
            this.inputTextSize = context.getResources().getDimension(R.dimen.text_size_16);
            this.hintTextSize = context.getResources().getDimension(R.dimen.text_size_16);
            this.itemTextSize = context.getResources().getDimension(R.dimen.text_size_16);
            this.positiveTextSize = context.getResources().getDimension(R.dimen.text_size_16);
            this.neutralTextSize = context.getResources().getDimension(R.dimen.text_size_16);
            this.negativeTextSize = context.getResources().getDimension(R.dimen.text_size_16);
            this.promptTextSize = context.getResources().getDimension(R.dimen.text_size_12);

            this.inputType = DialogInputType.NORMAL;
            this.inputMinLength = -1;
            this.inputMaxLength = -1;
            this.inputRangeErrorColor = 0;
        }

        public final Context getContext() {
            return this.context;
        }

        public Builder title(@StringRes int titleRes) {
            this.title(this.context.getText(titleRes));
            return this;
        }

        public Builder title(@NonNull CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder titleGravity(@NonNull DialogGravity gravity) {
            this.titleGravity = gravity;
            return this;
        }

        public Builder titleColor(@ColorInt int color) {
            this.titleTextColor = color;
            return this;
        }

        public Builder titleColorRes(@ColorRes int colorRes) {
            this.titleColor(context.getResources().getColor(colorRes));
            return this;
        }

        public Builder titleTextSize(@DimenRes int size) {
            this.titleTextSize = context.getResources().getDimension(size);
            return this;
        }

        public Builder content(@StringRes int contentRes) {
            return this.content(contentRes, false);
        }

        public Builder content(@StringRes int contentRes, boolean html) {
            CharSequence text = this.context.getText(contentRes);
            if (html) {
                text = Html.fromHtml(( text).toString().replace("\n", "<br/>"));
            }

            return this.content(text);
        }

        public Builder content(@NonNull CharSequence content) {
            this.content = content;
            return this;
        }

        public Builder content(@StringRes int contentRes, Object... formatArgs) {
            String str = String.format(this.context.getString(contentRes), formatArgs).replace("\n", "<br/>");
            return this.content(Html.fromHtml(str));
        }

        public Builder contentColor(@ColorRes int color) {
            this.contentTextColor = color;
            return this;
        }

        public Builder contentTextSize(@ColorRes int size) {
            this.contentTextSize = size;
            return this;
        }

        public Builder contentGravity(@NonNull DialogGravity gravity) {
            this.contentGravity = gravity;
            return this;
        }

        public Builder hint(@StringRes int titleRes) {
            this.hint(this.context.getText(titleRes));
            return this;
        }

        public Builder hint(@NonNull CharSequence hint) {
            this.inputable = true;
            this.hint = hint;
            return this;
        }

        public Builder hintGravity(@NonNull DialogGravity gravity) {
            this.inputable = true;
            this.hintGravity = gravity;
            return this;
        }

        public Builder hintTextSize(@ColorRes int size) {
            this.inputable = true;
            this.hintTextSize = size;
            return this;
        }
        public Builder inputMaxLength(int length) {
            this.inputMaxLength = length;
            return this;
        }

        public Builder hintColor(@ColorInt int color) {
            this.inputable = true;
            this.hintTextColor = color;
            return this;
        }

        public Builder hintColorRes(@ColorRes int colorRes) {
            this.hintColor(context.getResources().getColor(colorRes));
            return this;
        }

        public Builder prompt(@StringRes int titleRes) {
            this.prompt(this.context.getText(titleRes));
            return this;
        }

        public Builder prompt(@NonNull CharSequence prompt) {
            this.prompt = prompt;
            return this;
        }

        public Builder promptGravity(@NonNull DialogGravity gravity) {
            this.promptGravity = gravity;
            return this;
        }

        public Builder promptColor(@ColorInt int color) {
            this.promptTextColor = color;
            return this;
        }

        public Builder promptTextSize(@ColorRes int size) {
            this.promptTextSize = size;
            return this;
        }

        public Builder promptColorRes(@ColorRes int colorRes) {
            this.promptColor(context.getResources().getColor(colorRes));
            return this;
        }

        public Builder positive(@StringRes int titleRes) {
            this.positive(this.context.getText(titleRes));
            return this;
        }

        public Builder positive(@NonNull CharSequence text) {
            this.positiveText = text;
            return this;
        }

        public Builder positiveTextSize(@ColorRes int size) {
            this.positiveTextSize = size;
            return this;
        }

        public Builder positiveColorRes(@ColorRes int colorRes) {
            this.positiveTextColor = context.getResources().getColorStateList(colorRes);
            return this;
        }

        public Builder negative(@StringRes int titleRes) {
            this.negative(this.context.getText(titleRes));
            return this;
        }

        public Builder negative(@NonNull CharSequence text) {
            this.negativeText = text;
            return this;
        }

        public Builder negativeTextSize(@ColorRes int size) {
            this.negativeTextSize = size;
            return this;
        }

        public Builder negativeColorRes(@ColorRes int colorRes) {
            this.negativeTextColor = context.getResources().getColorStateList(colorRes);
            return this;
        }

        public Builder neutral(@StringRes int titleRes) {
            this.neutral(this.context.getText(titleRes));
            return this;
        }

        public Builder neutral(@NonNull CharSequence text) {
            this.confirmMode = false;
            this.neutralText = text;
            return this;
        }

        public Builder checkBox(@NonNull boolean showCheckBox){
            this.showCheckBox = showCheckBox;
            return this;
        }

        public Builder neutralTextSize(@ColorRes int size) {
            this.confirmMode = false;
            this.neutralTextSize = size;
            return this;
        }

        public Builder neutralColorRes(@ColorRes int colorRes) {
            this.confirmMode = false;
            this.neutralTextColor = context.getResources().getColorStateList(colorRes);
            return this;
        }

        public Builder buttonGravity(@NonNull DialogGravity gravity) {
            this.buttonsGravity = gravity;
            return this;
        }

        public Builder items(@NonNull Collection collection) {
            if (collection.size() > 0) {
                CharSequence[] array = new CharSequence[collection.size()];
                int i = 0;

                for (Iterator var4 = collection.iterator(); var4.hasNext(); ++i) {
                    Object obj = var4.next();
                    array[i] = obj.toString();
                }

                this.items(array);
            } else if (collection.size() == 0) {
                this.items = new ArrayList();
            }

            return this;
        }

        public Builder items(@ArrayRes int itemsRes) {
            this.items(this.context.getResources().getTextArray(itemsRes));
            return this;
        }

        public Builder items(@NonNull CharSequence... items) {
            this.items = new ArrayList();
            Collections.addAll(this.items, items);
            return this;
        }

        public Builder itemsCuston(ArrayList<ListItemGenerator> arrayList) {
            this.itemsList = new ArrayList<>();
            this.itemsList.addAll(arrayList);
            return this;
        }

        public Builder itemsTextSize(@DimenRes int size) {
            this.itemTextSize = size;
            return this;
        }

        public Builder itemsGravity(@NonNull DialogGravity gravity) {
            this.itemsGravity = gravity;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = true;
            return this;
        }

        public Builder setTouchCancelable(boolean touchCancelable) {
            this.touchCancelable = touchCancelable;
            return this;
        }

        public Builder setListPostion(int listSelectedPos) {
            this.listSelectedPos = listSelectedPos;
            return this;
        }

        public Builder itemsCallback(@NonNull ListCallback callback) {
            this.listCallback = callback;
            return this;
        }
        public Builder checkCallback(@NonNull CheckBoxCallback callback) {
            this.checkBoxCallback = callback;
            return this;
        }

        public Builder showListener(@NonNull DialogInterface.OnShowListener listener) {
            this.showListener = listener;
            return this;
        }

        public Builder dismissListener(@NonNull DialogInterface.OnDismissListener listener) {
            this.dismissListener = listener;
            return this;
        }

        public Builder cancelListener(@NonNull DialogInterface.OnCancelListener listener) {
            this.cancelListener = listener;
            return this;
        }

        public Builder keyListener(@NonNull DialogInterface.OnKeyListener listener) {
            this.keyListener = listener;
            return this;
        }

        public Builder input() {
            this.inputable = true;
            return this;
        }

        public Builder input(@Nullable CharSequence hint, @Nullable CharSequence prefill, boolean allowEmptyInput, @NonNull InputCallback callback) {
            this.inputable = true;
            this.inputCallback = callback;
            this.hint = hint;
            this.input = prefill;
            this.inputAllowEmpty = allowEmptyInput;
            return this;
        }

        public Builder input(@Nullable CharSequence hint, @Nullable CharSequence prefill, boolean allowEmptyInput, boolean isDir, @NonNull InputCallback callback) {
            this.inputable = true;
            this.inputCallback = callback;
            this.hint = hint;
            this.input = prefill;
            this.inputAllowEmpty = allowEmptyInput;
            this.isDir = isDir;
            return this;
        }

        public Builder input(@Nullable CharSequence hint, @Nullable CharSequence prefill, @NonNull InputCallback callback) {
            return this.input(hint, prefill, true, callback);
        }

        public Builder input(@StringRes int hint, @StringRes int prefill, boolean allowEmptyInput, @NonNull InputCallback callback) {
            return this.input(hint == 0 ? null : this.context.getText(hint), prefill == 0 ? null : this.context.getText(prefill), allowEmptyInput, callback);
        }

        public Builder input(@StringRes int hint, @StringRes int prefill, @NonNull InputCallback callback) {
            return this.input(hint, prefill, true, callback);
        }

        public Builder input(@Nullable CharSequence userName, @NonNull InputCallbacks callback) {
            this.inputable = true;
            this.userName = userName;
            this.inputCallbacks = callback;
            return this;
        }

        public Builder inputType(DialogInputType type) {
            this.inputable = true;
            this.inputType = type;
            return this;
        }

        public Builder alwaysCallInputCallback() {
            this.alwaysCallInputCallback = true;
            return this;
        }

        public Builder onPositive(@NonNull SingleButtonCallback callback) {
            this.onPositiveCallback = callback;
            return this;
        }

        public Builder onContent(@NonNull ContentCallback callback) {
            this.onContentCallback = callback;
            return this;
        }

        public Builder onNegative(@NonNull SingleButtonCallback callback) {
            this.onNegativeCallback = callback;
            return this;
        }

        public Builder onNeutral(@NonNull SingleButtonCallback callback) {
            this.onNeutralCallback = callback;
            return this;
        }

        public Builder hideButton() {
            hideButton = true;
            return this;
        }

        public Builder tag(@Nullable Object tag) {
            this.tag = tag;
            return this;
        }

        @UiThread
        public MiDialog build() {
            return new MiDialog(this);
        }

        @UiThread
        public MiDialog show() {
            MiDialog dialog = this.build();
            dialog.show();
            return dialog;
        }

    }

    public interface SingleButtonCallback {

        void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action);

    }

    public interface ContentCallback {
        void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action);
    }

    public interface InputCallback {
        void onInput(@NonNull MiDialog dialog, CharSequence text);

    }

    public interface InputCallbacks {
        void onInput(@NonNull MiDialog dialog, CharSequence text, CharSequence confirmText);
    }

    public interface ListCallback {
        void onSelection(@NonNull MiDialog dialog, View v, int pos, CharSequence text);
    }

    public interface CheckBoxCallback{
        void onCheckBoxChange(@NonNull MiDialog dialog, boolean isChecked);
    }
}
