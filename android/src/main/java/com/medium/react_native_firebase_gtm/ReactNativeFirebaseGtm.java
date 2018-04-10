package com.medium.react_native_firebase_gtm;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.*;

public class ReactNativeFirebaseGtm extends ReactContextBaseJavaModule{
    public ReactNativeFirebaseGtm(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void push(String name, ReadableMap parameters) {
        FirebaseAnalytics.getInstance(getReactApplicationContext()).logEvent(name, Arguments.toBundle(parameters));
    }

    @ReactMethod
    public void setUserProperty(String name, String value) {
        FirebaseAnalytics.getInstance(getReactApplicationContext()).setUserProperty(name, value);
    }

    @Override
    public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
      constants.put("kFIRParameterCurrency", Param.CURRENCY);
      constants.put("kFIREventViewItem", Event.VIEW_ITEM);
      constants.put("kFIREventBeginCheckout", Event.BEGIN_CHECKOUT);
      constants.put("kFIRParameterCheckoutStep", Param.CHECKOUT_STEP);
      constants.put("kFIREventEcommercePurchase", Event.ECOMMERCE_PURCHASE);
      constants.put("kFIREventAddToCart", Event.ADD_TO_CART);
      constants.put("kFIREventRemoveFromCart", Event.REMOVE_FROM_CART);
      constants.put("kFIREventViewSearchResults", Event.VIEW_SEARCH_RESULTS);
      constants.put("kFIREventSelectContent", Event.SELECT_CONTENT);
      return constants;
    }
    
    @Override
    public String getName() {
        return "ReactNativeFirebaseGtm";
    }
}
