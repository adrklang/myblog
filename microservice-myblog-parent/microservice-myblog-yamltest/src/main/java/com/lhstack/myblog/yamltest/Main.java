package com.lhstack.myblog.yamltest;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream yamlIn = classLoader.getResourceAsStream("bootstrap.yml");
        Yaml yaml = new Yaml();
        Map map = yaml.loadAs(yamlIn, Map.class);
        Map nacos = (Map) map.get("nacos");
        System.out.println(nacos.get("namespace"));
        Object spring = map.get("spring");
        System.out.println(spring);
        System.out.println(map);
    }
}
