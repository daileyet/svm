package com.openthinks.svm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.openthinks.svm.core.AppConfig;
import com.openthinks.svm.web.support.util.SpringContextUtil;

@MapperScan("com.openthinks.svm.core.mapper")
@SpringBootApplication
@EnableScheduling
public class SVMApplication extends SpringBootServletInitializer {
  private static final Logger LOGGER = LoggerFactory.getLogger(SVMApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SVMApplication.class, args);
    LOGGER.info(SpringContextUtil.getContext().getBean(AppConfig.class).toString());
  }


  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SVMApplication.class);
  }
}
