package com.kaibakorp.vaccine.domain.service;

import com.kaibakorp.vaccine.domain.exception.DontFoundEntityException;
import com.kaibakorp.vaccine.domain.exception.ServiceException;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUser(Long id){
        return userRepository.findById(id).
                orElseThrow(()-> new DontFoundEntityException("Don't found this user"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
        }

    public User addUser(User user){
        this.exist(user);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        if(!userRepository.existsById(id)){
            throw new DontFoundEntityException("Don't found this user");
        }
        User usernow = userRepository.findById(id).get();
        checkUpdateFields(usernow,user);
        user = userRepository.save(user);
        return user;
    }

    public void removeUser(Long id){
        if(!userRepository.existsById(id)){
            throw new DontFoundEntityException("Don't found this user");
        }
        userRepository.deleteById(id);
    }

    private void exist(User user){
        User userEmailExisits = userRepository.findByEmail(user.getEmail());
        User userCpfExisits = userRepository.findByCpf(user.getCpf());
        if(userEmailExisits!=null && !userEmailExisits.equals(user)){
            throw new ServiceException("This email already in data system");
        }
        if(userCpfExisits!=null && !userCpfExisits.equals(user)){
            throw new ServiceException("This CPF already in data system");
        }
    }

    private void checkUpdateFields(User usernow, User user){
        this.exist(user);
        user.setId(usernow.getId());
        if(user.getEmail()==null){
            user.setEmail(usernow.getEmail());
        }
        if(user.getBornDate()==null){
            user.setBornDate(usernow.getBornDate());
        }
        if(user.getCpf()==null){
            user.setCpf(usernow.getCpf());
        }
        if(user.getName()==null){
            user.setName(usernow.getName());
        }
    }
}
