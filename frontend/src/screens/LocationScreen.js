/* eslint-disable no-unused-vars */
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow s
 * trict-local
 */
import React, {useState, useEffect} from 'react';
import type {Node} from 'react';
import {
  StyleSheet,
  Text,
  View,
  Button,
  SafeAreaView,
  TextInput,
  TouchableOpacity,
} from 'react-native';
import DeviceInfo from 'react-native-device-info';
import Geolocation from 'react-native-geolocation-service';
import {Platform} from 'react-native';
import {
  check,
  PERMISSIONS,
  RESULTS,
  request,
  requestMultiple,
} from 'react-native-permissions';
import {getItemFromAsync, setItemToAsync} from '~/utils/asyncStorge';
import {TokenCheck, SignIn} from '~/utils/UserAPI';
async function requestPermission() {
  try {
    if (Platform.OS === 'ios') {
      return await Geolocation.requestAuthorization('always');
    }
    if (Platform.OS === 'android') {
      return await requestMultiple([
        PERMISSIONS.ANDROID.ACCESS_COARSE_LOCATION,
        PERMISSIONS.ANDROID.ACCESS_BACKGROUND_LOCATION,
        PERMISSIONS.ANDROID.ACCESS_COARSE_LOCATION,
      ]);
    }
  } catch (e) {
    console.log(e);
  }
}
const SignInCheck = (DeviceId, UserName) => {
  SignIn(DeviceId, UserName).then(res3 => {
    if (res3?.status === 200) {
      setItemToAsync('token', res3?.token);
    } else {
      return Error('토큰 값 없어요');
    }
  });
};
const LocationPage: () => Node = () => {
  const [DeviceId, setDeviceId] = useState('initialValue1');
  const [location, setLocation] = useState();
  const [radius, setRadius] = useState(3);
  getItemFromAsync('token').then(res => {
    TokenCheck(res).then(res => {
      if (res?.status !== 200) {
        SignInCheck(res?.deviceId, res?.name);
      }
    });
  });
  DeviceInfo.getUniqueId().then(uniqueId => {
    setDeviceId(uniqueId);
  });
  useEffect(() => {
    requestPermission().then(result => {
      if (
        result === 'granted' ||
        result['android.permission.ACCESS_BACKGROUND_LOCATION'] === 'granted'
      ) {
        Geolocation.watchPosition(
          pos => {
            console.log(pos);
            setLocation({lat: pos.coords.latitude, lng: pos.coords.longitude});
          },
          error => {
            console.log(error);
          },
          {enableHighAccuracy: true, timeout: 15000, maximumAge: 10000},
        );
      }
    });
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.one}>
        <Text style={styles.title}>{DeviceId}</Text>
        <Text>{location?.lat}</Text>
        <Text>{location?.lng}</Text>
      </View>
      <View style={styles.two}>
        <TextInput
          style={styles.textInput}
          placeholder="볼 채팅방 거리를 입력해주세요 단위 m"
          value={radius}
          onChangeText={setRadius}
        />
        <TouchableOpacity
          style={styles.signUpButton}
          onPress={() => {
            AccpetRadius(location, radius);
          }}>
          <Text style={styles.Text}>적용하기</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black',
  },
  title: {
    fontSize: 20,
    justifyContent: 'center',
  },
  one: {
    flex: 2,
    backgroundColor: 'yellow',
  },
  two: {
    flex: 3,
    backgroundColor: 'white',
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  signUpButton: {
    width: '20%',
    height: '10%',
    borderRadius: 15,
    borderWidth: 1,
    justifyContent: 'center',
  },
  textInput: {
    width: '80%',
    height: '10%',
    backgroundColor: '#d9d9d9',
    borderRadius: 10,
    fontSize: 20,
  },
});

export default LocationPage;
