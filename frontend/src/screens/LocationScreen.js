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
import {StyleSheet, Text, View, Button, SafeAreaView} from 'react-native';
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

/* $FlowFixMe[missing-local-annot] The type annotation(s) required by Flow's
 * LTI update could not be added via codemod */
const LocationPage: () => Node = () => {
  const [DeviceId, setDeviceId] = useState('initialValue1');
  const [location, setLocation] = useState();
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
    <View style={styles.container}>
      <Text style={styles.title}>{DeviceId}</Text>
      <Text>{location?.lat}</Text>
      <Text>{location?.lng}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#ffffff',
  },
  title: {
    fontSize: 30,
  },
});

export default LocationPage;
