package ua.com.deluxedostavka.tools;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.deluxedostavka.dto.sms.Message;
import ua.com.deluxedostavka.dto.sms.Request;
import javax.xml.bind.JAXBException;
import java.util.Base64;

@Component
public class SmsSender {


    public void sendSms( String name,String phone,Long sum ) throws JAXBException {
        String body = "Нове замовлення на суму : "+sum+" грн "+"Контакти - "+phone+" - "+name;
        Request request = new Request("SENDSMS",new Message("AUTO","AUTO",4L,120L,"My first campaign","DeLuxe Cafe",body,"380970019900"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Authorization","Basic "+ new String(Base64.getEncoder().encode("380935211662:28051997Yarik".getBytes())));
        headers.setContentType(MediaType.TEXT_XML);
        HttpEntity<Request> requestBody = new HttpEntity<Request>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.postForEntity("http://svitsms.com/api/api.php", requestBody , String.class));
//        JAXBContext jaxbContext = JAXBContext.newInstance( Request.class );
//        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
//        jaxbMarshaller.marshal(request,System.out);

    }
}
