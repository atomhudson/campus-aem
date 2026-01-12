package com.campus.aem.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Campus Gmail Email Configuration",
        description = "Gmail SMTP configuration"
)
public @interface EmailConfig {
    @AttributeDefinition(name = "SMTP Host")
    String smtpHost() default "smtp.gmail.com";

    @AttributeDefinition(name = "SMTP Port")
    int smtpPort() default 587;

    @AttributeDefinition(name = "From Email")
    String fromEmail();

    @AttributeDefinition(name = "Gmail Username")
    String username();

    @AttributeDefinition(name = "Gmail App Password")
    String password();
}
