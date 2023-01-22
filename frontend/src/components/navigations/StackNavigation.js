import React from 'react';
import {createNativeStackNavigator} from '@react-navigation/native-stack';

import LocationScreen from '../../screens/LocationScreen';
import SignInScreen from '../../screens/SignInScreen';
const Stack = createNativeStackNavigator();

const StackNavigation = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="LocationScreen" component={LocationScreen} />
      <Stack.Screen name="SignInScreen" component={SignInScreen} />
    </Stack.Navigator>
  );
};

export default StackNavigation;

/* src/navigations/Stack.js */
