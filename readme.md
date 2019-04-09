### For creating bridge component
npm install --save react-native-create-bridge
write in console "react-native new-module"
write name your module
chouse type your module UI/module
chouse languages for ios/android
chouse folder when they must by located

add to android/app/src/main/java/com/nameProj/mainApplication.java
...
 @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new YourModuleNamePackage()
      );
    }
...
##############################   UI example    ##############################
add to android/app/src/main/java/com/nameProj/mainApplication.java
...
 @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new ExampleBridgeManager()
      );
    }
...

## android/app/src/main/java/com/nameProj/examplebridge/ExampleBridgeManager.java
package com.testbridge.examplebridge;

import android.view.View;
import android.widget.TextView;
import android.graphics.Color;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ExampleBridgeManager extends SimpleViewManager<View> {
    public static final String REACT_CLASS = "ExampleBridge";

    private ThemedReactContext mContext;
    private TextView view;
    private String textItTextView;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public View createViewInstance(ThemedReactContext context){
        mContext = context;
        view = new TextView(context);
        view.setText("blablabla");
        view.setTextColor(Color.GREEN);
        view.setBackgroundColor(Color.BLUE);
        return view;
    }

    @ReactProp(name = "exampleProp")
    public void setExampleProp(TextView view, String prop) {
        view.setText(prop);  
    }
}

## exampleBridgeJavaUI/ExampleBridgeNativeView.js
import React, { Component } from 'react'
import { requireNativeComponent } from 'react-native'
import PropTypes from 'prop-types'

const ExampleBridge = requireNativeComponent('ExampleBridge', ExampleBridgeView)

export default class ExampleBridgeView extends Component {
  render() {
    return <ExampleBridge {...this.props} />
  }
}

ExampleBridgeView.propTypes = {
  exampleProp: PropTypes.string
}


## App.js

import React, { Component } from 'react';
import { Platform, StyleSheet, View, NativeModules, TextInput, Text, TouchableWithoutFeedback } from 'react-native';
import ExampleBridgeView from './exampleBridgeJavaUI/ExampleBridgeNativeView';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: 'test',
    }
  }

  render() {
    const { text } = this.state;
    return (
      <View style={styles.container}>
        <TextInput
          style={{ width: 200, height: 50, marginVertical: 50 }}
          placeholder="Write text here"
          onChangeText={(text) => { this.setState({ text }); this.setTestText(text) }}
        />
        <ExampleBridgeView style={{ width: 200, height: 200 }} exampleProp={text} />   
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

###########################   ui component must working   #################################
###########################################################################################
###########################   example module component    #################################
add to android/app/src/main/java/com/nameProj/mainApplication.java
...
 @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new NativeComponentPackage()
      );
    }
...
## android/app/src/main/java/com/nameProj/nativecomponent/NativeComponentModule.java

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
## nativeComponent/NativeComponentNativeModule.js

//  Created by react-native-create-bridge

import { NativeModules, NativeEventEmitter } from 'react-native'

const { NativeComponent } = NativeModules;
const NativeComponentEventEmitter = new NativeEventEmitter(NativeComponent);

export default {
  setTestText(data) {
    return NativeComponent.setTestText(data)
  },
  getTestText() {
    return NativeComponent.getTestText()
  },

  emitter: NativeComponentEventEmitter,

  EXAMPLE_CONSTANT: NativeComponent.EXAMPLE_CONSTANT
}
## nativeComponent/NativeComponentNativeView.js

//  Created by react-native-create-bridge

import React, { Component } from 'react'
import { requireNativeComponent } from 'react-native'

const NativeComponent = requireNativeComponent('NativeComponent', NativeComponentView)

export default class NativeComponentView extends Component {
  render () {
    return <NativeComponent {...this.props} />
  }
}

NativeComponentView.propTypes = {
  // exampleProp: React.PropTypes.any
}

## App.js

import React, { Component } from 'react';
import { Platform, StyleSheet, View, NativeModules, TextInput, Text, TouchableWithoutFeedback } from 'react-native';
import ExampleBridgeView from './exampleBridgeJavaUI/ExampleBridgeNativeView';
import NativeComponent from './nativeComponent/NativeComponentNativeModule';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: 'азазаз',
      helloWorldText: 'hello world',
    }
  }

  componentWillMount() {
    NativeComponent.emitter.addListener('WTSE', ({ ololo }) => {
      this.setState({ helloWorldText: ololo });
    })
  }

  componentWillUnmount() {
    NativeComponent.emitter.remove();
  }

  onPress = () => {
    const data = NativeComponent.getTestText();
    this.setState({ helloWorldText: data });
  }

  setTestText = (data) => {
    NativeComponent.setTestText(data)
  }

  render() {
    const { text } = this.state;
    return (
      <View style={styles.container}>
        <TextInput
          style={{ width: 200, height: 50, marginVertical: 50 }}
          placeholder="Введи текст что бы отобразить его снизу"
          onChangeText={(text) => { this.setState({ text }); this.setTestText(text) }}
        />
        <Text>{text}</Text>
        <ExampleBridgeView style={{ width: 200, height: 200 }} exampleProp={text} />

        <TouchableWithoutFeedback onPress={this.onPress}>
          <Text>HELLO WORLD BTN</Text>
        </TouchableWithoutFeedback>
        
        <Text>{this.state.helloWorldText}</Text>

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

###########################   module component must working   #################################
###############################################################################################