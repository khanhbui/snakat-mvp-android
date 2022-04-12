package com.snakat.mvp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;
import androidx.viewbinding.ViewBinding;

public abstract class DialogBase<B extends ViewBinding, P extends Contract.Presenter> extends DialogFragment implements Contract.View {

    protected ActivityBase mActivityBase;
    protected B mBinding;
    protected P mPresenter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = onCreateViewBinding(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = onCreatePresenter();
        mPresenter.onAttach(this);

        setupView(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivityBase = (ActivityBase) context;
    }

    @Override
    public void onDetach() {
        mActivityBase = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
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
    public void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(title, message, okText);
    }

    @Override
    public void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(title, message, okText, onClickOK);
    }

    @Override
    public void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText, DialogInterface.OnClickListener onClickOK) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(title, message, okText, onClickOK);
    }

    @Override
    public void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK, String cancelText, DialogInterface.OnClickListener onClickCancel) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(title, message, okText, onClickOK, cancelText, onClickCancel);
    }

    @Override
    public void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText, DialogInterface.OnClickListener onClickOK, @StringRes int cancelText, DialogInterface.OnClickListener onClickCancel) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(title, message, okText, onClickOK, cancelText, onClickCancel);
    }

    @Override
    public void showToast(String message) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showToast(message);
    }

    @Override
    public void showToast(@StringRes int message) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showToast(message);
    }

    @NonNull
    protected abstract P onCreatePresenter();

    @NonNull
    protected abstract B onCreateViewBinding(LayoutInflater inflater, ViewGroup container, boolean attachToParent);

    protected abstract void setupView(@NonNull View view);
}
