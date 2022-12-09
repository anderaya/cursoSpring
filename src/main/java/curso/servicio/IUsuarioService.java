package curso.servicio;

import java.util.List;
import java.util.Optional;

import curso.modelos.Usuario;

public interface IUsuarioService {
	
	List<Usuario> getUsuarios();
	int eliminarUsuario(Long id);
	Optional<Usuario> getUsuario(Long id);
	int crear(Usuario nuevo);
	int verificarCredenciales(String usuario,String password);
	
}
