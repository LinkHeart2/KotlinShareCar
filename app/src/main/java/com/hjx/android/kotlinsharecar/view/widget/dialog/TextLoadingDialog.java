package com.hjx.android.kotlinsharecar.view.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hjx.android.kotlinsharecar.R;


/**
 * Created by hjx on 0019 7-19.
 * You can make it better
 */

public class TextLoadingDialog {
    private Dialog mDialog;
    private Window window;
    private ProgressBar mProgressBar;

    public TextLoadingDialog(Activity activity,String message) {
        initView(activity,message);
    }

    private void initView(Activity activity,String message) {
        mDialog = new Dialog(activity, R.style.CustomDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        window = mDialog.getWindow();
        window.setContentView(R.layout.dialog_text_loading);
        TextView tvMessage = (TextView) window.findViewById(R.id.message);
        tvMessage.setText(message);
        mProgressBar = (ProgressBar) window.findViewById(R.id.pb_loadingbar);
    }

    public void show() {
        mDialog.setCancelable(true);
        mDialog.show();
    }

    public void showUnCalcel() {
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void setCancelable(boolean cancelable) {
        mDialog.setCancelable(cancelable);
    }

    public boolean isShowing() {
        if (mDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }

    public void dissmiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void setMessage(String msg){
        window = mDialog.getWindow();
        TextView tvMessage = (TextView) window.findViewById(R.id.message);
        tvMessage.setText(msg);
    }
}
