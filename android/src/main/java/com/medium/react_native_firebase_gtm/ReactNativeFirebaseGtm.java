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
import android.os.Bundle;
import android.util.Log;

public class ReactNativeFirebaseGtm extends ReactContextBaseJavaModule{
    public ReactNativeFirebaseGtm(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void push(String name, ReadableMap parameters) {
        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle = Arguments.toBundle(parameters);

        //Need to correct some params, as toBundle function will convert Number to Double only on Android
        //Because we need Long on some params, we need to convert it manually from its bundle
        if (name.equals(Event.SELECT_CONTENT) || name.equals(Event.BEGIN_CHECKOUT)
            || name.equals(Event.ECOMMERCE_PURCHASE)) {
            ArrayList<Bundle> items = new ArrayList<Bundle>();
            items = ecommerceBundle.getParcelableArrayList("items");
            for (int i = 0; i < items.size(); i++) {
                try {
                    double idx = items.get(i).getDouble(Param.INDEX, -1);
                    if (idx != -1)
                        items.get(i).putLong(Param.INDEX, (long)idx);
                
                    double qty = items.get(i).getDouble(Param.QUANTITY, -1);
                    if (qty != -1)
                        items.get(i).putLong(Param.QUANTITY, (long)qty);
                    
                } catch(Exception e) {
                    Log.e("FIREBASEGTM", "push: " + e);
                }
            }
            ecommerceBundle.putParcelableArrayList("items", items);

            try {
                double step = ecommerceBundle.getDouble(Param.CHECKOUT_STEP, -1);
                if (step != -1)
                    ecommerceBundle.putLong(Param.CHECKOUT_STEP, (long)step);
            } catch(Exception e) {
                Log.e("FIREBASEGTM", "push: " + e);
            }
        }
        FirebaseAnalytics.getInstance(getReactApplicationContext()).logEvent(name, ecommerceBundle);
    }

    @ReactMethod
    public void setUserProperty(String name, String value) {
        FirebaseAnalytics.getInstance(getReactApplicationContext()).setUserProperty(name, value);
    }

    @Override
    public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
      constants.put("kFIREventViewItem", Event.VIEW_ITEM);
      constants.put("kFIREventBeginCheckout", Event.BEGIN_CHECKOUT);
      constants.put("kFIREventEcommercePurchase", Event.ECOMMERCE_PURCHASE);
      constants.put("kFIREventAddToCart", Event.ADD_TO_CART);
      constants.put("kFIREventRemoveFromCart", Event.REMOVE_FROM_CART);
      constants.put("kFIREventViewSearchResults", Event.VIEW_SEARCH_RESULTS);
      constants.put("kFIREventSelectContent", Event.SELECT_CONTENT);
      constants.put("kFIRParameterCurrency", Param.CURRENCY);
      constants.put("kFIRParameterCheckoutStep", Param.CHECKOUT_STEP);
      constants.put("kFIRParameterItemList", Param.ITEM_LIST);
      constants.put("kFIRParameterItemID", Param.ITEM_ID);
      constants.put("kFIRParameterItemName", Param.ITEM_NAME);
      constants.put("kFIRParameterItemCategory", Param.ITEM_CATEGORY);
      constants.put("kFIRParameterItemBrand", Param.ITEM_BRAND);
      constants.put("kFIRParameterPrice", Param.PRICE);
      constants.put("kFIRParameterIndex", Param.INDEX);
      constants.put("kFIRParameterQuantity", Param.QUANTITY);
      constants.put("kFIRParameterTransactionID", Param.TRANSACTION_ID);
      constants.put("kFIRParameterValue", Param.VALUE);
      constants.put("kFIRParameterShipping", Param.SHIPPING);
      constants.put("kFIRParameterCoupon", Param.COUPON);
      return constants;
    }
    
    @Override
    public String getName() {
        return "ReactNativeFirebaseGtm";
    }
}
