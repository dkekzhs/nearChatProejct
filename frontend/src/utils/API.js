const API = ID => {
  const getID = fetch('http://127.0.0.1:8080' + '/user/sign-in', {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify({
      deviceId: ID,
      name: '33333333313122123313',
    }),
  })
    .then(response => response.json())
    .then(json => {
      console.log(json);
    })
    .catch(err => {
      console.log(err);
    });
  return getID;
};

export default API;
