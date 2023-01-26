package com.accenture.modulosPago.repositories;

import com.accenture.modulosPago.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByIdUserOrderByIdDesc(Long idUser);

    List<Account> findByIdUser(Long idUser);

    Account findByAccountNumber(String accountNumber);

    Account findByCbu(String cbu);
}
