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
import {
  StyleSheet,
  Text,
  View,
  Button,
  SafeAreaView,
  Alert,
  BackHandler,
} from 'react-native';
import {Platform} from 'react-native';
import {NavigationContainer} from '@react-navigation/native'; // 네비게이션 컨테이너
import StackNavigation from '~/components/navigations/StackNavigation';
import SplashScreen from 'react-native-splash-screen'; /** 추가 **/

/* $FlowFixMe[missing-local-annot] The type annotation(s) required by Flow's
 * LTI update could not be added via codemod */

const App: () => Node = () => {
  useEffect(() => {
    const backAction = () => {
      Alert.alert('Hold on!', '앱을 종료하시겠습니까?', [
        {
          text: '취소',
          onPress: () => null,
        },
        {text: '확인', onPress: () => BackHandler.exitApp()},
        ,
      ]);
      return true;
    };

    const backHandler = BackHandler.addEventListener(
      'hardwareBackPress',
      backAction,
    );

    return () => backHandler.remove();
  }, []);
  try {
    setTimeout(() => {
      SplashScreen.hide(); /** 추가 **/
    }, 2000);
  } catch (e) {
    console.warn('에러발생');
    console.warn(e);
  }
  return (
    <NavigationContainer>
      <StackNavigation />
    </NavigationContainer>
  );
};

export default App;
