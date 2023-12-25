package com.whatkinda.var;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* var 동적타입 (java 10 도입)
* 1. 지역변수 선언 시 타입을 명시적으로 지정하지 않고도
* 컴파일러가 초기화 식을 통해 변수의 타입을 추론 (runtime 진행 x. compile 시점에 컴파일러가 맥락 파악)
*
* 타입 추론: compiler can infer the type based on surrounding context.
*
* Use cases
* 1. 초기화 블록 내 변수
* 2. 메서드 지역변수
* 3. for문 iteration 변수
* 4. 함수의 반환값이 표현식일 때
* 5. 함수의 반환값으로 사용할 때
*
* Error cases
* 1. 초기화가 안된 지역 변수
* 2. 메서드 반환값 타입
* 3. null 로 초기화된 지역변수
* 4. 메서드 파라미터
* */
public class Main {
    public static void main(String[] args) {
        practice_one();
        System.out.println("----");
        practice_two();
        System.out.println("----");
        practice_thr();
        System.out.println("----");
        practice_four();

    }

    static void practice_one() {
        String sentence = "A simple Java example that explores what Java 10 has to offer";
        Map<String, String> testMap = new HashMap<>();
        testMap.put("test1", "1");
        testMap.put("test2", "2");
        testMap.put("test3", "3");

        var byOccurance = new HashMap<String, Object>(testMap);
        System.out.println("class : " + byOccurance.getClass());
    }

    static void practice_two() {
        var str = "What type is this?";
        System.out.println(str instanceof String);  // true 출력
    }

    static void practice_thr() {
        /*
        Map<String, List<String>> countryCitiesMap  = new HashMap<>();

        countryCitiesMap.put("Seoul", List.of(new String[] {"Sangdo-1-dong", "Banpo-dong", "Sillim-dong", "Heukseuk-dong"}));
        countryCitiesMap.put("Daejeon", List.of(new String[] {"Eoeun-dong", "Goong-dong", "Mannyeon-dong"}));
        countryCitiesMap.put("Busan", List.of(new String[] {"Nampo-dong", "Daeyeon-dong"}));


        for(Map.Entry<String, List<String>> entry : countryCitiesMap.entrySet()) {
            String country = entry.getKey();
            List<String> cities= entry.getValue();

            for (String city : cities) {
                // process city
            }
        }
        */

        Map<String, List<City>> countryCitiesMap = new HashMap<>();

        countryCitiesMap.put("Seoul", List.of(new City[] { new City("Sangdo-1-dong"), new City("Banpo-dong") }));
        countryCitiesMap.put("Daejeon", List.of(new City[] { new City("Eoeun-dong"), new City("Goong-dong") }));
        countryCitiesMap.put("Busan", List.of(new City[] { new City("Nampo-dong"), new City("Daeyeon-dong") }));

        for(var entry : countryCitiesMap.entrySet()) {
            String country = entry.getKey();
            List<City> cities = entry.getValue();

            for(var city : cities) {
                System.out.println(country + " 의 city = " + city.getName());
            }
            System.out.println();
        }
    }

    static void practice_four() {
        var cities = List.of(
                new City("Busan"),
                new City("Daegu"),
                new City("Gwangju"),
                new City("Incheon")
        );

        var citiesInfo = cities
                .stream()
                .map(city -> new Object() {
                    String name = city.getName();
                    int charCounts = name.length();
                })
                .collect(Collectors.toList());

        citiesInfo.forEach(city ->
                System.out.println("name = " + city.name + ", charCounts = " + city.charCounts));
    }

}

class City {
    private String name;
    private int charCounts;

    public City(String name) {
        this.name = name;
    }

    public City(String name, int charCounts) {
        this.name = name;
        this.charCounts = charCounts;
    }

    public String getName() {
        return name;
    }

    public int getCharCounts() {
        return charCounts;
    }
}
