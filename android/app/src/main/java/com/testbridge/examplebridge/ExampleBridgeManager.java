//  Created by react-native-create-bridge

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
