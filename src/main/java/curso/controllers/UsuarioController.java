package curso.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.modelos.Mensaje;
import curso.modelos.Usuario;
import curso.servicio.IUsuarioService;
import curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "null")
public class UsuarioController {
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private JWTUtil jwta;
	
	@GetMapping(value="todos")
	public List<Usuario> getUsuario(@RequestHeader(value="Authorization") String token) {
		if(validarToken(token)==null) {
			return new ArrayList();
		}
		return service.getUsuarios();
	}

	@DeleteMapping(value="eliminar/{id}")
	public ResponseEntity<Mensaje> eliminar(@PathVariable Long id,@RequestHeader(value="Authorization") String token) {
		if(validarToken(token)==null) {
			return ResponseEntity.notFound().build();
		}
		
		int result = service.eliminarUsuario(id);
		if(result ==0) {
			return ResponseEntity.notFound().build();
		}
		Mensaje resulta= new Mensaje();
		resulta.setMensaje("Eliminado correctamente.");
		resulta.setErrores("{}");
		resulta.setCode(200);
		
		
		return ResponseEntity.ok(resulta);
	}
	
	public String validarToken(String token) {
		String usuarioId=jwta.getKey(token);
		return usuarioId;
	}
	
	@PostMapping
	public ResponseEntity<Mensaje> crear(@RequestBody Usuario body) {
		Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
		body.setPassword(argon.hash(1,1024,1, body.getPassword()));
		int result = service.crear(body);
		if(result ==-1) {
			return ResponseEntity.badRequest().build();
		}
		Mensaje resulta= new Mensaje();
		resulta.setMensaje("Creado correctamente con id : "+result+".");
		resulta.setErrores("{}");
		resulta.setCode(200);
		
		
		return ResponseEntity.ok(resulta);
	}
	


}
