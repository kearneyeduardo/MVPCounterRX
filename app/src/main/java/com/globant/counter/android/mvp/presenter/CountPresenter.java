package com.globant.counter.android.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.globant.counter.android.util.bus.observers.AddButtonObserver;
import com.globant.counter.android.util.bus.observers.CountButtonUpBusObserver;
import com.globant.counter.android.util.bus.observers.ResetButtonObserver;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.mvp.model.CountModel;
import com.globant.counter.android.mvp.view.CountView;

public class CountPresenter {

    private CountModel model;
    private CountView view;

    public CountPresenter(CountModel model, CountView view) {
        this.model = model;
        this.view = view;
    }

    public void onCountButtonPressed() {
        model.inc();
        view.setCount(String.valueOf(model.getCount()));
    }

    public void onResetButtonPressed() {
        model.reset();
        view.setCount(String.valueOf(model.getCount()));
        view.setResult("0");
    }

    public void onAddButtonPressed() {
        if(view.validateFields()) {
            view.setResult(String.valueOf(model.getResult(view.getFirstValue(), view.getSecondValue())));
        } else {
            view.setResult("0");
        }
        InputMethodManager imm = (InputMethodManager)view.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new CountButtonUpBusObserver() {
            @Override
            public void onEvent(CountButtonUpBusObserver.CountButtonUp value) {
                onCountButtonPressed();
            }
        });

        RxBus.subscribe(activity, new ResetButtonObserver() {
            @Override
            public void onEvent(ResetButtonPressed value) {
                onResetButtonPressed();
            }
        });

        RxBus.subscribe(activity, new AddButtonObserver() {
            @Override
            public void onEvent(AddButtonPressed value) {
                onAddButtonPressed();
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}
