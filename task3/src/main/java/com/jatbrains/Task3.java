package com.jatbrains;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Task3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Use: 3 args <tests_file> <values_file> <report_file>");
            return;
        }

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        String reportFilePath = args[2];

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Загрузка тестов
            JsonNode testsNode = objectMapper.readTree(new File(testsFilePath));
            // Загрузка значений
            JsonNode valuesNode = objectMapper.readTree(new File(valuesFilePath));
            System.out.println(valuesNode);
            // Создание мапы для значений
            Map<String, String> valuesMap = new HashMap<>();
            for (JsonNode valueNode : valuesNode.get("values")) {
                String id = valueNode.get("id").asText();
                String value = valueNode.get("value").asText();
                valuesMap.put(id, value);
            }

            // Заполнение значений в тестах
            fillTestValues(testsNode.get("tests"), valuesMap);

            // Сохранение заполненного отчета
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testsNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillTestValues(JsonNode tests, Map<String, String> valuesMap) {
        for (JsonNode test : tests) {
            String id = test.get("id").asText();

            // Заполнение значения теста
            if (valuesMap.containsKey(id)) {
                ((ObjectNode) test).put("value", valuesMap.get(id));
            }

            // Проверка вложенных значений
            if (test.has("values")) {
                fillTestValues(test.get("values"), valuesMap);
            }
        }
    }
}

