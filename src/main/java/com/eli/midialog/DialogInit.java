package com.eli.midialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.chaos.view.PinView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;


/**
 * @author liuzhu@eli-tech.com  2019/06/03
 */
public class DialogInit {
    private static final String TAG = "DialogInit";

    public static Dialog init(final MiDialog miDialog) {
        final MiDialog.Builder builder = miDialog.builder;
        final View view = LayoutInflater.from(builder.context).inflate(R.layout.layout_dialog, null);
        final Context context = builder.context;

        final TextView mTitleView = view.findViewById(R.id.txt_dialog_title);
        final Button mNegativeButton = view.findViewById(R.id.btn_dialog_left);
        final Button mPositiveButton = view.findViewById(R.id.btn_dialog_right);
        final Button mNeutralButton = view.findViewById(R.id.btn_dialog_mid);
        final EditText mEditText = view.findViewById(R.id.et_dialog_input);
        final PinView mPinView = view.findViewById(R.id.pin_view);

        final EditText mEditSafemm = view.findViewById(R.id.edit_input_one);
        final EditText mEditPwdAgain = view.findViewById(R.id.edit_input_two);
        final TextView mUserNameView = view.findViewById(R.id.tv_dialog_two);
        final LinearLayout mInputLayout = view.findViewById(R.id.layout_input);

        if (Utils.isEmpty(builder.title)) {
            mTitleView.setVisibility(View.GONE);
        } else {
            mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, builder.titleTextSize);
            mTitleView.setTextColor(builder.titleTextColor);
            mTitleView.setGravity(builder.titleGravity.getGravity());
            mTitleView.setText(builder.title);
        }

        TextView mTextView = view.findViewById(R.id.txt_dialog_content);
        if (Utils.isEmpty(builder.content)) {
            mTextView.setVisibility(View.GONE);
        } else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTextView.getLayoutParams();
            lp.topMargin = Utils.dip2Px(context, 23);
            mTextView.setLayoutParams(lp);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, builder.contentTextSize);
            mTextView.setTextColor(builder.contentTextColor);
            mTextView.setGravity(builder.contentGravity.getGravity());
            mTextView.setText(builder.content);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != builder.onContentCallback) {
                        builder.onContentCallback.onClick(miDialog, DialogAction.CONTENT);
                    }
                }
            });
        }

        mTextView = (TextView) view.findViewById(R.id.txt_dialog_prompt);
        if (Utils.isEmpty(builder.prompt)) {
            mTextView.setVisibility(View.GONE);
        } else {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTextView.getLayoutParams();
            lp.topMargin = Utils.dip2Px(context, 10);
            mTextView.setLayoutParams(lp);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, builder.promptTextSize);
            mTextView.setTextColor(builder.promptTextColor);
            mTextView.setGravity(builder.promptGravity.getGravity());
            mTextView.setText(builder.prompt);
        }

        if (!builder.hideButton) {
            if (builder.confirmMode) {
                LinearLayout mButtonLayout = view.findViewById(R.id.layout_button);
                mButtonLayout.setVisibility(View.VISIBLE);
                mNegativeButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, builder.negativeTextSize);
                mNegativeButton.setTextColor(builder.negativeTextColor);
                mNegativeButton.setText(builder.negativeText);
                mNegativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != builder.onNegativeCallback) {
                            builder.onNegativeCallback.onClick(miDialog, DialogAction.NEGATIVE);
                        } else {
                            miDialog.dismiss();
                        }
                    }
                });

                mPositiveButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, builder.positiveTextSize);
                mPositiveButton.setTextColor(builder.positiveTextColor);
                mPositiveButton.setText(builder.positiveText);
                mPositiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (builder.inputable) {
                            if (null != builder.inputCallback) {
                                if (builder.inputType == DialogInputType.PIN) {
                                    builder.inputCallback.onInput(miDialog, mPinView.getText());
                                } else {
                                    builder.inputCallback.onInput(miDialog, mEditText.getText());
                                }
                            }
                            if (null != builder.inputCallbacks) {
                                if (builder.inputType == DialogInputType.PASSWORD) {
                                    builder.inputCallbacks.onInput(miDialog, mEditSafemm.getText(), mEditPwdAgain.getText());
                                }
                            }
                        } else {
                            if (null != builder.onPositiveCallback) {
                                builder.onPositiveCallback.onClick(miDialog, DialogAction.POSITIVE);
                            }
                        }
                    }
                });
            } else {
                mNeutralButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, builder.neutralTextSize);
                mNeutralButton.setTextColor(builder.neutralTextColor);
                mNeutralButton.setText(builder.neutralText);
                mNeutralButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != builder.onNeutralCallback) {
                            mNeutralButton.setTextColor(builder.positiveTextColor);
                            builder.onNeutralCallback.onClick(miDialog, DialogAction.NEUTRAL);
                        }
                    }
                });
                mNeutralButton.setVisibility(View.VISIBLE);
            }
        } else {
            mNeutralButton.setVisibility(View.GONE);
        }

        if (builder.inputable) {
            if (builder.inputType == DialogInputType.PIN) {
                int len = builder.hint.length();
                if (len > 0) {
                    float width = context.getResources().getDisplayMetrics().widthPixels - 2 * context.getResources().getDimension(R.dimen.mi_dialog_margin);
                    float size = context.getResources().getDimension(R.dimen.mi_dialog_confirm_edittext_size);
                    int margin = (int) ((width - len * size) / (len - 1));
                    mPinView.setItemSpacing(margin);
                    mPinView.setHint(builder.hint);
                    mPinView.setItemCount(len);
                    mPinView.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!Utils.isEmpty(s)) {
                                if (s.toString().equals(builder.hint)) {
                                    mPositiveButton.setEnabled(true);
                                    return;
                                }
                            }
                            mPositiveButton.setEnabled(false);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });
                    mPinView.setVisibility(View.VISIBLE);
                    mPinView.setAnimationEnable(true);
                    mPositiveButton.setEnabled(false);
                    mPinView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPinView.requestFocus();
                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (imm != null) {
                                imm.showSoftInput(mPinView, InputMethodManager.SHOW_IMPLICIT);
                            }
                        }
                    }, 100);
                }
            } else if (builder.inputType == DialogInputType.NORMAL) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mEditText.getLayoutParams();
                lp.topMargin = Utils.dip2Px(context, 23);
                mEditText.setLayoutParams(lp);
                mEditText.setTextColor(builder.inputTextColor);
                mEditText.setHintTextColor(builder.hintTextColor);
                mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, builder.inputTextSize);
                mEditText.setHint(builder.hint);
                mEditText.setText(builder.input);
                if (builder.inputMaxLength > 0) {
                    mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(builder.inputMaxLength)});
                }
                int selection = builder.input.length();
                if (!builder.isDir) {
                    int lastPosition = builder.input.toString().lastIndexOf(".");
                    if (lastPosition > 0) {
                        selection = lastPosition;
                    }

                }
                mEditText.setSelection(0, selection);
                if (!builder.inputAllowEmpty && Utils.isEmpty(builder.input)) {
                    mPositiveButton.setEnabled(false);
                }
                mEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        int length = s.toString().length();
                        if (length == 0 && !builder.inputAllowEmpty) {
                            mPositiveButton.setEnabled(false);
                        } else {
                            mPositiveButton.setEnabled(true);
                        }

                        if (null != builder.inputCallback && builder.alwaysCallInputCallback) {
                            builder.inputCallback.onInput(miDialog, s);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                mEditText.setVisibility(View.VISIBLE);
                mEditText.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mEditText.requestFocus();
                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
                        }
                    }
                }, 100);
            } else {
                mEditText.setVisibility(View.GONE);
                mInputLayout.setVisibility(View.VISIBLE);
                mUserNameView.setText(builder.userName);
                mEditSafemm.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mEditSafemm.requestFocus();
                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.showSoftInput(mEditSafemm, InputMethodManager.SHOW_IMPLICIT);
                        }
                    }
                }, 100);
            }
        } else {
            mEditText.setVisibility(View.GONE);
        }

        //init checkbox
        RelativeLayout mCheckLayout = view.findViewById(R.id.layout_check);
        if (builder.showCheckBox) {
            mCheckLayout.setVisibility(View.VISIBLE);
        } else {
            mCheckLayout.setVisibility(View.GONE);
        }

        CheckBox mCheckBox = view.findViewById(R.id.cb_select);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (null != builder.checkBoxCallback) {
                    builder.checkBoxCallback.onCheckBoxChange(miDialog, isChecked);
                }
            }
        });


        //init listView
        LinearLayout mListLayout = view.findViewById(R.id.layout_list);
        TextView lineTv = view.findViewById(R.id.recycler_view_line);
        if (null != builder.itemsList) {
            if (builder.title != null) {
                lineTv.setVisibility(View.VISIBLE);
            }else {
                lineTv.setVisibility(View.GONE);
            }
            RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTitleView.getLayoutParams();
            lp.topMargin = 0;
            lp.height = (int) context.getResources().getDimension(R.dimen.mi_dialog_list_item_height);
            lp.gravity = Gravity.CENTER_VERTICAL;
            mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                    .color(builder.dividerColor).sizeResId(R.dimen.mi_dialog_list_divider_height)
                    .showLastDivider().build());
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            mRecyclerView.setLayoutManager(layoutManager);
            final ListAdapter adapter = new ListAdapter(builder.context, builder.itemsList, builder);
            if (builder.listSelectedPos > 0) {
                int selectedColor = builder.itemsList.get(builder.listSelectedPos).getTextColor();
                int unSelectedColor = builder.itemsList.get(builder.listSelectedPos - 1).getTextColor();
                adapter.initColor(selectedColor, unSelectedColor);
            } else if (builder.listSelectedPos == 0 && builder.itemsList.size() > 1) {
                int selectedColor = builder.itemsList.get(builder.listSelectedPos).getTextColor();
                int unSelectedColor = builder.itemsList.get(builder.listSelectedPos + 1).getTextColor();
                adapter.initColor(selectedColor, unSelectedColor);
            }
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    if (null != builder.listCallback) {
                        builder.listSelectedPos = i;
                        adapter.notifyDataSetChanged();
                        builder.listCallback.onSelection(miDialog, view, i, adapter.getItem(i).getDisplayValue());
                    }
                }
            });
            adapter.notifyDataSetChanged();
        } else {
            mListLayout.setVisibility(View.GONE);
        }

        Dialog mDialog = new Dialog(builder.context, R.style.MiDialogTheme);
        mDialog.setContentView(view);
        // set fill parent

        mDialog.setCancelable(builder.cancelable);
        mDialog.setCanceledOnTouchOutside(builder.touchCancelable);
        mDialog.getWindow().setGravity(Gravity.BOTTOM);

        mDialog.setOnShowListener(builder.showListener);
        mDialog.setOnDismissListener(builder.dismissListener);
        mDialog.setOnCancelListener(builder.cancelListener);
        mDialog.setOnKeyListener(builder.keyListener);
        mDialog.show();

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        view.setLayoutParams(layoutParams);

        return mDialog;
    }

    private static class ListAdapter extends BaseQuickAdapter<ListItemGenerator, BaseViewHolder> {
        private Context context;
        MiDialog.Builder builder;
        int mSelecdColor = R.color.orange;
        int mUnSelecdColor = R.color.color_text_black;

        public ListAdapter(Context context, List<ListItemGenerator> data, MiDialog.Builder builder) {
            super(data.get(0).getDisplayValueInfo() == null ?
                    R.layout.layout_item_dialog_list : R.layout.layout_item_dialog_list_new, data);
            this.context = context;
            this.builder = builder;
        }

        public void initColor(int selectedColor, int unSelecdColor) {
            mSelecdColor = selectedColor;
            mUnSelecdColor = unSelecdColor;
        }

        @Override
        protected void convert(BaseViewHolder helper, ListItemGenerator s) {
            TextView textView = helper.getView(R.id.txt_title);
            TextView textViewInfo = helper.getView(R.id.txt_title_info);
            ImageView intoIV = helper.getView(R.id.icon_into);
            intoIV.setEnabled(s.isClickable());
            if (s.getDisplayValueInfo() == null) {
                textView.setGravity(Gravity.CENTER_VERTICAL | builder.itemsGravity.getGravity());
                textView.setTextSize(16);
            }
            textView.setText(s.getDisplayValue());
            textView.setEnabled(s.isClickable());
            textViewInfo.setText(s.getDisplayValueInfo());
            if (Utils.isEmpty(s.getDisplayValueInfo())) {
                textViewInfo.setVisibility(View.GONE);
            }
            if (!s.isClickable()) {
                textView.setOnClickListener(null);
                intoIV.setOnClickListener(null);
            }
            if (s.isShowRightBtn()) {
                helper.setGone(R.id.icon_into, false);
            } else {
                helper.setGone(R.id.icon_into, true);
            }
            int pisiton = helper.getAdapterPosition();
            if (pisiton == 0 && Utils.isEmpty(builder.title)) {
                helper.setBackgroundResource(R.id.layout_root, R.drawable.selector_bg_mi_dialog);
            } else {
                helper.setBackgroundResource(R.id.layout_root, R.drawable.selector_bg_list_item);
            }

            if (builder.listSelectedPos >= 0) {
                if (builder.listSelectedPos == helper.getAdapterPosition()) {
                    textView.setTextColor(context.getResources().getColorStateList(mSelecdColor));
                    textViewInfo.setTextColor(context.getResources().getColorStateList(mSelecdColor));
                } else {
                    textView.setTextColor(context.getResources().getColorStateList(mUnSelecdColor));
                    textViewInfo.setTextColor(context.getResources().getColorStateList(mUnSelecdColor));
                }
            } else {
                textView.setTextColor(context.getResources().getColorStateList(s.getTextColor()));
            }
        }
    }
}
