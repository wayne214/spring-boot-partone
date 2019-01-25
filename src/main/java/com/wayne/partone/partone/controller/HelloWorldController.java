package com.wayne.partone.partone.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;

@RestController
@RequestMapping
@Api(value = "用户模块")
public class HelloWorldController {
//    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @GetMapping("/hello")
    @ApiOperation(value = "测试Swagger")
    public String index() {
        return "Hello World!";
    }
    /**
     * GET方式URL传参方式一
     * @PathVariable
     *
     */
    @GetMapping("/hello/{name}")
    @ApiOperation(value = "测试api传递参数")
    @ApiImplicitParam(value = "用户的name", paramType= "path")
    public String hello(@PathVariable("name") String name) {
        return "Hello " + name;
    }

    /**
     * GET方式URL传参方式二
     * @RequeestParam
     * 如果请求参数的名字跟方法中的形参名字一致可以省略
     * @RequestParam("name")
     * defaultValue 默认值
     * 如果没有指定默认值，并且没有传递参数将会报错
     * 可通过如下两种方式：
     * 1. 使用defaultValue 设置默认值
     * 2. 设置required 为 false 标注参数是非必须的
     *
     */
    @GetMapping("/hello2")
    public String hello2(@RequestParam(value = "name", defaultValue = "wayne214", required = false) String name) {
        return "Hello " + name;
    }

    // logger 日志工具
    public static Logger log = LoggerFactory.getLogger(HelloWorldController.class);
    /**
     * POST方式传递参数
     * 使用Postman工具进行测试
     * */
    @PostMapping("/user")
    public String addUser(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        log.info(name + "," + age);
        return "name:"+name+"\nage:"+age;
    }



}
