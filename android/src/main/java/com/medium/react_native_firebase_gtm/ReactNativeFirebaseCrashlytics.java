package com.medium.react_native_firebase_gtm;

import android.util.Log;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import com.facebook.react.bridge.ReadableMap;
import java.util.Map;

public class ReactNativeFirebaseCrashlytics extends ReactContextBaseJavaModule {
    private FirebaseCrashlytics mFirebaseCrashlytics;

    public ReactNativeFirebaseCrashlytics(ReactApplicationContext reactContext) {
        super(reactContext);

        mFirebaseCrashlytics = FirebaseCrashlytics.getInstance();
    }

    /**
     *
     * @param message information about what error that we get
     * @param attributes extra information to let us know more
     */
    @ReactMethod
    public void logMessage(String message) {
        // Log.d("LOG MESSAGE", "ANDROID: " + message);

        mFirebaseCrashlytics.log(message);
    }

    /**
     *
     * @param message error message
     * @param code error code
     * @param domain error domain
     */
    @ReactMethod
    public void logNonFatalError(String message, int code, String domain) {
        // Log.d("LOG NON FATAL", "Message: " + message + " Code: " + code + " Domain: " + domain);

        ReebonzException e = new ReebonzException(message, code, domain);
        
        mFirebaseCrashlytics.recordException(e);
    }

    /**
     * Set user id for crash info
     * @param userId user id
     */
    @ReactMethod
    public void setUserId(String userId) {
        // Log.d("LOG SET USER ID", "UserID: " + userId);

        mFirebaseCrashlytics.setUserId(userId);
    }

    /**
     * Pass a map-like object as firebase crashlytics key
     * @param value javascript object
     */
    @ReactMethod
    public void setCustomValue(ReadableMap value) {
        Map<String, Object> mappedValue = ConversionUtils.toMap(value);

        // Log.d("LOG SET CUSTOM VALUE", "VALUE: " + mappedValue);

        for (Map.Entry<String, Object> entry : mappedValue.entrySet()) {
            // System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

            mFirebaseCrashlytics.setCustomKey(entry.getKey(), entry.getValue().toString());
        }
    }

    @Override
    public String getName() {
        return "ReactNativeFirebaseCrashlytics";
    }
}
