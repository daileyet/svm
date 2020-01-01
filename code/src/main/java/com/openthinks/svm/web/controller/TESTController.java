package com.openthinks.svm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.svm.web.job.BuildSyncJob;
import com.openthinks.svm.web.support.util.ResponseJSON;

@RestController
public class TESTController {

  @Autowired
  BuildSyncJob buildSyncJob;
  
  @RequestMapping("/test/sync/version")
  public Object syncTest() {
    ResponseJSON obj = new ResponseJSON();
    buildSyncJob.checkAndSyncProjects();
    return obj.get();
  }
}
