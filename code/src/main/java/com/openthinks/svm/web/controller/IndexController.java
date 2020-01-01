package com.openthinks.svm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @RequestMapping("/")
  public String index() {
    return "build/index";
  }
  
  @RequestMapping("/index.html")
  public String toindex() {
    return "build/index";
  }
}
