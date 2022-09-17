package br.com.gusta.algalogapi.domain.repository;

import br.com.gusta.algalogapi.domain.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeContaining(String nome);

    Optional<Cliente> findByEmail(String email);

}
