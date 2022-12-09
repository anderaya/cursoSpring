package curso.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import curso.respository.IUsuarioRepo;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import curso.modelos.Usuario;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private IUsuarioRepo repo;
	
	@Override
	public List<Usuario> getUsuarios() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public int eliminarUsuario(Long id) {
		// TODO Auto-generated method stub
		Optional<Usuario> actual= repo.findById(id);
		if(actual.isEmpty()) {
			return 0;
		}
		
		repo.deleteById(id);
		return 1;
	}

	@Override
	public Optional<Usuario> getUsuario(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public int crear(Usuario nuevo) {
		Usuario e= new Usuario();
		e.setNombre(nuevo.getNombre());
		e.setApellido(nuevo.getApellido());
		e.setEmail(nuevo.getEmail());
		e.setTelefono(nuevo.getTelefono());
		e.setPassword(nuevo.getPassword());
		int id=repo.guardarUsuario(e.getNombre(), e.getApellido(), e.getTelefono(), e.getPassword(), e.getEmail());
		Optional<Usuario> user= repo.findById((long) id);
		
		if(user.isEmpty()) {
			return -1;
		}else {
			return id;
		}

	}

	@Override
	public int verificarCredenciales(String usuario, String password) {
		// TODO Auto-generated method stub
		List<Usuario> userLogin=repo.loginCre(usuario);
		
		if(userLogin.isEmpty()) {
			return -1;
		}
		String contrasenaHash=userLogin.get(0).getPassword();
		Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
		boolean result=argon.verify(contrasenaHash, password);
		
		return result==true?1:-1;
	}



	

}
