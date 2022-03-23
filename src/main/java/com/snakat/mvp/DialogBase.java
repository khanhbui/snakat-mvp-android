package com.snakat.mvp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class DialogBase extends DialogFragment implements Contract.DialogView {

    private ActivityBase mActivityBase;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivityBase = (ActivityBase) context;
    }

    @Override
    public void onDetach() {
        mActivityBase = null;

        super.onDetach();
    }

    @Override
    public void showLoading() {
        if (mActivityBase != null) {
            mActivityBase.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivityBase != null) {
            mActivityBase.hideLoading();
        }
    }

    @Override
    public void showAlert(String title, String message, String ok) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(title, message, ok);
    }

    @Override
    public void showAlert(@StringRes int titleId, @StringRes int messageId, @StringRes int okId) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(titleId, messageId, okId);
    }

    protected abstract void setUp(View view);

    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        show(transaction, tag);
    }

    @Override
    public void dismissDialog(String tag) {
        dismiss();
    }
}
