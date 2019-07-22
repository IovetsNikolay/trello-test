package Junk;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;

public class TestSendEmail {

    @Test
    public void mailTest() throws EmailException {
        Email email = new SimpleEmail(); // Create Object
        email.setHostName("smtp.googlemail.com"); // Set SMTP hostname
        email.setSmtpPort(465); // Set port
        email.setAuthenticator(new DefaultAuthenticator("iovetsnikolay@gmail.com", "feedwteks2019")); // Set email/password
        email.setSSLOnConnect(true); // SSL true
        email.setFrom("iovetsnikolay@gmail.com"); // set FROM
        email.setSubject("Some subj"); // set Subject
        email.setMsg("This is a test mail ... :-)"); // Set message
        email.addTo("iovets.mykola@pdffiller.team"); // Set recipients
        email.send(); // Send Email
    }
}
