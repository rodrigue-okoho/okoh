package com.okoho.okoho.utils;

 import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.context.annotation.PropertySource;
 import org.springframework.context.annotation.PropertySources;
 import org.springframework.web.cors.CorsConfiguration;
 
 import javax.validation.constraints.NotNull;
 import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
 import java.util.List;
 import java.util.Map;
 
 /**
  * Properties specific to JHipster.
  *
  * <p> Properties are configured in the application.yml file. </p>
  * <p> This class also load properties in the Spring Environment from the git.properties and META-INF/build-info.properties
  * files if they are found in the classpath.</p>
  */
 @ConfigurationProperties(prefix = "jhipster", ignoreUnknownFields = false)
 @PropertySources({
     @PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true),
     @PropertySource(value = "classpath:META-INF/build-info.properties", ignoreResourceNotFound = true)
 })
 public class JHipsterProperties {
 
     private final Http http = new Http();
 
     private final Database database = new Database();

 
     private final Security security = new Security();
 
 
     private final CorsConfiguration cors = new CorsConfiguration();
 
 
     /**
      * <p>Getter for the field <code>http</code>.</p>
      *
      * @return a {@link io.github.jhipster.config.JHipsterProperties.Http} object.
      */
     public Http getHttp() {
         return http;
     }
 
     /**
      * <p>Getter for the field <code>database</code>.</p>
      *
      * @return a {@link io.github.jhipster.config.JHipsterProperties.Database} object.
      */
     public Database getDatabase() {
         return database;
     }
 
    
     /**
      * <p>Getter for the field <code>security</code>.</p>
      *
      * @return a {@link io.github.jhipster.config.JHipsterProperties.Security} object.
      */
     public Security getSecurity() {
         return security;
     }
 
     /**
      * <p>Getter for the field <code>cors</code>.</p>
      *
      * @return a {@link org.springframework.web.cors.CorsConfiguration} object.
      */
     public CorsConfiguration getCors() {
         return cors;
     }

 
   
     public static class Http {
 
         private final Cache cache = new Cache();
 
         public Cache getCache() {
             return cache;
         }
 
         public static class Cache {
 
             private int timeToLiveInDays = 18000;
 
             public int getTimeToLiveInDays() {
                 return timeToLiveInDays;
             }
 
             public void setTimeToLiveInDays(int timeToLiveInDays) {
                 this.timeToLiveInDays = timeToLiveInDays;
             }
         }
     }
 
     public static class Database {
 
         private final Couchbase couchbase = new Couchbase();
 
         public Couchbase getCouchbase() {
             return couchbase;
         }
 
         public static class Couchbase {
 
             private String bucketName;
 
             public String getBucketName() {
                 return bucketName;
             }
 
             public Couchbase setBucketName(String bucketName) {
                 this.bucketName = bucketName;
                 return this;
             }
         }
     }
 
  
   public static class Security {
 
        
         private final Authentication authentication = new Authentication();
 
         private final RememberMe rememberMe = new RememberMe();
 
         private final OAuth2 oauth2 = new OAuth2();
 
    
 
         public Authentication getAuthentication() {
             return authentication;
         }
 
         public RememberMe getRememberMe() {
             return rememberMe;
         }
 
         public OAuth2 getOauth2() {
             return oauth2;
         }
 
        /*  public static class ClientAuthorization {
 
             private String accessTokenUri = JHipsterDefaults.Security.ClientAuthorization.accessTokenUri;
 
             private String tokenServiceId = JHipsterDefaults.Security.ClientAuthorization.tokenServiceId;
 
             private String clientId = JHipsterDefaults.Security.ClientAuthorization.clientId;
 
             private String clientSecret = JHipsterDefaults.Security.ClientAuthorization.clientSecret;
 
             public String getAccessTokenUri() {
                 return accessTokenUri;
             }
 
             public void setAccessTokenUri(String accessTokenUri) {
                 this.accessTokenUri = accessTokenUri;
             }
 
             public String getTokenServiceId() {
                 return tokenServiceId;
             }
 
             public void setTokenServiceId(String tokenServiceId) {
                 this.tokenServiceId = tokenServiceId;
             }
 
             public String getClientId() {
                 return clientId;
             }
 
             public void setClientId(String clientId) {
                 this.clientId = clientId;
             }
 
             public String getClientSecret() {
                 return clientSecret;
             }
 
             public void setClientSecret(String clientSecret) {
                 this.clientSecret = clientSecret;
             }
         } */
 
         public static class Authentication {
 
             private final Jwt jwt = new Jwt();
 
             public Jwt getJwt() {
                 return jwt;
             }
 
             public static class Jwt {
               
                 private String secret = "okoho";
 
                 private String base64Secret = "okoho";
 
                 private long tokenValidityInSeconds = 3600;
 
                 private long tokenValidityInSecondsForRememberMe = 3600;
 
                 public String getSecret() {
                     return secret;
                 }
 
                 public void setSecret(String secret) {
                     this.secret = secret;
                 }
 
                 public String getBase64Secret() {
                     return base64Secret;
                 }
 
                 public void setBase64Secret(String base64Secret) {
                     this.base64Secret = base64Secret;
                 }
 
                 public long getTokenValidityInSeconds() {
                     return tokenValidityInSeconds;
                 }
 
                 public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                     this.tokenValidityInSeconds = tokenValidityInSeconds;
                 }
 
                 public long getTokenValidityInSecondsForRememberMe() {
                     return tokenValidityInSecondsForRememberMe;
                 }
 
                 public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                     this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                 }
             }
         }
 
         public static class RememberMe {
 
             @NotNull
             private String key = "";
 
             public String getKey() {
                 return key;
             }
 
             public void setKey(String key) {
                 this.key = key;
             }
         }
 
         public static class OAuth2 {
             private List<String> audience = new ArrayList<>();
 
             public List<String> getAudience() {
                 return Collections.unmodifiableList(audience);
             }
 
             public void setAudience(@NotNull List<String> audience) {
                 this.audience.addAll(audience);
             }
         }
     }
 }