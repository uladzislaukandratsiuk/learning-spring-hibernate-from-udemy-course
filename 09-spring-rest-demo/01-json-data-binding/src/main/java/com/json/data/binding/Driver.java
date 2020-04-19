package com.json.data.binding;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {

    public static void main(String[] args) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper
                    .readValue(new File("data/sample-lite.json"), Student.class);

            System.out.println("Student info from json file -> " + student);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
