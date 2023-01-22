import * as React from 'react';
import {useState, useEffect} from 'react';
import Splash from 'react-native-splash-screen'; /** 추가 **/
import API from '~/utils/API';
import getDeviceId from '~/utils/getDeviceId';
import {StyleSheet, Text, View, Button, SafeAreaView} from 'react-native';
import LocationScreen from '~/screens/LocationScreen';
import SignInScreen from '~/screens/SignInScreen';

const SplashScreen = ({navigation}) => {
  const [loginCheck, setLoginCheck] = useState('');
  const [code, setCode] = useState(false);
  const device = getDeviceId();
  console.log(device);
  useEffect(() => {
    try {
      setTimeout(() => {
        setLoginCheck(API(device._j).status);
        if (loginCheck === 400 || loginCheck === 404 || loginCheck === 500) {
          setCode(true);
        } else {
          setCode(false);
        }
        Splash.hide(); /** 추가 **/
      }, 2000); /** 스플래시 시간 조절 (2초) **/
    } catch (e) {
      console.warn('에러발생');
      console.warn(e);
    }
  }, []);
  return code ? <SignInScreen /> : <LocationScreen />;
};

export default SplashScreen;
