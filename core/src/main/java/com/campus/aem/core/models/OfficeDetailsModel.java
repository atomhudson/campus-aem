package com.campus.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OfficeDetailsModel {

    @ValueMapValue
    private String locationName;

    @ChildResource
    private List<Employee> employees;

    public String getLocationName() {
        return locationName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
