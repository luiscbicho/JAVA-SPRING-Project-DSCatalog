package com.luisbicho.dscatalog.services;

import com.luisbicho.dscatalog.dto.RoleDTO;
import com.luisbicho.dscatalog.dto.UserDTO;
import com.luisbicho.dscatalog.dto.UserInsertDTO;
import com.luisbicho.dscatalog.entities.Role;
import com.luisbicho.dscatalog.entities.User;
import com.luisbicho.dscatalog.repositories.RoleRepository;
import com.luisbicho.dscatalog.repositories.UserRepository;
import com.luisbicho.dscatalog.services.exceptions.DatabaseException;
import com.luisbicho.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> result = repository.findAll(pageable);
        return result.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> result = repository.findById(id);
        return new UserDTO(result.orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User user = new User();
        update(user, dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = repository.save(user);
        return new UserDTO(user);

    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User user = repository.getReferenceById(id);
            update(user, dto);
            user = repository.save(user);
            return new UserDTO(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void update(User user, UserDTO dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        user.getRoles().clear();
        for (RoleDTO x : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(x.getId());
            user.getRoles().add(role);
        }
    }
}