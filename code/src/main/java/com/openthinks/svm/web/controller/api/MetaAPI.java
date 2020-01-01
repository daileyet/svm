package com.openthinks.svm.web.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONArray;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.svm.core.AppConstants;
import com.openthinks.svm.core.model.BizMetaProductline;
import com.openthinks.svm.core.service.MetaService;
import com.openthinks.svm.web.support.util.JSONConvertersUtil;

/**
 * @author dailey.dai@openthinks.com
 *
 */
@RestController
@RequestMapping("/api/meta")
public class MetaAPI {

  @Autowired
  MetaService metaService;

  @RequestMapping("/prj/productline")
  @ResponseBody
  public JSONObject fetchByProductline(@RequestParam(name = "prjId", required = true) String prjId,
      @RequestParam(name = "cate", required = false,
          defaultValue = AppConstants.META_CATEGORY_DEFAULT) String category) {
    JSONObject jsonObj = JSON.object();
    JSONArray array = jsonObj.createEmbedArray("productlines");
    List<BizMetaProductline> productlines = metaService.findMetaProductlines(category, prjId);
    productlines.forEach(productline -> {
      array.add(JSONConvertersUtil.perparedAndGet(productline));
    });
    return jsonObj;
  }


}
