export async function createRoom(token, NAME) {
  const response = await fetch('http://127.0.0.1:8080' + '/create_room', {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
      Authorization: token,
    },
    body: JSON.stringify({
      name: NAME,
    }),
  });
  return await response.json();
}
