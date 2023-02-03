package com.accenture.modulosPago.repositories;

import com.accenture.modulosPago.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByIdUserOrderByIdDesc(Long idUser);

    List<Account> findByIdUser(Long idUser);

    Account findByAccountNumber(String accountNumber);

    Account findByCbu(String cbu);
    Optional<Account> findById(Long id);
    List<Account> findByIdUserAndStatus(Long idUser, Boolean status);

    Optional<Account> findByIdUserOrderByIdUserDesc(Long idUser);
}
