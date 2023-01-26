package com.accenture.modulosPago.clients;

import com.accenture.modulosPago.entities.Account;
import com.accenture.modulosPago.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "userMicroService", url = "localhost:8001")
public interface UserClientRest {

    @GetMapping("/api/users/list")
    public List<User> findAll();

    @GetMapping("/api/users/detail/{id}")
    public User findById(@PathVariable Long id);

    @PostMapping("api/user/addAccountUser")
    public ResponseEntity<Object> addAccountUser(@RequestBody Account account);

}