package com.snakat.mvp;

import android.content.DialogInterface;

public interface Contract {
    interface View {

        void showLoading();

        void hideLoading();

        void showAlert(String title, String message, String okText);
        void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK);
        void showAlert(String title, String message, String okText, DialogInterface.OnClickListener onClickOK, String cancelText, DialogInterface.OnClickListener onClickCancel);

        void showToast(String message);
    }

    interface DialogView extends View {
        void dismissDialog(String tag);
    }

    interface Presenter<V extends View> {

        void onAttach(V view);

        void onDetach();
    }
}
