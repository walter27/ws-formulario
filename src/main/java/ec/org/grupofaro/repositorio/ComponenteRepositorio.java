package ec.org.grupofaro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.org.grupofaro.modelo.Componente;

@Repository
public interface ComponenteRepositorio extends JpaRepository<Componente, Long> {

}
