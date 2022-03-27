package com.snakat.mvp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class FragmentBase extends Fragment implements Contract.View {

    private ActivityBase mActivityBase;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUp(view);
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
    public void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK) {
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

    protected abstract void setUp(View view);
}
