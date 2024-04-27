package com.luisbicho.dscatalog.dto;

import com.luisbicho.dscatalog.entities.User;
import com.luisbicho.dscatalog.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "Required feld")
    @Size(min = 8, message = "At least 8 Characters")
    private String password;

    public UserInsertDTO() {
        super();
    }

    public UserInsertDTO(String password) {
        this.password = password;
    }

    public UserInsertDTO(User user, String password) {
        super(user);
        this.password = password;
    }

    public UserInsertDTO(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
