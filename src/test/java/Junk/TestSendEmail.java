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
        email.setAuthenticator(new DefaultAuthenticator("iovets.mykola@pdffiller.team", "feedwteks")); // Set email/password
        email.setSSLOnConnect(true); // SSL true
        email.setFrom("iovets.mykola@pdffiller.team"); // set FROM
        email.setSubject("iovets.mykola@pdffiller.team"); // set Subject
        email.setMsg("This is a test mail ... :-)"); // Set message
        email.addTo("iovets.mykola+1@pdffiller.team"); // Set recipients
        email.send(); // Send Email
    }
}
