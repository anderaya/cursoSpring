// Call the dataTables jQuery plugin
$(document).ready(function() {

  
});

async function registrarUsuario(){
	console.log('entra')

	let datos={};
	datos.nombre= document.getElementById('exampleFirstName').value;
	datos.apellido= document.getElementById('exampleLastName').value;
	//datos.telefono= document.getElementById('exampleFirstName').value;
	datos.email= document.getElementById('exampleInputEmail').value;
	datos.password= document.getElementById('exampleInputPassword').value;
	datos.telefono=123456;
	let passwordconfir= document.getElementById('exampleRepeatPassword').value;
	
	if(datos.password != passwordconfir){
		alert('Las contraseñas deben ser iguales');
		return;
	}
	
	const peticion = await fetch('http://localhost:8080/usuario', {
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
	if(respuesta.code===200){
		alert('usuario creado éxitosamente');
		window.location.href='login.html';
	}else{
		alert('usuario o contraseña incorrectos');
	}
	
}