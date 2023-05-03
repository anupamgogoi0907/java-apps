package com.acn;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0 || args.length == 1) {
            System.out.println("USAGE: Please provide YAML file path and Expected Properties file paths.");
        }
        String arg0 = args[0];
        String arg1 = args[1];

        parseYaml("/Users/anupam.gogoi.br/github/anupamgogoi0907/java-apps/jwt-validator/validate-prop/src/test/resources/B2B-Esteira1.yml");
    }

    public static List<String> getExpectedValues(String file) throws Exception {
        List<String> values = Files.readAllLines(Paths.get(file));
        return values;
    }

    public static List<String> parseYaml(String file) throws Exception {
        InputStream inputStream = new FileInputStream(new File(file));
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(inputStream);

        List<String> values = new ArrayList<>();
        parseRecursivelyYaml(map, values);
        return values;
    }

    public static void parseRecursivelyYaml(Object element, List<String> values) {
        if (element instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) element;
            for (String key : map.keySet()) {
                Object value = map.get(key);
                if (value instanceof Map) {
                    parseRecursivelyYaml(value, values);
                } else if (value instanceof Object) {
                    values.add(value.toString());
                }
            }
        }

    }

    public static void validateValues(List<String> currentValues,List<String> expectedValues) throws Exception {

    }
}