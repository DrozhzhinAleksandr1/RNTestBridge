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
