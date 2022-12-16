package cn.edu.sdau.demo2020214464.controller;

import cn.edu.sdau.demo2020214464.dao.UserDao;
import cn.edu.sdau.demo2020214464.dto.LoginDTO;
import cn.edu.sdau.demo2020214464.dto.UserDTO;
import cn.edu.sdau.demo2020214464.entity.User;
import cn.edu.sdau.demo2020214464.form.LoginForm;
import cn.edu.sdau.demo2020214464.form.RegisterForm;
import cn.edu.sdau.demo2020214464.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;


    @GetMapping(value = "registerForm")
    public ModelAndView registerForm(Model model) throws Exception {
        model.addAttribute("user", new RegisterForm());
        return new ModelAndView("users/register", "register", model);
    }

    @PostMapping(value = "register")
    public ModelAndView register(RegisterForm registerForm) throws Exception {
        //System.out.println(registerForm.getName());
        if (userService.findByName(registerForm.getName())) {
            throw new Exception("用户已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(registerForm, user);
        userService.registerUser(user);
        return new ModelAndView("redirect:/users/loginForm");
    }

    @GetMapping(value = "/loginForm")
    public ModelAndView loginForm(Model model) throws Exception {
        model.addAttribute("account", new LoginDTO());
        return new ModelAndView("users/login", "login", model);
    }

    @PostMapping(value = "/login")
    public ModelAndView login(LoginForm loginForm,
                              Model model,
                              HttpServletRequest request) throws Exception {
        User result = userService.findByNameAndPassword(loginForm.getName(), loginForm.getPassword());

        if (null == result) {
            throw new Exception("用户不存在");
        }
        request.getSession().setAttribute("name", result.getName());

        if (result.getRole() == 1) {
            model.addAttribute("username", result.getName());
            return new ModelAndView("redirect:index/admin", "admin", model);
        }
        model.addAttribute("username", result.getName());
        return new ModelAndView("redirect:index/member", "member", model);
    }

    @GetMapping("/index/admin")
    public ModelAndView admin(Model model) {
        model.addAttribute("title", "管理员界面");
        return new ModelAndView("index/admin/admin");
    }

    @GetMapping("/index/member")
    public ModelAndView member(Model model, HttpServletRequest request) {
        model.addAttribute("title", "会员界面");
        model.addAttribute("username", request.getSession().getAttribute("name"));
        return new ModelAndView("index/member/member", "member", model);
    }

    @GetMapping("/loginout")
    public ModelAndView loginout(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        return new ModelAndView("users/loginForm");
    }

    @GetMapping("/listUser")
    public ModelAndView listUser(Model model) {
        model.addAttribute("userList", userDao.findAllByRole(0));
        return new ModelAndView("index/admin/userList", "users", model);
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, Model model) {
        userDao.deleteById(id);
        model.addAttribute("userList", userDao.findAllByRole(0));
        return new ModelAndView("index/admin/userList", "users", model);
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Integer id, Model model) {
        User user = userDao.getOne(id);

        model.addAttribute("user", user);
        model.addAttribute("id", id);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("index/admin/modifyUser", "modify", model);
    }

    @PostMapping("/modifyUser")
    public ModelAndView modify(UserDTO userDTO) throws Exception {
        userService.updateUser(userDTO);
        return new ModelAndView("redirect:loginForm");
    }

    @GetMapping("/modifyMy")
    public ModelAndView modifyMy(Model model) throws Exception {
        model.addAttribute("user", new UserDTO());
        return new ModelAndView("index/member/modifyUser", "modify", model);
    }
}
