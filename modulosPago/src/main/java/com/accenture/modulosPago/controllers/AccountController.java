package com.accenture.modulosPago.controllers;

import com.accenture.modulosPago.dtos.AccountDto;
import com.accenture.modulosPago.dtos.TransactionDto;
import com.accenture.modulosPago.entities.Account;
import com.accenture.modulosPago.models.User;
import com.accenture.modulosPago.repositories.AccountRepository;
import com.accenture.modulosPago.service.AccountService;
import com.accenture.modulosPago.service.InterfaceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utils.Utils;
import java.util.List;
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    @Qualifier("accountServiceRest")
    private InterfaceAccountService interfaceAccountService;

    @GetMapping("/list")
    public List<Account> getListAccounts(){
        return interfaceAccountService.findAll();
    }

    @GetMapping("/list/{idUser}")
    public List<Account> getListActiveAccounts(@PathVariable Long idUser){
        System.out.println(interfaceAccountService.findAccounts(idUser));
        return interfaceAccountService.findAccounts(idUser);
    }

    @GetMapping("/detail/{id}")
    public Account detail(@PathVariable Long id){
        return interfaceAccountService.findById(id);
    }

    @GetMapping("/listAccount/{idUser}")
    public List<Account> listAccountIdUser(@PathVariable Long idUser){
        return interfaceAccountService.findByIdUser(idUser);
    }

    @GetMapping("/lastAccountCreated/{idUser}")
    public Account lastAccountCreated(@PathVariable Long idUser){
        return interfaceAccountService.LastCreatedAccount(idUser);
    }

    @GetMapping("/list/cbu/{cbu}")
    public ResponseEntity<Object> getByCbu(@PathVariable String cbu) {
        if (cbu.length() != 22) {
            return new ResponseEntity<>("CBU must have 22 digits", HttpStatus.NOT_FOUND);
        }
        if (Utils.verifyNumber(cbu) == false) {
            return new ResponseEntity<>("CBU only accept digits numbers", HttpStatus.NOT_FOUND);
        }
        Account account = interfaceAccountService.findByCbu(cbu);
        return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/number/{number}")
    public ResponseEntity<Object> getByNumberAccount(@PathVariable String number) {
        if (number.length() != 10) {
            return new ResponseEntity<>("Number Account must have 8 digits", HttpStatus.NOT_FOUND);
        }
        if (Utils.verifyNumber(number) == false) {
            return new ResponseEntity<>("Number Account only accept digits numbers", HttpStatus.NOT_FOUND);
        }
        Account account = interfaceAccountService.findByAccountNumber(number);
        return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
    }

    @PostMapping ("/createdAccount")
    public ResponseEntity<Object> createdAccount(@RequestBody User user){
        try{
            int dniLength = user.getDni().length();
            if (!(dniLength > 6 && dniLength < 9)) {
                return new ResponseEntity<>("The length of DNI is not correct", HttpStatus.NOT_ACCEPTABLE);
            }
            if (!Utils.verifyNumber(user.getDni())) {
                return new ResponseEntity<>("Error, DNI only accept numbers", HttpStatus.NOT_ACCEPTABLE);
            }
            if (user.getName().trim().isEmpty() || user.getLastName().trim().isEmpty() || user.getDni().trim().isEmpty()
                    || user.getMail().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
                return new ResponseEntity<>("Missing data, please check all fields", HttpStatus.NOT_ACCEPTABLE);
            }
            if(!interfaceAccountService.findAll().isEmpty()){
                if(interfaceAccountService.findLastUserWithAccount(user.getId()) == 0L){
                    return new ResponseEntity<>("This user no exist, check data", HttpStatus.NOT_ACCEPTABLE);
                }
            }
            Account accountNew = interfaceAccountService.createdAccount(user);
            return new ResponseEntity<>(accountNew, HttpStatus.CREATED);
        } catch (Exception ex){
            ex.printStackTrace();
            ex.getMessage();
            return new ResponseEntity<>("Unexpected Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove/{idAccount}")
    public ResponseEntity<Object> removeAccount(@PathVariable Long idAccount){

        if(interfaceAccountService.deleteByIdAccount(idAccount)){
            return new ResponseEntity<>("Account deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Account no exist", HttpStatus.NOT_ACCEPTABLE);
    }


    @PostMapping("/updateBalance")
    public void updatedBalance(@RequestBody TransactionDto transactionDto){
        interfaceAccountService.updatedBalance(transactionDto);
    }

}













