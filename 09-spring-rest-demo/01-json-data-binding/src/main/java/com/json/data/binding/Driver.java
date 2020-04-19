package com.json.data.binding;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {

    public static void main(String[] args) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper
                    .readValue(new File("data/sample-full.json"), Student.class);

            System.out.println("Student info from json file -> " + student);
            System.out.println("Student address info from json file -> " + student.getAddress());
            System.out.print("Student languages info from json file -> ");

            for (String language : student.getLanguages()) {
                System.out.print(language + "; ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
