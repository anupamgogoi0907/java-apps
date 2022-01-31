package app;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CheckEmail {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.err.println("Please provide properties file.");
            return;
        }
        String configFile = args[0];
        sendEmail(configFile);
    }

    public static void sendEmail(String configFile) {
        Session session = null;
        try {
            InputStream inputStream = new FileInputStream(configFile);
            Properties props = new Properties();
            props.load(inputStream);

            // 1. Authenticate.
            String userName = props.getProperty("mail.smtp.user");
            String password = props.getProperty("mail.smtp.password");
            System.out.println("Authenticating with user:" + userName);
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            };

            // 2. Create Session
            session = Session.getInstance(props, authenticator);

            // 3. Send dummy email.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(userName));
            message.setSubject("Check Email");
            message.setText("Hello World");
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
