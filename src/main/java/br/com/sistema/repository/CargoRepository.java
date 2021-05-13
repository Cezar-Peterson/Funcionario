package br.com.sistema.repository;

import br.com.sistema.model.Cargo;
import br.com.sistema.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    public Cargo findByNome(String nome);
    public Cargo findByIdNotAndNome(Long id, String nome);

}
