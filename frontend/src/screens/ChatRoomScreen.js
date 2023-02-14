import React from 'react';
import {useState, useEffect} from 'react';
import {
  View,
  Text,
  StyleSheet,
  SafeAreaView,
  TouchableOpacity,
  TextInput,
} from 'react-native';
import {isEmpty, getItemFromAsync, setItemToAsync} from '~/utils/asyncStorge';
import {createRoom} from '~/utils/ChatRoomApi';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

const SignInScreen = ({navigation}) => {
  const [socketTest, setSocket] = useState(null);

  const test = async () => {
    let result = await getItemFromAsync('token');
    setTimeout(() => {
      setSocket(result);
    }, 2000);
  };
  const create = async token => {
    let result = await createRoom(token, '123123123');
    console.log(result);
  };
  const createMessage = () => {
    console.log(socketTest);
    let socket = new WebSocket('ws://localhost:8080/ws', null, {
      headers: {Authorization: socketTest},
    });

    stompClient = Stomp.over(socket);
    stompClient.connect({Authorization: socketTest}, function (params) {
      console.log(params);
    });
  };
  useEffect(() => {
    test();
    if (socketTest === null) {
      console.log('아직 없어 토큰');
    } else {
      let socket = new WebSocket('ws://localhost:8080/ws', null, {
        headers: {Authorization: socketTest},
      });
      stompClient = Stomp.over(socket);
      stompClient.connect({Authorization: socketTest}, function (params) {
        console.log(params);
        stompClient.subscribe('/room/room/1', function (body) {
          console.log(body);
        });
      });
    }
  }, [socketTest]);
  return (
    <SafeAreaView>
      <View style={styles.signInTextContainer}>
        <Text style={styles.signInText}>안녕하세요 토큰 정보이에용</Text>
        <Text style={styles.signInText}>{socketTest}.</Text>
      </View>
      <View style={styles.form}>
        <TouchableOpacity
          style={styles.signUpButton}
          onPress={() => {
            create(socketTest);
          }}>
          <Text>방만들기</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.signUpButton}
          onPress={() => {
            createMessage();
          }}>
          <Text>메세지 보내기</Text>
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
