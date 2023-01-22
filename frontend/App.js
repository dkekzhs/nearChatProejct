/* eslint-disable no-unused-vars */
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import * as React from 'react';
import {useState, useEffect} from 'react';
import type {Node} from 'react';
import {StyleSheet, Text, View, Button, SafeAreaView} from 'react-native';
import {Platform} from 'react-native';
import {NavigationContainer} from '@react-navigation/native'; // 네비게이션 컨테이너
import StackNavigation from '~/components/navigations/StackNavigation';
import SplashScreen from 'react-native-splash-screen'; /** 추가 **/
import LocationScreen from '~/screens/LocationScreen';
import API from '~/utils/API';
import SignInScreen from '~/screens/SignInScreen';
import getDeviceId from '~/utils/getDeviceId';

/* $FlowFixMe[missing-local-annot] The type annotation(s) required by Flow's
 * LTI update could not be added via codemod */

const App: () => Node = () => {
  return (
    <NavigationContainer>
      <StackNavigation />
    </NavigationContainer>
  );
};

export default App;
