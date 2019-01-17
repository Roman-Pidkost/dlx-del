package ua.com.deluxedostavka.dto.sms;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

    private String start_time;

    private String end_time;

    private Long lifetime;

    private Long rate;

    private String desc;

    private String source;

    private String body;

    private String recipient;

    public Message() {
    }

    public Message(String start_time, String end_time, Long lifetime, Long rate, String desc, String source, String body, String recipient) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.lifetime = lifetime;
        this.rate = rate;
        this.desc = desc;
        this.source = source;
        this.body = body;
        this.recipient = recipient;
    }

    public String getStart_time() {
        return start_time;
    }

    @XmlAttribute(name = "start_time")
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    @XmlAttribute(name = "end_time")
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Long getLifetime() {
        return lifetime;
    }

    @XmlAttribute(name = "lifetime")
    public void setLifetime(Long lifetime) {
        this.lifetime = lifetime;
    }

    public Long getRate() {
        return rate;
    }

    @XmlAttribute(name = "rate")
    public void setRate(Long rate) {
        this.rate = rate;
    }

    public String getDesc() {
        return desc;
    }

    @XmlAttribute(name = "desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    @XmlAttribute(name="source")
    public void setSource(String source) {
        this.source = source;
    }

    @XmlElement
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @XmlElement
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
