package com.medium.react_native_firebase_gtm;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class ReactNativeFirebaseGtm extends ReactContextBaseJavaModule{
    private final String TAG = "firebase-gtm";

    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public ReactNativeFirebaseGtm(ReactApplicationContext reactContext) {
        super(reactContext);

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
    }

    @ReactMethod
    public void push(String name, ReadableMap parameters) {
        FirebaseAnalytics.getInstance(getReactApplicationContext()).logEvent(name, Arguments.toBundle(parameters));
    }

    @ReactMethod
    public void setUserProperty(String name, String value) {
        FirebaseAnalytics.getInstance(getReactApplicationContext()).setUserProperty(name, value);
    }

    /**
     * Fetch remote config value from Firebase
     * @param cacheTime cache time in seconds, default: 43200L, which is 12 hours
     */
    @ReactMethod
    public void fetchRemoteConfig(long cacheTime) {
        cacheTime = (cacheTime == 0 ? 43200L: cacheTime);
        mFirebaseRemoteConfig.fetch(cacheTime)
                .addOnCompleteListener(this.getCurrentActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                            Log.d(TAG, "Fetched remote config.");
                        } else {
                            Log.e(TAG, "Fetch remote config: failed.");
                        }
                    }
                });
    }

    /**
     * Return the remote config value for given key
     * @param key The name of the key
     */
    @ReactMethod
    public String getRemoteConfigValue(String key) {
        return mFirebaseRemoteConfig.getString(key);
    }

    @Override
    public String getName() {
        return "ReactNativeFirebaseGtm";
    }
}
