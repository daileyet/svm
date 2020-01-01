package com.openthinks.svm;

import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppIdSecrtGenerator {

  public static void main(String[] args) {
    String[] pair = generate();
    System.out.println(String.format("AppId:%s\nAppSecrt:%s\nBCrypt AppSecrt:%s", pair[0],pair[1],pair[2]));
  }
  
  static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
  public static String[] generate() {
    String appId = "01"+UUIDUtils.genShortUUID(),appSecrt = UUIDUtils.genUUID16();
    return new String[] {appId,appSecrt,bCryptPasswordEncoder.encode(appSecrt)};
  }

  static final class UUIDUtils {
    private UUIDUtils() {}

    public static String[] chars = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
        "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


    /**
     * 
     * genShortUUID:generate short UUID with 8 bytes. </br>
     * 
     * @return String short UUID which length is 8
     */
    public static String genShortUUID() {
      StringBuffer stringBuffer = new StringBuffer();
      String uuid = UUID.randomUUID().toString().replace("-", "");
      for (int i = 0; i < 8; i++) {
        String str = uuid.substring(i * 4, i * 4 + 4);
        int strInteger = Integer.parseInt(str, 16);
        stringBuffer.append(chars[strInteger % 0x3E]);
      }
      return stringBuffer.toString();
    }
    
    public static String genUUID16() {
      StringBuffer stringBuffer = new StringBuffer();
      String uuid = UUID.randomUUID().toString().replace("-", "");
      for (int i = 0; i < 16; i++) {
        String str = uuid.substring(i * 2, i * 2 + 2);
        int strInteger = Integer.parseInt(str, 16);
        stringBuffer.append(chars[strInteger % 0x3E]);
      }
      return stringBuffer.toString();
    }
    
  }
}
