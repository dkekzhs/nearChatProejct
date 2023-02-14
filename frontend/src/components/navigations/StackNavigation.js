import React from 'react';
import {createNativeStackNavigator} from '@react-navigation/native-stack';

import SignInScreen from '~/screens/SignInScreen';
import MainScreen from './BottomNavigation';
const Stack = createNativeStackNavigator();

const StackNavigation = () => {
  return (
    <Stack.Navigator initialRouteName="signin">
      <Stack.Screen
        name="home"
        component={MainScreen}
        options={{headerShown: false}}
      />
      <Stack.Screen
        name="signin"
        component={SignInScreen}
        options={{headerShown: false}}
      />
    </Stack.Navigator>
  );
};

export default StackNavigation;

/* src/navigations/Stack.js */
