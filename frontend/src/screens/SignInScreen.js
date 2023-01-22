import React from 'react';
import {useState, useEffect} from 'react';
import getDeviceId from '~/utils/getDeviceId';
import SignUp from '~/utils/SignUp';
import {
  View,
  Text,
  Pressable,
  Image,
  StyleSheet,
  SafeAreaView,
  TouchableOpacity,
  TextInput,
} from 'react-native';

const SignInScreen = () => {
  const [username, setUsername] = useState('');
  const [userDeviceId, setDeviceId] = useState('');
  useEffect(() => {
    setDeviceId(getDeviceId()._j);
  }, []);
  return (
    <SafeAreaView>
      <View style={styles.signInTextContainer}>
        <Text style={styles.signInText}>안녕하세요,</Text>
        <Text style={styles.signInText}>근거리채팅어플입니다.</Text>
        <Text style={styles.signInTextS}>
          서비스 이용을 위해 이름을 설정해주세요.
        </Text>
      </View>
      <View>
        <Text style={styles.signInText}>이름</Text>
        <TextInput
          style={styles.textInput}
          placeholder="이름"
          value={username}
          onChangeText={setUsername}
        />
      </View>
      <View style={styles.form}>
        <TouchableOpacity
          style={styles.signUpButton}
          onPress={() => {
            SignUp(userDeviceId, username);
          }}>
          <Text>시작하기</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
};
const styles = StyleSheet.create({
  signInTextContainer: {
    marginTop: '23%',
    marginLeft: '9%',
  },
  signInText: {
    fontSize: 25,
    fontWeight: '500',
    color: '#000000',
    lineHeight: 29.3,
  },
  signInTextS: {
    fontSize: 12,
    fontWeight: '300',
    color: '#000000',
    marginTop: 5,
    marginBottom: 50,
  },
  signUpButton: {
    width: '80%',
    alignItems: 'center',
    borderRadius: 15,
    borderWidth: 1,
    padding: 10,
    justifyContent: 'center',
  },
  form: {
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: '15%',
  },
  textInput: {
    width: '100%',
    height: 50,
    backgroundColor: '#d9d9d9',
    borderRadius: 10,
    fontSize: 20,
  },
});

export default SignInScreen;
