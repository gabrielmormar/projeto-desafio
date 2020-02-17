package desafio.empresas.repositories;

import desafio.empresas.domain.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Transactional(readOnly = true)
    Page<Empresa> findByNomeContainingOrCnpjContaining(String nome, String cnpj, Pageable pageRequest);
}
