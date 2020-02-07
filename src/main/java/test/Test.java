package test;

import tools.EmailSender;

import javax.mail.internet.AddressException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws AddressException {

        EmailSender es = new EmailSender();
         es.sendEmail("yourmail@gmail.com, yourmail2@ihost.am","YourName","Hello","Name");

        List<String> to = new ArrayList<String>();
        to.add("aa@gmail.com");
        to.add("bb@bb.am");

        List<String> cc = new ArrayList<String>();
        cc.add("vv@mail.ru");
        cc.add("cc@mail.ru");

        List<String> bcc = new ArrayList<String>();
        bcc.add("gg@yahoo.com");

        es.sendEmail("yourmail@mail.com", "New message", "text/html", "<div><span style=\\\"color:#57aaca;\\\">c</span></div>"
                , to, cc, bcc);

    }
}
