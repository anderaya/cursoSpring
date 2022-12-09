// Call the dataTables jQuery plugin
$(document).ready(function() {
	
  
});

async function iniciarSesion(){
	let datos={};
	datos.email= document.getElementById('exampleInputEmail').value;
	datos.password= document.getElementById('exampleInputPassword').value;
	
	const peticion = await fetch('http://localhost:8080/login', {
	  method: 'POST',
	  body: JSON.stringify(datos),
	  headers: {
	  	'Content-Type': 'application/json',
	  	'Accept': 'application/json'
	  },
	  mode: 'cors',
	  cache: 'default',
	});
	
	const respuesta= await peticion.json();
	console.log(respuesta)
	if(respuesta.code===200){
		localStorage.token=respuesta.mensaje;
		localStorage.email=datos.email;
		window.location.href='usuarios.html';
	}else{
		alert('usuario o contraseña incorrectos');
	}
	
}

