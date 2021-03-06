package com.eli.midialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

/**
 * @author liuzhu@eli-tech.com  2019/06/03
 */
public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialog_demo);

        Button button = $(R.id.button_confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();
            }
        });
        button = $(R.id.button_input, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        button = $(R.id.button_list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });
        button = $(R.id.button_confirm_input, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmInputDialog();
            }
        });
        button = $(R.id.button_modify_password, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showModifyDialog();
            }
        });

        button = $(R.id.button_confirm_lenovo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLenovoConfirmDialog();
            }
        });
        button = $(R.id.button_input_lenovo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLenovoInputDialog();
            }
        });
        button = $(R.id.button_list_lenovo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLenovoListDialog();
            }
        });
        button = $(R.id.button_confirm_input_lenovo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLenovoConfirmInputDialog();
            }
        });
        button = $(R.id.button_modify_password_lenovo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLenovoModifyDialog();
            }
        });
    }

    private void showConfirmDialog() {
        new MiDialog.Builder(this)
                .title("???????????????????????????")
                .content("????????????1080p????????????HD2018????????????")
                .prompt("???????????????MP4 ????????????1.29G")
                .positive("??????")
                .onPositive(new MiDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .negative("??????")
                .onNegative(new MiDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showInputDialog() {
        new MiDialog.Builder(this)
                .title("???????????????")
                .positive("??????")
                .negative("??????")
                .onNegative(new MiDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .input("?????????????????????", "", false, new MiDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MiDialog dialog, CharSequence text) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showListDialog() {
        new MiDialog.Builder(this)
                .title("??????????????????")
                .titleGravity(DialogGravity.LEFT)
                .items(new String[]{"111111", "2222222", "333333"})
                .itemsCallback(new MiDialog.ListCallback() {
                    @Override
                    public void onSelection(@NonNull MiDialog dialog, View v, int pos, CharSequence text) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                })
                .neutral("??????")
                .onNeutral(new MiDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showConfirmInputDialog() {
        new MiDialog.Builder(this)
                .title("????????????NAS???")
                .prompt("???????????????????????????")
                .positive("??????")
                .negative("??????")
                .onNegative(new MiDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .inputType(DialogInputType.PIN)
                .input("????????????", "", false, new MiDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MiDialog dialog, CharSequence text) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showModifyDialog() {
        new MiDialog.Builder(this)
                .title("????????????")
                .positive("??????")
                .negative("??????")
                .onNegative(new MiDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MiDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .inputType(DialogInputType.PASSWORD)
                .input("15156692598", new MiDialog.InputCallbacks() {
                    @Override
                    public void onInput(@NonNull MiDialog dialog, CharSequence text, CharSequence confirmText) {

                        if (text.toString().isEmpty() || confirmText.toString().isEmpty()) {
                            Toast.makeText(DemoActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (text.toString().equals(confirmText.toString())) {
                            dialog.dismiss();
                            Toast.makeText(DemoActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DemoActivity.this, "???????????????????????????", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .show();
    }


    private void showLenovoConfirmDialog() {
        new LenovoDialog.Builder(this)
                .title("???????????????????????????")
                .content("????????????1080p????????????HD2018????????????")
                .prompt("???????????????MP4 ????????????1.29G")
                .positive("??????")
                .onPositive(new LenovoDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull LenovoDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .negative("??????")
                .onNegative(new LenovoDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull LenovoDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
    private void showLenovoInputDialog() {
        new LenovoDialog.Builder(this)
                .title("???????????????")
                .positive("??????")
                .negative("??????")
                .onNegative(new LenovoDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull LenovoDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .input("?????????????????????", "", false, new LenovoDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull LenovoDialog dialog, CharSequence text) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showLenovoListDialog() {
        new LenovoDialog.Builder(this)
                .title("??????????????????")
                .titleGravity(DialogGravity.LEFT)
                .items(new String[]{"111111", "2222222", "333333"})
                .itemsCallback(new LenovoDialog.ListCallback() {
                    @Override
                    public void onSelection(@NonNull LenovoDialog dialog, View v, int pos, CharSequence text) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                })
                .neutral("??????")
                .onNeutral(new LenovoDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull LenovoDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showLenovoConfirmInputDialog() {
        new LenovoDialog.Builder(this)
                .title("????????????NAS???")
                .prompt("???????????????????????????")
                .positive("??????")
                .negative("??????")
                .onNegative(new LenovoDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull LenovoDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .inputType(DialogInputType.PIN)
                .input("????????????", "", false, new LenovoDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull LenovoDialog dialog, CharSequence text) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void showLenovoModifyDialog() {
        new LenovoDialog.Builder(this)
                .title("????????????")
                .positive("??????")
                .negative("??????")
                .onNegative(new LenovoDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull LenovoDialog dialog, @NonNull DialogAction action) {
                        dialog.dismiss();
                        Toast.makeText(DemoActivity.this, "??????", Toast.LENGTH_SHORT).show();
                    }
                })
                .inputType(DialogInputType.PASSWORD)
                .input("15156692598", new LenovoDialog.InputCallbacks() {
                    @Override
                    public void onInput(@NonNull LenovoDialog dialog, CharSequence text, CharSequence confirmText) {

                        if (text.toString().isEmpty() || confirmText.toString().isEmpty()) {
                            Toast.makeText(DemoActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (text.toString().equals(confirmText.toString())) {
                            dialog.dismiss();
                            Toast.makeText(DemoActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DemoActivity.this, "???????????????????????????", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .show();
    }
}
