package com.test.employess.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.employess.entity.Employees;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectUtil {
    private static final String FILE_PATH = "employees.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Integer saveEmployeesToFile(List<Employees> employees) throws IOException {
        File file = new File(FILE_PATH);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employees);
        return employees.size();
    }

    public static List<Employees> getEmployeesFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Employees>>() {
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
