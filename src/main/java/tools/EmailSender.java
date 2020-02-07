package tools;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;

public class EmailSender implements IEmailSender {

    @Override
    public void sendEmail(String toEmail, String receipentName, String text, String senderName) {

        final String FROMEMAIL = "test@gmail.com";
        final String PASSWORD = "testGmail";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        /*for tls
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        */


        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROMEMAIL, PASSWORD);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROMEMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Your Message");
            message.setText("Dear " + receipentName + "\n" + text + "\n" + "Regards" + "\n" + senderName);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmail(String from, String subject, String mimeType, String body, Collection<String> to, Collection<String> cc, Collection<String> bcc) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        /*
        for tls
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        */

        Session session = Session.getInstance(properties, new MailAuthenticator());

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(getList(to)));

            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(getList(cc)));

            message.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(getList(bcc)));

            message.setSubject(subject);
            message.setContent(body, mimeType);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getList(Collection<String> collectionList) {
        List<String> list = new ArrayList<>(collectionList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == list.get(list.size() - 1)) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}

