"use strict";

import { NativeModules } from 'react-native';

const RCTFirebaseGtm = NativeModules.ReactNativeFirebaseGtm;

const ReactNativeFirebaseGtm = {
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
    }
}

module.exports = ReactNativeFirebaseGtm;
