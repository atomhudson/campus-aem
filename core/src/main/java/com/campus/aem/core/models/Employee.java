package com.campus.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Employee {

    @ValueMapValue
    private String name;

    @ValueMapValue
    private Integer age;

    @ValueMapValue
    private String type;

    @ValueMapValue
    private String department;

    @ValueMapValue
    private String[] skills;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public String getDepartment() {
        return department;
    }

    public String[] getSkills() {
        return skills;
    }
}
