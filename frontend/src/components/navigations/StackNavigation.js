import React from 'react';
import {createNativeStackNavigator} from '@react-navigation/native-stack';

import LocationScreen from '~/screens/LocationScreen';
import SignInScreen from '~/screens/SignInScreen';
import SplashScreen from '~/screens/SplashScreen';
const Stack = createNativeStackNavigator();

const StackNavigation = () => {
  return (
    <Stack.Navigator initialRouteName="Splash">
      <Stack.Screen
        name="Splash"
        component={SplashScreen}
        options={{headerShown: false}}
      />
      <Stack.Screen name="home" component={LocationScreen} />
      <Stack.Screen name="signin" component={SignInScreen} />
    </Stack.Navigator>
  );
};

export default StackNavigation;

/* src/navigations/Stack.js */
