package com.openthinks.svm.web.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.openthinks.svm.core.AppConstants.BuildState;
import com.openthinks.svm.core.AppConstants.BuildSyncStatus;
import com.openthinks.svm.core.ReleaseType;
import com.openthinks.svm.core.model.BizProject;
import com.openthinks.svm.core.model.BizReleaseInfo;
import com.openthinks.svm.core.model.BizVersion;
import com.openthinks.svm.core.model.BizVersionExample;
import com.openthinks.svm.core.service.ProjectService;
import com.openthinks.svm.core.service.ReleaseInfoService;
import com.openthinks.svm.core.service.ThirdPartyAPIService;
import com.openthinks.svm.core.service.ThirdPartyAPIService.VersionSyncModel;
import com.openthinks.svm.core.service.VersionService;
import com.openthinks.svm.core.util.DateUtils;

/**
 * ClassName: BuildSyncJob <br>
 * Function: Job for sync build info to Third party version server. <br>
 * Call API:<BR>
 * <ul>
 * <li><B>GET TOKEN</B>: http://third-party/api/pub/token?appid=&lt;APPID&gt;&secrt=&lt;APPSECRT&gt;
 * <li><B>RFRESH TOKEN</B>: http://third-party/api/pub/token/refresh?refresh_token=&lt;TOKEN&gt;
 * <li><B>POST DATA</B>: http://third-party/api/pub/version/sync
 * </ul>
 * date: Apr 12, 2019 3:06:59 PM <br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
@Component
public class BuildSyncJob {
  private static final Logger LOGGER = LoggerFactory.getLogger(BuildSyncJob.class);
  @Autowired
  VersionService versionService;

  @Autowired
  ProjectService projectService;

  @Autowired
  ReleaseInfoService releaseInfoService;

  @Autowired
  ThirdPartyAPIService thirdPartyApiService;

  private final static Queue<Long> syncFlagQueue = new ConcurrentLinkedQueue<>();

  @Scheduled(initialDelay = 60000, fixedDelay = 60000)
  public void jobExecute() {
    if (syncFlagQueue.isEmpty()) {
      // IGNORE check sync
    } else {
      syncFlagQueue.clear();
      try {
        checkAndSyncProjects();
      } catch (Exception e) {
        LOGGER.warn("Failed to sync version data by reason:{}", e);
      }
    }
  }

  public void pushTrigger() {
    syncFlagQueue.offer(DateUtils.currentTimeMillis());
  }


  // call every day 23:00
  @Scheduled(cron = "0 0 23 * * ?")
  public void checkAndSyncProjects() {
    List<BizProject> projects = projectService.findNeedSyncProjects();
    if (projects.isEmpty()) {
      return;
    }
    BizProject project = projects.get(0);
    BizVersionExample example = new BizVersionExample();
    example.createCriteria().andSyncStatusEqualTo(BuildSyncStatus.NOT_SYNC.value())
        .andValidEqualTo(BuildState.VALID.value()).andProjectIdEqualTo(project.getId());
    List<BizVersion> notSyncList = versionService.find(example);
    if (notSyncList.isEmpty()) {
      LOGGER.info("There are no need sync version record which project short name:{}",
          project.getShortName());
      if (projects.size() > 1) {
        pushTrigger();
      }
      return;
    }
    List<VersionSyncModel> syncModeList = new ArrayList<>();
    for (final BizVersion record : notSyncList) {
      VersionSyncModel mode = convertToVersionSyncMode(record);
      syncModeList.add(mode);
    }
    // GET TOKEN
    String token = thirdPartyApiService.requestToken();
    if (token == null) {
      LOGGER.warn(
          "Could not request token from Third Party API, so will ignore sync action this time.");
    } else {
      try {
        // DO SYNC
        thirdPartyApiService.postVersions(syncModeList, token);
      } catch (Exception e) {
        LOGGER.warn(
            "Failed to post version data to Third Party API by reason:{}, so will ignore sync action this time.",
            e);
      }
    }
    if (projects.size() > 1) {
      pushTrigger();
    }

  }


  private VersionSyncModel convertToVersionSyncMode(BizVersion record) {
    Map<String, BizReleaseInfo> relMap =
        releaseInfoService.searchReleaseGroupByType(record.getId());
    VersionSyncModel syncMode = new VersionSyncModel();
    syncMode.setV(record.getNumber());
    for (ReleaseType type : ReleaseType.values()) {
      BizReleaseInfo info = relMap.get(type.getValue());
      String relNum = (info == null) ? null : info.getReleaseNum();
      syncMode.setReleaseVersion(type, relNum);
    }
    return syncMode;
  }
}
