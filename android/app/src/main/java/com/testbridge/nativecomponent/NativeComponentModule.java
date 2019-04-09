//  Created by react-native-create-bridge

package com.testbridge.nativecomponent;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

public class NativeComponentModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "NativeComponent";
    private static ReactApplicationContext reactContext = null;
    private String test = "null";

    public NativeComponentModule(ReactApplicationContext context) {
        // Pass in the context to the constructor and save it so you can emit events
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        super(context);

        reactContext = context;
    }

    @Override
    public String getName() {
        // Tell React the name of the module
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        return REACT_CLASS;
    }

    @Override
    public Map<String, Object> getConstants() {
        // Export any constants to be used in your native module
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        final Map<String, Object> constants = new HashMap<>();
        constants.put("EXAMPLE_CONSTANT", "example");

        return constants;
    }

    @ReactMethod
    public void getTestText () {
        // An example native method that you will expose to React
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        final WritableMap event = Arguments.createMap();
        event.putString("ololo", test);
        emitDeviceEvent("WTSE", event);
        // void putNull(@Nonnull String key);
        //   void putBoolean(@Nonnull String key, boolean value);
        //   void putDouble(@Nonnull String key, double value);
        //   void putInt(@Nonnull String key, int value);
        //   void putString(@Nonnull String key, @Nullable String value);
        //   void putArray(@Nonnull String key, @Nullable WritableArray value);
        //   void putMap(@Nonnull String key, @Nullable WritableMap value);
    }

    @ReactMethod
    public void setTestText (String text) {
        // An example native method that you will expose to React
        // https://facebook.github.io/react-native/docs/native-modules-android.html#the-toast-module
        test = text;
    }
    
    private static void emitDeviceEvent(String eventName, @Nullable WritableMap eventData) {
        // A method for emitting from the native side to JS
        // https://facebook.github.io/react-native/docs/native-modules-android.html#sending-events-to-javascript
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, eventData);
    }
}
