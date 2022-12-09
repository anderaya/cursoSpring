package curso.respository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import curso.modelos.Usuario;


@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario,Long>{

	public List<Usuario> findAll();
	
	
	@Transactional
	@Query(nativeQuery = true,
			value="INSERT INTO public.usuario(\r\n"
			+ " nombre, apellido, telefono, password, email)\r\n"
			+ "	VALUES ( ?, ?, ?, ?, ?) RETURNING  id")
	public int guardarUsuario(String nombre, String apellido, String telefono,String password , String email);
	
	
	@Transactional
	@Query(nativeQuery = true,
			value="select * from Usuario where email=?")
	public List<Usuario> loginCre(String email);
	
}
