fetch('http://localhost:8080/sml').then(response => response.text())
  .then(texto => {
    console.log('Mensaje desde el servidor:', texto);
    // Hacer algo con los datos, como mostrarlos en la página web
    document.getElementById('mostrarCadena').textContent = texto;
  })
  .catch(error => {
    console.error('Error al obtener el mensaje:', error);
  });

