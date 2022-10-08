package com.gxa.controller;

import com.gxa.entity.User;
import com.gxa.service.UserService;
import com.gxa.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @CrossOrigin(value = "*")
    @RequestMapping(value = "/user/login" , method = RequestMethod.POST)
//    @PostMapping("/user/login")
    public R login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        System.out.println(username + pwd);
//        User user1 = this.userService.login(user);

//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);


//        try {
//            subject.login(token);
//            return R.ok();
//        }catch (Exception e){
//            e.printStackTrace();
//            //登录失败
//            return null;
//        }


        return R.ok();
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/index.html";
    }
}
