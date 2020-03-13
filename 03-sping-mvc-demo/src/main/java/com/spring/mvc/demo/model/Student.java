package com.spring.mvc.demo.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    private String firstName;
    private String lastName;
    private String country;

    private final Map<String, String> countryOptions;

    public Student() {
        countryOptions = new LinkedHashMap<>();

        countryOptions.put("BY", "Belarus");
        countryOptions.put("RU", "Russia");
        countryOptions.put("PL", "Poland");
    }

    public Map<String, String> getCountryOptions() {
        return Collections.unmodifiableMap(countryOptions);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
