package com.remmy;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld implements EnvironmentAware {

    @RequestMapping(value = "/hello", method = { RequestMethod.GET })
    // @ResponseBody
    public String hello(Model model) {
        model.addAttribute("msg", "Hello World!!!");
        return "hello.jsp";
    }

    private Environment env = null;
    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }
}
