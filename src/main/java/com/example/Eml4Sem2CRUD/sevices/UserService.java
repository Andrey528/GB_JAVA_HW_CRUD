package com.example.Eml4Sem2CRUD.sevices;

import com.example.Eml4Sem2CRUD.models.User;
import com.example.Eml4Sem2CRUD.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User getUserById(int id) {
        return userRepository.getOne(id);
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }
}