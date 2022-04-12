package com.snakat.mvp;

import android.content.DialogInterface;

import androidx.annotation.StringRes;

public interface Contract {
    interface View {

        void showLoading();

        void hideLoading();

        void showAlert(String title, String message, String okText);
        void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText);

        void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK);
        void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText, DialogInterface.OnClickListener onClickOK);

        void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK, String cancelText, DialogInterface.OnClickListener onClickCancel);
        void showAlert(@StringRes int title, @StringRes int message, @StringRes int okText, DialogInterface.OnClickListener onClickOK, @StringRes int cancelText, DialogInterface.OnClickListener onClickCancel);

        void showToast(String message);
        void showToast(@StringRes int message);
    }

    interface Presenter<V extends View> {

        void onAttach(V view);

        void onDetach();
    }
}
