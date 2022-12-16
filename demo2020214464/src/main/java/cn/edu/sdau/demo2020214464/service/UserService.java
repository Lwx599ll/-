package cn.edu.sdau.demo2020214464.service;

import cn.edu.sdau.demo2020214464.dao.UserDao;
import cn.edu.sdau.demo2020214464.dto.UserDTO;
import cn.edu.sdau.demo2020214464.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    
    public boolean findByName(String name) throws Exception {
        User user = userDao.findByName(name);
        if (user == null) {
            return false;
        }
        return true;
    }

    public void registerUser(User user) throws Exception {
        userDao.save(user);
    }

    public User findByNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name, password);
    }

    public void updateUser(UserDTO userDTO) {
    }
}
