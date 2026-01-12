package com.campus.aem.core.service.implementation;

import com.campus.aem.core.config.EmailConfig;
import com.campus.aem.core.service.EmailService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = EmailService.class,
        immediate = true
)
@Designate(ocd = EmailConfig.class)
public class EmailServiceImplementation implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImplementation.class);

    private EmailConfig config;

    @Activate
    @Modified
    protected void activate(EmailConfig config) {
        this.config = config;
        LOG.info("EmailService (LOG-ONLY MODE) activated");
    }

    @Override
    public void sendLoginSuccessMail(String toEmail) {
        if (config == null) {
            LOG.error("Email configuration is missing");
            return;
        }
        logPrettyEmail(toEmail);
    }


    private void logPrettyEmail(String toEmail) {
        LOG.info("=========== EMAIL SENT (MOCK) ===========");
        LOG.info("From    : {}", config.fromEmail());
        LOG.info("To      : {}", toEmail);
        LOG.info("SMTP    : {}:{}", config.smtpHost(), config.smtpPort());
        LOG.info("Subject : Login Successful");
        LOG.info("----------------------------------------");
        LOG.info("Hello,");
        LOG.info("You have successfully logged in to the application.");
        LOG.info("If this was not you, please contact support immediately.");
        LOG.info("Regards,");
        LOG.info("Campus Team");
        LOG.info("========================================");

    }
}
