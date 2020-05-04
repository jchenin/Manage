package jin.chen.controller;

import jin.chen.bean.AdminUser;
import jin.chen.pojo.Admin;
import jin.chen.pojo.Users;
import jin.chen.service.UsersService;
import jin.chen.utils.PagedResult;
import jin.chen.utils.VideoJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/showList")
    public String showUsersList() {
        return "users/usersList";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }



    @PostMapping("/login")
    @ResponseBody
    public VideoJSONResult userLogin(String username, String password,
                                     HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return VideoJSONResult.errorMsg("用户名和密码不能为空");
        }
        boolean flag = usersService.queryAdminSuccess(username, password);
         if (!flag) {
             return VideoJSONResult.errorMsg("用户名或密码错误，请重试...");
        }else{
             String token = UUID.randomUUID().toString();
             AdminUser user = new AdminUser(username, password, token);
             request.getSession().setAttribute("sessionUser", user);
             return VideoJSONResult.ok();
         }
    }

    @PostMapping("/register")
    @ResponseBody
    public VideoJSONResult userRegister(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return VideoJSONResult.errorMsg("用户名和密码不能为空");
        }
        boolean flag = usersService.queryAdminExist(username);
        if(flag){
            return VideoJSONResult.errorMsg("用户名已存在，请重新输入");
        }else{
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);
            usersService.addAdmin(admin);
            return VideoJSONResult.ok("注册成功");
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("sessionUser");
        return "login";
    }

    @PostMapping("/list")
    @ResponseBody
    public PagedResult queryUsersList(Users users, Integer page){
        PagedResult result = usersService.queryUsersList(users, page == null ? 1 : page, 10);
        return result;
    }
}
