export async function IdCheck(ID) {
  const response = await fetch('http://127.0.0.1:8080' + '/user/device-check', {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify({
      deviceId: ID,
    }),
  });
  return await response.json();
}
export async function SignUp(ID, NAME) {
  const response = await fetch('http://127.0.0.1:8080' + '/user/sign-up', {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify({
      deviceId: ID,
      name: NAME,
      role: 'USER',
    }),
  });
  return await response.json();
}
export async function SignIn(ID, NAME) {
  const response = await fetch('http://127.0.0.1:8080' + '/user/sign-in', {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify({
      deviceId: ID,
      name: NAME,
    }),
  });
  return await response.json();
}
export async function TokenCheck(token) {
  const response = await fetch('http://127.0.0.1:8080' + '/user/check', {
    method: 'GET',
    headers: {
      'Content-type': 'application/json',
      'Authorization': token,
    },
  });
  return await response.json();
}
