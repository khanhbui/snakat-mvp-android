package com.snakat.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
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
}
