fetch('http://localhost:8080/sml/prender').then(response => response.text())
  .then(texto => {
    console.log('Mensaje desde el servidor:', texto);
    // Hacer algo con los datos, como mostrarlos en la página web
    document.getElementById('mostrarCadena').textContent = texto;
    
  })
  .catch(error => {
    console.error('Error al obtener el mensaje:', error);
  });

  fetch('http://localhost:8080/sml/memoria').then(response => response.text())
  .then(testo => {
    console.log('Mensaje desde el servidor:', testo);
    // Hacer algo con los datos, como mostrarlos en la página web
     document.getElementById('memoriamostrar').innerText = testo;
  })
  .catch(error => {
    console.error('Error al obtener el mensaje:', error);
  });

  fetch('http://localhost:8080/sml/lista').then(response => response.text())
  .then(testo => {
    console.log('Mensaje desde el servidor:', testo);
    // Hacer algo con los datos, como mostrarlos en la página web
     
  })
  .catch(error => {
    console.error('Error al obtener el mensaje:', error);
  });

  ///////////////////////////////////////////////////////////

  // Función existente en tu archivo main.js
// Función para poblar la tabla con los procesos
function populateTable(procesos) {
  const tableBody = document.querySelector('#tablitaprocess tbody');
  
  // Limpiar la tabla
  tableBody.innerHTML = '';

  // Insertar cada proceso como una fila en la tabla
  procesos.forEach(proceso => {
    const row = document.createElement('tr');
    
    // Crear y agregar la celda para el nombre
    let cell = document.createElement('td');
    cell.textContent = proceso.nombre;
    row.appendChild(cell);

    // Crear y agregar la celda para la dirección inicial
    cell = document.createElement('td');
    cell.textContent = proceso.direIni;
    row.appendChild(cell);

    // Crear y agregar la celda para el tamaño
    cell = document.createElement('td');
    cell.textContent = proceso.tamanio;
    row.appendChild(cell);

    // Crear y agregar la celda para el PID
    cell = document.createElement('td');
    cell.textContent = proceso.pid;
    row.appendChild(cell);

    // Agregar la fila a la tabla
    tableBody.appendChild(row);
  });
}

// Función para obtener los datos de los procesos existentes
async function existingFetchRequest() {
  try {
    const response = await fetch('http://localhost:8080/sml/lista');
    if (!response.ok) {
      throw new Error('Error al obtener los datos de procesos');
    }
    const data = await response.json();
    populateTable(data); // Poblar la tabla con los datos obtenidos
  } catch (error) {
    console.error('Error:', error);
    alert('Hubo un error al obtener los datos de procesos');
  }
}

// Función para obtener los datos actualizados de los procesos
async function fetchData() {
  try {
    const response = await fetch('http://localhost:8080/sml/lista');
    if (!response.ok) {
      throw new Error('Error al obtener los datos de procesos');
    }
    const data = await response.json();
    populateTable(data); // Poblar la tabla con los datos obtenidos
  } catch (error) {
    console.error('Error:', error);
    alert('Hubo un error al obtener los datos de procesos');
  }
}

// Llama a las funciones para obtener los datos cuando el documento esté listo
document.addEventListener('DOMContentLoaded', () => {
  existingFetchRequest(); // Obtener los datos existentes al cargar la página
  
  // Actualizar los datos cada cierto intervalo de tiempo (por ejemplo, cada 5 segundos)
  setInterval(fetchData, 5000); // 5000 milisegundos = 5 segundos
});







///////////////////////
////////////////////
/////////////////


document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('processForm');
  
  form.addEventListener('submit', async (event) => {
      event.preventDefault();  // Previene el envío del formulario por defecto

      const pid = document.getElementById('pidpid').value;
      const tamanio = document.getElementById('tamaProce').value;

      try {
          await sendProcessData(pid, tamanio);
          alert('Proceso enviado correctamente');
      } catch (error) {
          console.error('Error al enviar los datos:', error);
          alert('Hubo un error al enviar los datos');
      }
  });
});

async function sendProcessData(pid, tamanio) {
  
  const apiUrl = 'http://localhost:8080/sml/crear';  // Actualiza esta URL con la correcta
  const data = { pid: parseInt(pid, 10), tam: parseInt(tamanio, 10) };
  alert(data);
  try {
      const response = await fetch(apiUrl, {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
      });

      if (!response.ok) {
          throw new Error('Error en la solicitud: ' + response.statusText);
      }

      console.log('Proceso creado correctamente');
  } catch (error) {
      throw new Error('Hubo un problema al enviar los datos: ' + error.message);
  }
}

//////
////
////
/////

document.addEventListener('DOMContentLoaded', () => {
  const formEliminar = document.getElementById('formEliminar');
  
  formEliminar.addEventListener('submit', async (event) => {
    event.preventDefault();  // Previene el envío del formulario por defecto
    
    const pid = document.getElementById('pidEliminar').value;

    try {
      await eliminarProceso(pid);
      alert('Proceso eliminado correctamente');
      fetchData();  // Actualiza la tabla después de eliminar el proceso
    } catch (error) {
      console.error('Error al eliminar el proceso:', error);
      alert('Hubo un error al eliminar el proceso');
    }
  });
});

async function eliminarProceso(pid) {
  const apiUrl = `http://localhost:8080/sml/eliminar/${pid}`;  // Endpoint para eliminar proceso
  const response = await fetch(apiUrl, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    }
  });

  if (!response.ok) {
    throw new Error('Error en la solicitud: ' + response.statusText);
  }
}
