package com.example;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class GeoipDemo {

    public static void main(String[] args) {
        try {
            // Path to your MaxMind database file
            File database = new File("/path/to/maxmind-database.mmdb");
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            // Use an IP address to look up
            InetAddress ipAddress = InetAddress.getByName("128.101.101.101");
            CityResponse response = reader.city(ipAddress);

            // Get country information
            String isoCode = response.getCountry().getIsoCode();
            System.out.println("Country ISO Code: " + isoCode);

            // Remember to close the reader
            reader.close();

        } catch (GeoIp2Exception | IOException e) {
            e.printStackTrace();
        }
    }
}
