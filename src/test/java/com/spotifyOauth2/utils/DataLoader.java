package com.spotifyOauth2.utils;

import java.util.Properties;

public class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader()
    {
       properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance()
    {
        if(dataLoader==null)
        {
            dataLoader= new DataLoader();
        }
        return dataLoader;
    }

    public String getGetPlaylistId()
    {
       String getPlaylistId = properties.getProperty("get_playlist_id");
       if(getPlaylistId!=null)
       {
           return getPlaylistId;
       }else {
           throw new RuntimeException("playlist id is not specified in the data.properties file");
       }
    }

    public String getUpdatePlaylistId()
    {
        String updatePlaylistId = properties.getProperty("update_playlist_id");
        if(updatePlaylistId!=null)
        {
            return updatePlaylistId;
        }else {
            throw new RuntimeException("update playlist id is not specified in the data.properties file");
        }
    }

}
