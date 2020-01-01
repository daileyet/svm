/**
 * 
 */
package com.openthinks.svm;

import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author dailey
 *
 */
public class BCrptPasswordEncoderTest {

  public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String secrt = UUID.randomUUID().toString().replace("-", "");
    secrt = "a00d64c5068148bd8896f034e4c3ab28";
    String ecodeSecrt = bCryptPasswordEncoder.encode(secrt);
    System.out.println(secrt + " = " + ecodeSecrt);
    System.out.println(bCryptPasswordEncoder.matches(secrt, ecodeSecrt));
    // a00d64c5068148bd8896f034e4c3ab28
    // $2a$10$NllrZVuK6cREgtE3W6kYCe1lkxAW1P.Z91pec4zfNfihYjOZbIivG
  }
}
