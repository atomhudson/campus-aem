package com.campus.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class Contact {

    @Inject
    private String contactName;

    @Inject
    private String contactEmail;

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }
}
