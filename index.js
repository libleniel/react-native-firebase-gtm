"use strict";

import { NativeModules } from 'react-native';

const RCTFirebaseGtm = NativeModules.ReactNativeFirebaseGtm;

const ReactNativeFirebaseGtm = {
    /**
     * @param {String}json The json object to process
     */
    push: function (eventName, json) {
        return RCTFirebaseGtm.push(eventName, json);
    }

}

module.exports = ReactNativeFirebaseGtm;
