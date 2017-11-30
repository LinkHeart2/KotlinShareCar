package com.hjx.android.kotlinsharecar.view.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ProgressBar;

import com.hjx.android.kotlinsharecar.R;


/**
 * Created by Administrator on 2016/10/21.
 */

public class LoadingDialog {

    private Dialog mDialog;
    private Window window;
    private ProgressBar mProgressBar;

    public LoadingDialog(Activity activity) {
        initView(activity);
    }

    private void initView(Activity activity) {
        mDialog = new Dialog(activity, R.style.CustomDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        window = mDialog.getWindow();
        window.setContentView(R.layout.dialog_loading);
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
}
