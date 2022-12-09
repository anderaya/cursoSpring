// Call the dataTables jQuery plugin
$(document).ready(function() {
	cargarUsuarios();
  	$('#usuarios').DataTable();
  
});

async function cargarUsuarios(){

	const peticion = await fetch('http://localhost:8080/usuario/todos', {
	  method: 'GET',
	  headers: getHeaders(),
	  mode: 'cors',
	  cache: 'default',
	});
	
	const datos= await peticion.json();
	let lista ='';
	for (let user of datos){
		let eliminar='<a href="#" onClick="eliminarUsuario('+user.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
		let texttelefono=user.telefono == null ? '-' : user.telefono;
		let usuario='<tr><td>'+user.id+'</td><td>'+user.nombre+' '+user.apellido+'</td><td>'+user.email+'</td><td>'+texttelefono+'</td><td>'+eliminar+'</td></tr>';
		lista=lista+usuario;
	}
	
    document.querySelector("#usuarios tbody").outerHTML= lista;                                         
                                         
	

	console.log(lista);
	
	
}

function getHeaders(){
		return {
			'Content-Type': 'application/json',
		  	'Accept': 'application/json',
		  	'Authorization':localStorage.token
	  	};
}
	
	
async function eliminarUsuario(id){

	if(!confirm('Desea eliminar este usuario?')){
	 	return ;
	}

	const peticion = await fetch('http://localhost:8080/usuario/eliminar/'+id, {
	  method: 'DELETE',
	  headers: getHeaders(),
	  mode: 'cors',
	  cache: 'default',
	});
	
	location.reload();

}
