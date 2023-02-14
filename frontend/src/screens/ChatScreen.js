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
import {
  StyleSheet,
  Text,
  View,
  Button,
  SafeAreaView,
  Alert,
  BackHandler,
  TextInput,
  TouchableOpacity,
} from 'react-native';
import {Platform} from 'react-native';

const ChatScreen = ({navigation}) => {
  const [mes, setMes] = useState(null);
  return (
    <SafeAreaView>
      <View>
        <TextInput placeholder="이름" value={mes} onChangeText={setMes} />
        <TouchableOpacity onPress={() => {}}>
          <Text>전송</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
};

export default ChatScreen;
