package com.snakat.mvp;

import androidx.annotation.StringRes;

public interface Contract {
    interface View {

        void showLoading();

        void hideLoading();

        void showAlert(String title, String message, String ok);

        void showAlert(@StringRes int titleId, @StringRes int messageId, @StringRes int okId);
    }

    interface DialogView extends View {
        void dismissDialog(String tag);
    }

    interface Presenter<V extends View> {

        void onAttach(V view);

        void onDetach();
    }
}
