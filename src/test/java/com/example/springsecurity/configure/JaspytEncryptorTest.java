package com.example.springsecurity.configure;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JaspytEncryptorTest {

  private StringEncryptor encryptor;

  @BeforeAll
  public void setUp() {
    encryptor = setUpEncryptor();
  }

  @Test
  @Disabled
  public void enc() {
    String password = "password1!";
    String encPassword = encryptor.encrypt(password);
    String decPassword = encryptor.decrypt(encPassword);

    System.out.println("encPassword: " + encPassword);

    assertThat(password).isEqualTo(decPassword);
  }

  @Test
  @Disabled
  public void dbPasswordEnc() {
    String password = "1234";
    String encPassword = encryptor.encrypt(password);
    String decPassword = encryptor.decrypt(encPassword);

    System.out.println("dbEncPassword: " + encPassword);

    assertThat(password).isEqualTo(decPassword);
  }

  private StringEncryptor setUpEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    config.setPassword("password1!");
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
    encryptor.setConfig(config);
    return encryptor;
  }
}
