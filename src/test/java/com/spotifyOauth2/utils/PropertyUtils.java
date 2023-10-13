package com.spotifyOauth2.utils;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filePath)
    {
        //// Create a Properties object to store key-value pairs from the properties file.
        Properties properties = new Properties();

        // Declare a BufferedReader for reading the properties file.
        BufferedReader reader;
        try
        {
            // Create a BufferedReader to read from the specified file path.
            reader = new BufferedReader(new FileReader(filePath));
            try
            {
                // Load the properties from the reader into the 'properties' object.
                properties.load(reader);

                // Close the reader after loading properties.
                reader.close();
            }
            catch (IOException e)
            {
                // Handle IOException if there is an issue while loading properties.
                e.printStackTrace();

                // Throw a RuntimeException with an error message.
                throw new RuntimeException("Failed to load property file "+filePath);
            }
        }
        catch (FileNotFoundException e)
        {
            // Handle FileNotFoundException if the specified file is not found.
            e.printStackTrace();

            // Throw a RuntimeException with an error message.
            throw new RuntimeException("Properties file not fount at "+filePath);
        }
        // Return the 'properties' object containing the loaded properties.
        return properties;
    }


}
