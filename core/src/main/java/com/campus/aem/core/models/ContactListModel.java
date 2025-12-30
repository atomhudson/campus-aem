package com.campus.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContactListModel {

    @ChildResource(name = "contactdetails")
    private List<ContactItem> contacts;

    public List<ContactItem> getContacts() {
        return contacts;
    }

    @Model(adaptables = Resource.class)
    public static class ContactItem{

        @Inject
        private String name;
        @Inject
        private String designation;
        @Inject
        private String email;
        @Inject
        private String phone;
        public String getName() {
            return name;
        }

        public String getDesignation() {
            return designation;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }
    }

}
