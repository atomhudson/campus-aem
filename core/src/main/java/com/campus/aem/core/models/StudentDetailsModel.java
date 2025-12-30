package com.campus.aem.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class StudentDetailsModel {
    @ValueMapValue
    private String name;

    @ValueMapValue
    private String age;

    @ValueMapValue
    private String studentClass;

    private String group;
    private int ageNumber;

    @PostConstruct
    protected void init() {
        if (age != null && !age.isEmpty()) {
            try {
                ageNumber = Integer.parseInt(age);
            } catch (NumberFormatException e) {
                ageNumber = 0;
            }
        } else {
            ageNumber = 0;
        }


        if (ageNumber == 0) {
            group = "";
        } else if (ageNumber <= 18) {
            group = "Young";
        } else if (ageNumber < 60) {
            group = "Adult";
        } else {
            group = "Aged";
        }
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return ageNumber;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getGroup() {
        return group;
    }

    public boolean isConfigured() {
        return name != null && !name.isEmpty();
    }
}
