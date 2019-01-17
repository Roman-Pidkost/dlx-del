package ua.com.deluxedostavka.dto.sms;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {

    private String operation;

    private Message message;

    public Request() {
    }

    public Request(String operation, Message message) {
        this.operation = operation;
        this.message = message;
    }

    @XmlElement
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @XmlElement
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
