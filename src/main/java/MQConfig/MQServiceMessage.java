package MQConfig;



import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MQServiceMessage {

    private static final String QUEUE_NAME_ADD = "Training_ADD";
    private static final String QUEUE_NAME_DELETE = "Training_DELETE";


    private final JmsTemplate jmsTemplate;

    public MQServiceMessage(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(QUEUE_NAME_ADD, message);
    }
    public void sendMessageDelete(String message) {
        jmsTemplate.convertAndSend(QUEUE_NAME_DELETE, message);
    }
}