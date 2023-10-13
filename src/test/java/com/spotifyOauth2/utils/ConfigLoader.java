package com.spotifyOauth2.utils;

import java.util.Properties;

//here we need to follow singleton design pattern to make sure that the properties are loaded only once throughout the Framework
public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    //we need to create private constructor so that we are not able to create instance/object of the class outside the class
    private ConfigLoader()
    {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance()
    {
        if(configLoader==null)
        {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId()
    {
        String clientId = properties.getProperty("client_id");
        if(clientId!=null)
        {
            return clientId;
        }
        else{
            throw new RuntimeException("client id is not specified in the config.properties file");
        }
    }

    public String getClientSecret()
    {
        String clientSecret = properties.getProperty("client_secret");
        if(clientSecret!=null)
        {
            return clientSecret;
        }else {
            throw new RuntimeException("client id is not specified in the config.properties file");
        }
    }

    public String getRefreshToken()
    {
        String refreshToken = properties.getProperty("refresh_token");
        if(refreshToken!=null)
        {
            return refreshToken;
        }
        else {
            throw new RuntimeException("Refresh Token is not specified in the config.properties file");
        }
    }

    public String getGrantType()
    {
        String grantType = properties.getProperty("grant_type");
        if(grantType!=null)
        {
            return grantType;
        }else {
            throw new RuntimeException("Grant type is not specified in the config.properties file");
        }
    }

    public String getUserId()
    {
       String userId = properties.getProperty("user_id");
       if(userId!=null)
       {
           return userId;
       }else {
           throw new RuntimeException("User Id is not specified in the config.properties file");
       }
    }

}
