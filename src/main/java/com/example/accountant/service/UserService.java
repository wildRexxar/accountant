package com.example.accountant.service;

import com.example.accountant.entity.User;
import com.example.accountant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserById(Long id){
        return  userRepository.findById(id);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User addNewUser(User user){
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUser(User userForUpdate, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userForUpdate.setId(id);
            userRepository.save(userForUpdate);
             return true;
        } else{
            return false;
        }
    }
}
