package com.openthinks.svm.core.util;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.TimeZone;

/**
 * ClassName: DateUtils </br>
 * date: Aug 7, 2018 9:30:44 AM </br>
 * 
 * @author dailey.dai@openthinks.com
 * @since JDK 1.8
 */
public class DateUtils {

  private static String tz = "Asia/Shanghai";
  private static TimeZone timeZone;
  private static ZoneId zoneId;

  static {
    timeZone = TimeZone.getTimeZone(tz);
    zoneId = timeZone.toZoneId();
  }

  protected DateUtils() {}

  public static final ZoneId getZoneId() {
    return zoneId;
  }

  public static final TimeZone getTimeZone() {
    return timeZone;
  }

  /**
   * 
   * now: replacement of new java.util.Date(); 请使用新的jdk8的java.time.LocaleDateTime<br/>
   * 系统中使用@see express.util.DateTime.current()
   * 
   * @since JDK 1.8
   */
  public static Date now() {
    return Date.from(Instant.now(Clock.system(zoneId)));
  }

  public static Instant nowInstant() {
    return Instant.now(Clock.system(zoneId));
  }

  public static Instant nowInstantPlus(long amountToAdd, TemporalUnit unit) {
    return nowInstant().plus(amountToAdd, unit);
  }

  public static Instant instantPlus(Instant target, long amountToAdd, TemporalUnit unit) {
    return target.plus(amountToAdd, unit);
  }

  public static Instant nowInstantMinus(long amountToSubtract, TemporalUnit unit) {
    return nowInstant().minus(amountToSubtract, unit);
  }

  public static Instant instantMinus(Instant target, long amountToSubtract, TemporalUnit unit) {
    return target.minus(amountToSubtract, unit);
  }

  public static Date offset(Date date, long millseconds) {
    date.setTime(date.getTime() + millseconds);
    return date;
  }

  public static Date offset(long millseconds) {
    return offset(now(), millseconds);
  }

  public static LocalDateTime current() {
    return LocalDateTime.now(zoneId);
  }

  /**
   * 
   * systemUpMillseconds: how many millseconds the system up. please be noted, this is ok for single
   * CPU. if there are more than one CPU, it is not accurate.
   * 
   * @return
   */
  public final static long systemUpMillseconds() {
    return System.nanoTime() / 1000000;
  }

  /**
   * 
   * currentTimeMillis <br/>
   * 
   * @since JDK 1.8
   */
  public static long currentTimeMillis() {
    return Instant.now(Clock.system(zoneId)).toEpochMilli();
  }

  public static Instant toInstant(long time) {
    return Instant.ofEpochMilli(time);
  }

  public static LocalDateTime toLocalDateTime(long time) {
    return LocalDateTime.ofInstant(toInstant(time), getZoneId());
  }

  public static Duration between(long firstTime, long secondTime) {
    LocalDateTime first = toLocalDateTime(firstTime);
    LocalDateTime second = toLocalDateTime(secondTime);
    return Duration.between(first, second);
  }

}
