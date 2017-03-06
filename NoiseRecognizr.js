'use strict';

import React from 'react-native';

const ReactNativeClapper = React.NativeModules.ReactNativeClapper;

export default {
  reactNativeClapper: (onResponse, threshold, sensitivity) => {
    return ReactNativeClapper.reactNativeClapper(onResponse, threshold, sensitivity);
  },
}
