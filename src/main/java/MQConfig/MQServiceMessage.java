package MQConfig;




import jakarta.jms.JMSSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.jms.MessageFormatException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQServiceMessage {

    private static final String QUEUE_NAME_ADD = "Training_ADD";
    private static final String QUEUE_NAME_DELETE = "Training_DELETE";


    private final JmsTemplate jmsTemplate;

    public MQServiceMessage(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) throws JMSSecurityException {
        try {
            jmsTemplate.convertAndSend(QUEUE_NAME_ADD, message);
            log.info("Message sent to queue {}: {}", QUEUE_NAME_ADD, message);

        } catch (MessageFormatException E){
            log.info("Failed to sent message to queve {} Error: {} ",QUEUE_NAME_DELETE, E.getMessage());
            throw E;
        } catch (JmsException E){
            log.error("Failed to send message to queue {}. Error: {}", QUEUE_NAME_ADD, E.getMessage());
            throw E;
        }

    }
    public void sendMessageDelete(String message)throws JMSSecurityException {
        try {
            jmsTemplate.convertAndSend(QUEUE_NAME_DELETE, message);
            log.info("Message sent to queue {}: {}", QUEUE_NAME_DELETE, message);

        } catch (MessageFormatException e){
            log.info("Failed to sent message to queve {} Error: {} ",QUEUE_NAME_DELETE, e.getMessage());
            throw e;
        } catch (JmsException E){
            log.error("Failed to send message to queue {}. Error: {}",QUEUE_NAME_DELETE, E.getMessage());
            throw E;
        }

    }
}