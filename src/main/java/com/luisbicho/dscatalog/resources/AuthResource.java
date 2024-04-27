package com.luisbicho.dscatalog.resources;

import com.luisbicho.dscatalog.dto.EmailDTO;
import com.luisbicho.dscatalog.dto.NewPasswordDTO;
import com.luisbicho.dscatalog.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private AuthService service;


    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO emailDTO) {
        service.createRecoverToken(emailDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/new-password")
    public ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordDTO dto) {
        service.saveNewPassword(dto);
        return ResponseEntity.noContent().build();
    }
}