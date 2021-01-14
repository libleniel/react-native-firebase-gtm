"use strict";

import { NativeModules, Platform } from 'react-native';

const RCTFirebaseCrashlytics = NativeModules.ReactNativeFirebaseCrashlytics;
const RCTFirebaseGtm = NativeModules.ReactNativeFirebaseGtm;
const RCTFirebaseRemoteConfig = NativeModules.ReactNativeFirebaseRemoteConfig;

export const ReactNativeFirebaseCrashlytics = {
    logMessage: function(message, params = {}) {
        if (Platform.OS == 'ios') {
            return RCTFirebaseCrashlytics.logMessage(message, params)
        } else {
            return RCTFirebaseCrashlytics.logMessage(message)
        }
        
    },
    logNonFatalError: function(error, code, domain) {
        return RCTFirebaseCrashlytics.logNonFatalError(error, code, domain)
    },
    setUserId: function(uid) {
        return RCTFirebaseCrashlytics.setUserId(uid)
    },
    setCustomValue: function(value) {
        return RCTFirebaseCrashlytics.setCustomValue(value)
    }
}

export const ReactNativeFirebaseGtm = {
    /**
     * @param {String}json The json object to process
     */
    push: function (eventName, json) {
        return RCTFirebaseGtm.push(eventName, json);
    },

    /**
     * Set user property to Gtm
     * Note that:
     *  - up to 25 user property may be set
     *  - name and value are limited to max 24 and 36 chars
     * @see: https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics
     *
     * @param {string} name - length: 1~24 chars
     * @param {string} value - length: 1~36 chars
     */
    setUserProperty: function(name, value='') {
        // Convert value to string.
        //   In iOS, non-string such as NSNumber cannot implicitly be converted to NSString 
        value = '' + value;

    	return RCTFirebaseGtm.setUserProperty(name, value);
    },
    kFIRConstant: {
        kFIREventViewItem: RCTFirebaseGtm.kFIREventViewItem,
        kFIREventBeginCheckout: RCTFirebaseGtm.kFIREventBeginCheckout,
        kFIREventEcommercePurchase: RCTFirebaseGtm.kFIREventEcommercePurchase,
        kFIREventAddToCart: RCTFirebaseGtm.kFIREventAddToCart,
        kFIREventRemoveFromCart: RCTFirebaseGtm.kFIREventRemoveFromCart,
        kFIREventViewSearchResults: RCTFirebaseGtm.kFIREventViewSearchResults,
        kFIREventSelectContent: RCTFirebaseGtm.kFIREventSelectContent,
        kFIRParameterCurrency: RCTFirebaseGtm.kFIRParameterCurrency,
        kFIRParameterCheckoutStep: RCTFirebaseGtm.kFIRParameterCheckoutStep,
        kFIRParameterItemList: RCTFirebaseGtm.kFIRParameterItemList,
        kFIRParameterItemID: RCTFirebaseGtm.kFIRParameterItemID,
        kFIRParameterItemName: RCTFirebaseGtm.kFIRParameterItemName,
        kFIRParameterItemCategory: RCTFirebaseGtm.kFIRParameterItemCategory,
        kFIRParameterItemBrand: RCTFirebaseGtm.kFIRParameterItemBrand,
        kFIRParameterPrice: RCTFirebaseGtm.kFIRParameterPrice,
        kFIRParameterIndex: RCTFirebaseGtm.kFIRParameterIndex,
        kFIRParameterQuantity: RCTFirebaseGtm.kFIRParameterQuantity,
        kFIRParameterTransactionID: RCTFirebaseGtm.kFIRParameterTransactionID,
        kFIRParameterValue: RCTFirebaseGtm.kFIRParameterValue,
        kFIRParameterShipping: RCTFirebaseGtm.kFIRParameterShipping,
        kFIRParameterCoupon: RCTFirebaseGtm.kFIRParameterCoupon,
    },
}

export const ReactNativeFirebaseRemoteConfig = {
    /**
     * Tell Firebase to fetch new/cached values from Firebase Remote Config,
     * 
     * @param {number} cacheTime - cache time in seconds, default: 43200, which is 12 hours
     */
    fetchRemoteConfig: function(cacheTime=43200) {
        RCTFirebaseRemoteConfig.fetchRemoteConfig(cacheTime);
    },

    setDefaults: function(defaults={}) {
        RCTFirebaseRemoteConfig.setDefaults(defaults);
    },

    /**
     * Async function to return the remote config value (as String) for given key

     * @param {string} key - The name of the key
     * @return {string} the value fetched in String
     */
    getStringAsync: function(key) {
        return new Promise((resolve) => {
            RCTFirebaseRemoteConfig.getString(key, (value) => {
                resolve(value);
            });
        });
    },

    /**
     * Async function to return the remote config value (as Boolean) for given key

     * @param {string} key - The name of the key
     * @return {boolean} the value fetched in Boolean
     */
    getBooleanAsync: function(key) {
        return new Promise((resolve) => {
            RCTFirebaseRemoteConfig.getBoolean(key, (value) => {
                resolve(value);
            });
        });
    },
}
