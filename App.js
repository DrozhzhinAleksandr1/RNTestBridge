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
