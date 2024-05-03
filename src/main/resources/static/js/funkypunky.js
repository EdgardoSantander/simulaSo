function miFuncion() {
    // Código para manejar el evento del botón
    alert('¡Se hizo clic en el botón!');
}

// Obtener el botón por su ID
var boton = document.getElementById('boton-izquierdo');

// Agregar un "escuchador de eventos" para el evento de clic al botón
boton.addEventListener('click', miFuncion);
var botonderecho = document.getElementById('boton-derecho');
botonderecho.addEventListener('click',abrirNuevaVentana);
function abrirNuevaVentana() {
    // Abrir una nueva ventana con un tamaño específico
    window.open('datos.html', '_blank', 'width=820,height=800');

}

var botonFormu = document.getElementById('btn-regis');

botonFormu.addEventListener('click',guardarDatos);

function guardarDatos(){
    
}