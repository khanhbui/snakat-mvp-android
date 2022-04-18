package com.snakat.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class PresenterBase<V extends Contract.View> implements Contract.Presenter<V> {

    private final CompositeDisposable mCompositeDisposable;

    private V mView;

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

    protected V getView() {
        return mView;
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
