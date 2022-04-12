package com.snakat.mvp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class FragmentBase<B extends ViewBinding, P extends Contract.Presenter> extends Fragment implements Contract.View {

    protected ActivityBase mActivityBase;
    protected B mBinding;
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = onCreateViewBinding(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
        mActivityBase.showLoading();
    }

    @Override
    public void hideLoading() {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.hideLoading();
    }

    @Override
    public void showAlert(String title, String message, String okText) {
        if (mActivityBase == null) {
            return;
        }
        mActivityBase.showAlert(title, message, okText);
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
