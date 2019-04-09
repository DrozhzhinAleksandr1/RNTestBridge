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
