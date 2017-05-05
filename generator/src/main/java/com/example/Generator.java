package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import static java.lang.System.out;

public class Generator {

    public static void main(String[] args) {
        try {
            new Generator().generator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generator() throws IOException {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(getClass(), "/");
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        File templateDir = new File("generator/src/main/resources/");
        config.setDirectoryForTemplateLoading(templateDir);

        Template template = config.getTemplate("person.ftl");

        Map<String, Object> root = new HashMap<>();
        root.put("packageName", "com.example.entity");
        root.put("className", "Person");
        root.put("author", "cherry");

        List<Attribute> attrs = new ArrayList<>();
        attrs.add(new Attribute("id", "Long"));
        attrs.add(new Attribute("name", "String"));
        attrs.add(new Attribute("age", "int"));
        attrs.add(new Attribute("hobby", "List<String>"));

        out.printf("attrs size %d\n", attrs.size());

        root.put("attrs", attrs);

        File dir = new File("generator/src/main/java/com/example/entity");
        if (!dir.exists())
            dir.mkdirs();
        FileWriter writer = new FileWriter(new File(dir, "Person.java"));
        try {
            template.process(root, writer);
            writer.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }
}
