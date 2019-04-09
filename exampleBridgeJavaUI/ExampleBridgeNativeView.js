//  Created by react-native-create-bridge

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
