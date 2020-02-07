package tools;

import java.util.Collection;
import java.util.List;

public interface IEmailSender {

    public void sendEmail(String toEmail, String receipentName, String text, String senderName);

    public void sendEmail(String from, String subject, String mimeType, String body, Collection<String> to, Collection<String> cc, Collection<String> bcc);

    public String getList(Collection<String> collectionList);

}
