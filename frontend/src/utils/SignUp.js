const SignUp = (ID, NAME) => {
  const SignUpData = fetch('http://127.0.0.1:8080' + '/user/sign-up', {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify({
      deviceId: ID,
      name: NAME,
      role: 'USER',
    }),
  })
    .then(response => response.json())
    .then(json => {
      console.log(json);
    })
    .catch(err => {
      console.log(err);
    });
  return SignUpData;
};

export default SignUp;
