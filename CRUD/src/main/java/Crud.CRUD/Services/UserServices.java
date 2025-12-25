package Crud.CRUD.Services;

import Crud.CRUD.Module.User;
import Crud.CRUD.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository Connection;

    public User createUser (User user){
        return Connection.save(user);
    }

    public User ReadUser(Long id){
        return Connection.findById(id).orElseThrow(()->new RuntimeException("User not shown"));
    }
}
