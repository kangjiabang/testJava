package mockito;

public class AppServices {

    private EmailService emailService;

   /* public AppServices(EmailService emailService) {
        this.emailService = emailService;
    }*/


    public boolean sendEmail(String msg) {
        return emailService.send(msg);
    }
}
