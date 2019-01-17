package ua.com.deluxedostavka.tools;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class TelegramTool {

    public void sendNotification(String url){
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("Telegram response -> "+response);
    }

}
