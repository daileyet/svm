package com.openthinks.svm.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import com.openthinks.libs.utilities.exception.CheckerNoPassException;

/**
 * ClassName: LogErrorController </br>
 * date: Aug 20, 2018 11:13:13 AM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@ControllerAdvice
@Controller
public final class PageErrorController implements ErrorController {

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {
    Map<String, Object> errorMap = new HashMap<>();
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      errorMap.put("code", statusCode);
    } else {
      errorMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    String details = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
    if ((details == null || details.trim().isEmpty()) && status != null) {
      details = status.toString();
    }
    String URI = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    errorMap.put("details", details);
    errorMap.put("uri", URI);
    model.addAttribute("error", errorMap);
    return "error";
  }

  /**
   * @see org.springframework.boot.web.servlet.error.ErrorController#getErrorPath()
   */
  @Override
  public String getErrorPath() {
    return "/error";
  }

  @ExceptionHandler({CheckerNoPassException.class, Exception.class})
  public String defaultExceptionHandler(HttpServletRequest request, Exception e, Model model) {
    Map<String, Object> errorMap = new HashMap<>();
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      errorMap.put("code", statusCode);
    } else {
      errorMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    String details = e.getMessage();
    if ((details == null || details.trim().isEmpty()) && status != null) {
      details = status.toString();
    }
    String URI = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    errorMap.put("details", details);
    errorMap.put("uri", URI);
    model.addAttribute("error", errorMap);
    return "error";
  }

}
