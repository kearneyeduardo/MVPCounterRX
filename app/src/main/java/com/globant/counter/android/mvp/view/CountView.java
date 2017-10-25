package com.globant.counter.android.mvp.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.TextView;

import com.globant.counter.android.R;
import com.globant.counter.android.util.bus.observers.AddButtonObserver;
import com.globant.counter.android.util.bus.observers.CountButtonUpBusObserver;
import com.globant.counter.android.util.bus.observers.ResetButtonObserver;
import com.globant.counter.android.util.bus.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountView extends ActivityView {

    @BindView(R.id.count_label) TextView countLabel;
    @BindView(R.id.first_input) TextInputLayout firstNumberInputLayout;
    @BindView(R.id.second_input) TextInputLayout secondNumberInputLayout;
    @BindView(R.id.result_label) TextView resultLabel;

    public CountView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void setCount(String count) {
        countLabel.setText(count);
    }

    public void setResult(String count) {
        resultLabel.setText(count);
    }

    public int getFirstValue() {
        return Integer.parseInt(firstNumberInputLayout.getEditText().getText().toString());
    }

    public int getSecondValue() {
        return Integer.parseInt(secondNumberInputLayout.getEditText().getText().toString());
    }

    @OnClick(R.id.count_button)
    public void countButtonPressed() {
        RxBus.post(new CountButtonUpBusObserver.CountButtonUp());
    }

    @OnClick(R.id.reset_button)
    public void resetButtonPressed() {
        RxBus.post(new ResetButtonObserver.ResetButtonPressed());
    }

    @OnClick(R.id.add_button)
    public void add() {
        RxBus.post(new AddButtonObserver.AddButtonPressed());
    }

    public boolean validateFields() {
        firstNumberInputLayout.setError(null);
        secondNumberInputLayout.setError(null);
        boolean valid = true;
        if (TextUtils.isEmpty(firstNumberInputLayout.getEditText().getText().toString())) {
            firstNumberInputLayout.setError("Insert value");
            valid = false;
        }
        if (TextUtils.isEmpty(secondNumberInputLayout.getEditText().getText().toString())) {
            secondNumberInputLayout.setError("Insert value");
            valid = false;
        }
        return valid;
    }
}
