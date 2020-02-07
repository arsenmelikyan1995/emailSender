package tools;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {

    final String FROMEMAIL = "test@gmail.com";
    final String PASSWORD = "testGmail";

    public MailAuthenticator() {

    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(FROMEMAIL, PASSWORD);
    }

}