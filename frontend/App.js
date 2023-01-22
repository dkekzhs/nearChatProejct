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
import StackNavigation from './src/components/navigations/StackNavigation';
import SplashScreen from 'react-native-splash-screen'; /** 추가 **/
import LocationScreen from './src/screens/LocationScreen';
import API from './src/utils/API';
import SignInScreen from './src/screens/SignInScreen';
import getDeviceId from '~/utils/getDeviceId';

/* $FlowFixMe[missing-local-annot] The type annotation(s) required by Flow's
 * LTI update could not be added via codemod */

const App: () => Node = () => {
  const [DeviceId, setDeviceId] = useState('initialValue1');
  const [loginCheck, setLoginCheck] = useState('');
  const [code, setCode] = useState(false);
  useEffect(() => {
    try {
      setTimeout(() => {
        console.log(API(getDeviceId()._j));
        setLoginCheck(API(getDeviceId()._j).code);
        if (loginCheck === 'USER_NOT_FOUND' || loginCheck === 'undefined') {
          setCode(true);
        } else {
          setCode(false);
        }
        SplashScreen.hide(); /** 추가 **/
      }, 2000); /** 스플래시 시간 조절 (2초) **/
    } catch (e) {
      console.warn('에러발생');
      console.warn(e);
    }
  },[]);

  <NavigationContainer>
    <StackNavigation />
  </NavigationContainer>;
  return code ? <LocationScreen /> : <SignInScreen />;
};

export default App;
