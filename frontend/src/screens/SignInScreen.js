import React from 'react';
import {useState, useEffect} from 'react';
import DeviceInfo from 'react-native-device-info';
import {
  View,
  Text,
  StyleSheet,
  SafeAreaView,
  TouchableOpacity,
  TextInput,
} from 'react-native';
import {IdCheck, SignUp, SignIn} from '~/utils/UserAPI';
import {isEmpty, getItemFromAsync, setItemToAsync} from '~/utils/asyncStorge';

const SignInScreen = ({navigation}) => {
  const [username, setUsername] = useState('');
  const [userDeviceId, setDeviceId] = useState('');
  const [SignUpData, setSignUpData] = useState('');

  useEffect(() => {
    DeviceInfo.getUniqueId().then(res => {
      setDeviceId(res);
      LoginCheck(res);
    });
    setTimeout(() => {}, 200);
  }, [SignUpData, userDeviceId]);
  const LoginCheck = res => {
    IdCheck(res).then(res2 => {
      if (res2?.status === 200 && !isEmpty('token')) {
        navigation.navigate('home');
      } else {
        navigation.navigate('signin');
      }
    });
  };
  const gotoMain = (userID, userName) => {
    SignCheck(userID, userName).then(res => {
      setSignUpData(res);
      console.log(SignUpData, userID, userName);
      if (res?.status === 200) {
        SignInCheck(userID, userName);
        navigation.navigate('home');
      } else {
        navigation.navigate('signin');
      }
    }); //회원가입 체크 성공적이면 토큰 발행
  };

  const SignCheck = async (DeviceId, UserName) => {
    const data = await SignUp(DeviceId, UserName);
    return data;
  };

  const SignInCheck = (DeviceId, UserName) => {
    SignIn(DeviceId, UserName).then(res3 => {
      if (res3?.status === 200) {
        setItemToAsync('token', res3?.token);
      } else {
        return Error('토큰 값 없어요');
      }
    });
  };
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
            gotoMain(userDeviceId, username);
          }}>
          <Text>시작하기</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.form}>
        <Text style={styles.signInTextError}>{SignUpData?.message}</Text>
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
  signInTextError: {
    fontSize: 15,
    fontWeight: '500',
    color: '#FF0000',
    lineHeight: 29.3,
    alignItems: 'center',
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
