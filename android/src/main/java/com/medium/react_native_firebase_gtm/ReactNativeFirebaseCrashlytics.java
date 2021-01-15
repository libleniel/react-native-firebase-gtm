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
        
        switch (code) {
            case -1000:
                e = new ReebonzPaymentException(message, code, domain);
                break;
            case -999:
                e = new ReebonzDeeplinkException(message, code, domain);
                break;
            case -998:
                e = new ReebonzCodePushException(message, code, domain);
                break;
            case -997:
                e = new ReebonzAPIException(message, code, domain);
                break;
            case -996:
                e = new ReebonzNetworkException(message, code, domain);
                break;
            case -995:
                e = new ReebonzShareException(message, code, domain);
                break;
            default:
                e = new ReebonzTestingException(message, code, domain);
                break;
        }
        
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

class ReebonzException extends Exception {
    private String domain;
    private int code;

    public ReebonzException(String message, int code, String domain) {
        super(message);
        this.setCode(code);
        this.setDomain(domain);
    }

    public ReebonzException(String message, int code, String domain, Throwable cause) {
        super(message, cause);
        this.setCode(code);
        this.setDomain(domain);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}

class ReebonzPaymentException extends ReebonzException {
    public ReebonzPaymentException(String message, int code, String domain) {
        super(message, code, domain);
    }

    public ReebonzPaymentException(String message, int code, String domain, Throwable cause) {
        super(message, code, domain, cause);
    }
}

class ReebonzDeeplinkException extends ReebonzException {
    public ReebonzDeeplinkException(String message, int code, String domain) {
        super(message, code, domain);
    }

    public ReebonzDeeplinkException(String message, int code, String domain, Throwable cause) {
        super(message, code, domain, cause);
    }
}

class ReebonzCodePushException extends ReebonzException {
    public ReebonzCodePushException(String message, int code, String domain) {
        super(message, code, domain);
    }

    public ReebonzCodePushException(String message, int code, String domain, Throwable cause) {
        super(message, code, domain, cause);
    }
}

class ReebonzAPIException extends ReebonzException {
    public ReebonzAPIException(String message, int code, String domain) {
        super(message, code, domain);
    }

    public ReebonzAPIException(String message, int code, String domain, Throwable cause) {
        super(message, code, domain, cause);
    }
}

class ReebonzNetworkException extends ReebonzException {
    public ReebonzNetworkException(String message, int code, String domain) {
        super(message, code, domain);
    }

    public ReebonzNetworkException(String message, int code, String domain, Throwable cause) {
        super(message, code, domain, cause);
    }
}

class ReebonzShareException extends ReebonzException {
    public ReebonzShareException(String message, int code, String domain) {
        super(message, code, domain);
    }

    public ReebonzShareException(String message, int code, String domain, Throwable cause) {
        super(message, code, domain, cause);
    }
}

class ReebonzTestingException extends ReebonzException {
    public ReebonzTestingException(String message, int code, String domain) {
        super(message, code, domain);
    }

    public ReebonzTestingException(String message, int code, String domain, Throwable cause) {
        super(message, code, domain, cause);
    }
}