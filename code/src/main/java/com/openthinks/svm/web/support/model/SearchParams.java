package com.openthinks.svm.web.support.model;

import java.util.Date;
import com.openthinks.svm.web.support.util.WebDateUtils;

/**
 * ClassName: SearchParams </br>
 * date: Aug 13, 2018 4:13:21 PM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public final class SearchParams {
  /**
   * 当前显示的页码
   */
  private int page = 1;
  /**
   * 当前设定每页显示的数据个数
   */
  private int recPerPage = 15;
  /**
   * 当前用于检索数据的文本
   */
  private String search;
  /**
   * 当前排序依据的列名
   */
  private String sortBy;
  /**
   * 当前排序的顺序，包括顺序（'asc'）和倒序（'desc'）
   */
  private String order;
  /**
   * 总的数据数目
   */
  private int recTotal;

  private String startTime;

  private String endTime;

  public final int getPage() {
    return page;
  }

  public final void setPage(int page) {
    this.page = page;
  }

  public final int getRecPerPage() {
    return recPerPage;
  }

  public final void setRecPerPage(int recPerPage) {
    this.recPerPage = recPerPage;
  }

  public final String getSearch() {
    return search;
  }

  public final void setSearch(String search) {
    this.search = search;
  }

  public final String getSortBy() {
    return sortBy;
  }

  public final void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public final String getOrder() {
    return order;
  }

  public final void setOrder(String order) {
    this.order = order;
  }

  public int getRecTotal() {
    return recTotal;
  }

  public void setRecTotal(int recTotal) {
    this.recTotal = recTotal;
  }

  public final String getStartTime() {
    return startTime;
  }

  public final void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public final String getEndTime() {
    return endTime;
  }

  public final void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "SearchParams [page=" + page + ", recPerPage=" + recPerPage + ", search=" + search
        + ", sortBy=" + sortBy + ", order=" + order + ", recTotal=" + recTotal + ", startTime="
        + startTime + ", endTime=" + endTime + "]";
  }

  public Date getStartTimeAsDate() {
    return WebDateUtils.parse(getStartTime());
  }

  public Date getEndTimeAsDate() {
    return WebDateUtils.parse(getEndTime());
  }


}
