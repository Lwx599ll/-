package cn.edu.sdau.demo2020214464.dao;

import cn.edu.sdau.demo2020214464.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface UserDao extends JpaRepository<User,Integer> {
     @param name
     @return

    User findByName(String name);

    User findByNameAndPassword(String name, String password);

    Object findAllByRole(int i);
}
