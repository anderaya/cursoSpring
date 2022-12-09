package curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.modelos.Mensaje;
import curso.modelos.Usuario;
import curso.servicio.IUsuarioService;
import curso.utils.JWTUtil;




@RestController
@RequestMapping("login")
@CrossOrigin(origins = "null")
public class LoginController {
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private JWTUtil jwta;
	
	@PostMapping
	public ResponseEntity<Mensaje> login(@RequestBody Usuario body) {
		int result = service.verificarCredenciales(body.getEmail(),body.getPassword());
		if(result ==-1) {
			Mensaje resulta= new Mensaje();
			resulta.setMensaje("Fallo al iniciar sesión.");
			resulta.setErrores("{Usuario o contraseña incorrectos}");
			resulta.setCode(502);
			return new ResponseEntity<Mensaje>(resulta,HttpStatus.valueOf(502));
		}
		
		String token=jwta.create(String.valueOf(body.getId()), body.getEmail());
		Mensaje resulta= new Mensaje();
		resulta.setMensaje(token);
		resulta.setErrores("{}");
		resulta.setCode(200);
		
		
		return ResponseEntity.ok(resulta);
	}
	
}
