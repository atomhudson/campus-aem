package com.campus.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LoginForm {

    @ValueMapValue
    private String usernameLabel;
    @ValueMapValue
    private String passwordLabel;
    @ValueMapValue
    private String buttonText;

    public String getUsernameLabel() {
        return usernameLabel;
    }

    public String getPasswordLabel() {
        return passwordLabel;
    }

    public String getButtonText() {
        return buttonText;
    }
}
