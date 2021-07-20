package com.kaibakorp.vaccine.domain.service;


import com.kaibakorp.vaccine.api.conversion.ConversionUser;
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
        User userExisits = userRepository.findByEmail(user.getEmail());
        if(userExisits!=null && !userExisits.equals(user)){
            throw new ServiceException("This email already in data system");
        }
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

    private void checkUpdateFields(User usernow, User user){
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
