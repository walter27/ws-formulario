package ec.org.grupofaro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.org.grupofaro.modelo.Variable;

@Repository
public interface VariableRepository extends JpaRepository<Variable, Long>{

}
