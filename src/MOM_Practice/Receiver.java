package MOM_Practice;

import javax.jms.*;
import javax.naming.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	// URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"
 
    // Name of the queue we will receive messages from
    private static String subject = "JCG_QUEUE";
 
    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
 
        // Creating session for seding messages
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
 
        // Getting the queue 'JCG_QUEUE'
        Destination destination = session.createQueue(subject);
 
        // MessageConsumer is used for receiving (consuming) messages
        MessageConsumer consumer = session.createConsumer(destination);
 
        // Here we receive the message.
        Message message = consumer.receive();
 
        // We will be using TestMessage in our example. MessageProducer sent us a TextMessage
        // so we must cast to it to get access to its .getText() method.
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("���������� ������'" + textMessage.getText() + "'");
        }
        connection.close();
    }

}
