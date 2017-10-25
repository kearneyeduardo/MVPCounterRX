package com.globant.counter.android.util.bus.observers;

public abstract class AddButtonObserver extends BusObserver<AddButtonObserver.AddButtonPressed> {
    public AddButtonObserver() {
        super(AddButtonPressed.class);
    }

    public static class AddButtonPressed {
    }
}
