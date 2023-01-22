import DeviceInfo from 'react-native-device-info';

const getDeviceId = () => {
  try {
    const data = DeviceInfo.getUniqueId();
    return data;
  } catch {
    console.log('error');
  }
};

export default getDeviceId;
