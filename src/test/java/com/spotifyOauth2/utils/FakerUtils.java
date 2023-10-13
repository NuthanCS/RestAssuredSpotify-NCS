package com.spotifyOauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName()
    {
        Faker faker = new Faker();
        return "Playlist "+ faker.regexify("[a-bA-B0-9 ,_-][10]");// this the regex expression we can get it from the dev team or we can enter by our own based on the requirement.
    }

    public static String generateDescription()
    {
        Faker faker = new Faker();
        return "Description "+ faker.regexify("[a-bA-B0-9 ,_-][20]");
    }
}
