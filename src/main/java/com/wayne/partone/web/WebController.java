package com.wayne.partone.web;

import com.wayne.partone.model.User;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

//@RestController
public class WebController {

    @RequestMapping(name="/getUser", method =  RequestMethod.GET)
    public User getUser() {
        User user = new User();
        user.setName("小明");
        user.setAge(12);
        user.setPass("123456");

        return user;
    }
    @RequestMapping("/saveUser")
    public void saveUser(@Valid User user, BindingResult result) {
        System.out.println("user:" + user);
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError error : list) {
                System.out.println(error.getCode() + "-" + error.getDefaultMessage());
            }
        }
    }
}
