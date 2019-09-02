package com.wmrsu.casclient.controller;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author WangXu
 * @create 2019/8/30 12:51
 */
@Controller
public class CasController {

    @ResponseBody
    @RequestMapping("/test")
    public Object test() {
        return "cas-客户端-a";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws IOException {
        System.out.println(session.getId());
        session.invalidate();
        return "redirect:http://localhost:8080/cas/logout?service=http://127.0.0.1:8081/a/test";
    }

    @ResponseBody
    @RequestMapping("/name")
    public String getName(HttpSession session) {
        System.out.println("name:"+session.getAttribute("_const_cas_assertion_"));
        Assertion assertion =(Assertion)session.getAttribute("_const_cas_assertion_");
        System.out.println("登录用户:"+assertion.getPrincipal().getName());
        return "当前登录用户："+assertion.getPrincipal().getName();
    }
}
