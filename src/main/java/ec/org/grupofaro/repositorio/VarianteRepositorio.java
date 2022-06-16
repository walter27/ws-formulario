package ec.org.grupofaro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.org.grupofaro.modelo.Variante;

@Repository
public interface VarianteRepositorio extends JpaRepository<Variante, Long> {

}
