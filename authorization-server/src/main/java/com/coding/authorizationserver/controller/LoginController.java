package com.coding.authorizationserver.controller;

import com.coding.authorizationserver.constant.Urls;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器.
 *
 * @author caijunhui 2017/11/18
 **/
@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "login";

    @GetMapping(value = Urls.LOGIN)
    public String loginPage() {
        return LOGIN_PAGE;
    }

    @GetMapping(value = Urls.LOGIN, params = "error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return LOGIN_PAGE;
    }

    @GetMapping(value = Urls.LOGIN, params = "logout")
    public String logoutSuccess() {
        return LOGIN_PAGE;
    }

    @RequestMapping(value = "/")
    public String home() {
        return "redirect:" + Urls.DASHBOARD;
    }

}
