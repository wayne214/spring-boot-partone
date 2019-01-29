package com.wayne.partone.partone.controller;

import com.wayne.partone.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class indexController {
//    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @Value("${application.hello:Hello Wayne}")
    private String hello = "Hello Wayne";

    @GetMapping(value = "/")
    public String index(ModelMap map) {
        map.addAttribute("message", "http://www.ityouknow.com");
        return "hello";
    }

    @RequestMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "wayne");
        return "string";
    }

    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<>();
        User user1=new User("大牛",12,"123456");
        User user2=new User("小牛",6,"123563");
        User user3=new User("纯洁的微笑",66,"666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);

        return list;

    }

    /**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     * */
    @RequestMapping(value = {"/","/index"})
    public String index(Map<String, Object> model) {
        // 直接返回字符串，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        // 本例为 /WEB-INF/jsp/index.jsp
        model.put("time", new Date());
        model.put("message", this.hello);
        return "index";
    }
    @RequestMapping(value = "/page1")
    public ModelAndView page1() {
        ModelAndView mav = new ModelAndView("/page1");
        mav.addObject("content", hello);
        return mav;
    }
}
