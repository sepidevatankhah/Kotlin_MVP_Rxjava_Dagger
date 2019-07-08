package com.nwise.mvptemplate.ui.base;
import java.lang.ref.WeakReference;

/**
 * Created by Sepideh Vatankhah on 01.08.2019.
 * sun.vatankhah@gmail.com
 * https://github.com/sepidevatankhah
 */
public abstract class AbstractPresenter<V> implements Presenter<V> {
    WeakReference<V> view;

    @Override
    public void onViewAttached(V view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void onViewDetached() {
        if (view != null)
            view.clear();
    }

    @Override
    public void onDestroy() {
        if (view != null)
            view.clear();
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null && view.get() != null;
    }

    public V getView()
    {
        if(view!= null)
        {
            return view.get();
        }
        else return null;
    }
}
