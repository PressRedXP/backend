package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class PubFinderDao {
    private RestTemplate restTemplate;

    public PubFinderDao() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJacksonHttpMessageConverter messageConverter = new MappingJacksonHttpMessageConverter();
        messageConverters.add(messageConverter);

        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters);
        System.out.println("constructor complete");
    }

    public Place getPlace(double latitude, double longitude) {
        try {
            String URL = String.format("https://justmeet-pubfinder-service.herokuapp.com/pub/latitude/%f/longitude/%f", latitude, longitude);
            Place place = restTemplate.getForObject(URL, Place.class);
            return place;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace().toString());
            System.out.println(e.getCause());
        }

        return null;
    }

    public int getTest() {
        System.out.println("getTest method called");
        try {
            StatusResponse testResponse = restTemplate.getForObject("https://justmeet-pubfinder-service.herokuapp.com/status", StatusResponse.class);
            System.out.println(testResponse.code);
            return testResponse.code;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace().toString());
            System.out.println(e.getCause());
        }

        return 0;
    }
}
