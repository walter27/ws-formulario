package ec.org.grupofaro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.org.grupofaro.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
