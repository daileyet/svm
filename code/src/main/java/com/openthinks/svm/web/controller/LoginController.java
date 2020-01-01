package com.openthinks.svm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {


  @RequestMapping("/login")
  public String toLogin(Model model) {
    return "login";
  }

  @RequestMapping("/logout")
  public String doLogout(Model model) {
    return "login";
  }
}
