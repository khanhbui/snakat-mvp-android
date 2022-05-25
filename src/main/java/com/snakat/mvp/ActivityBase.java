package com.snakat.mvp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ActivityBase extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public final void showAlert(String title, String message, String okText)
    {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(okText, (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .show();
    }

    public final void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(okText, (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .show();
    }

    public final void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(okText, onClickOK)
                .setCancelable(false)
                .show();
    }

    public final void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText, DialogInterface.OnClickListener onClickOK) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(okText, onClickOK)
                .setCancelable(false)
                .show();
    }

    public final void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK, String cancelText, DialogInterface.OnClickListener onClickCancel) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(cancelText, onClickCancel)
                .setPositiveButton(okText, onClickOK)
                .setCancelable(false)
                .show();
    }

    public final void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText, DialogInterface.OnClickListener onClickOK, @StringRes int cancelText, DialogInterface.OnClickListener onClickCancel) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(cancelText, onClickCancel)
                .setPositiveButton(okText, onClickOK)
                .setCancelable(false)
                .show();
    }

    public final void showToast(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public final void showToast(@StringRes int message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public final void showLoading() {
        hideLoading();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
        if (mProgressDialog.getWindow() != null) {
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mProgressDialog.setContentView(R.layout.common_progress_dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public final void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public final void removeFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }
}
