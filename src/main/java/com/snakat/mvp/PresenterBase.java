package com.snakat.mvp;

import io.reactivex.disposables.CompositeDisposable;

public abstract class PresenterBase<V extends Contract.View> implements Contract.Presenter<V> {

    protected final CompositeDisposable mCompositeDisposable;

    protected V mView;

    public PresenterBase() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mView = null;
    }
}
