package com.medium.react_native_firebase_gtm;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.google.firebase.analytics.FirebaseAnalytics;

public class ReactNativeFirebaseGtm extends ReactContextBaseJavaModule{
    public ReactNativeFirebaseGtm(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void push(String name, ReadableMap parameters) {
        FirebaseAnalytics.getInstance(getReactApplicationContext()).logEvent(name, Arguments.toBundle(parameters));
    }

    @Override
    public String getName() {
        return "ReactNativeFirebaseGtm";
    }
}
