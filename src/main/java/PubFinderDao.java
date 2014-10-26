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
    }

    public int getTest() {
        return 0;
    }
}
