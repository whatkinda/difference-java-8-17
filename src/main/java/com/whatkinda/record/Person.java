package com.whatkinda.record;

/*
* 1. 간결한 데이터 전달 클래스(DTO) 선언을 위한 특별 클래스
* 2. 선언 시 데이터를 명시하면 데이터 전달 클래스를 위한 작업을 자동화
* 3. 데이터 전달 클래스를 위한 작업
* = 불변 데이터 + 값 기반 식별 메서드 + public 접근자
* (변수들을 private final 키워드로 취급 + equals, hashCode, toString, public 생성자, 접근자)
* 4. Class 와 유사하나 제약조건 존재
* = 암묵적 Final class
* = 인스턴스 변수 사용불가
* 5. Java 16 에 정식 반영
*
* */
public record Person(String name, int age) {
    static final String DEFAULT_NAME = "unknown";
    static final int DEFAULT_AGE = 0;

    /* 생성자 오버로딩: name 만 기본값 적용 */
    public Person(int age) {
        this(DEFAULT_NAME, age);
    }

    /* age 만 기본값 적용 */
    public Person(String name) {
        this(name, DEFAULT_AGE);
    }

    /* name 과 age 모두 기본값 적용 */
    public Person() {
        this(DEFAULT_NAME, DEFAULT_AGE);
    }

    // compact 생성자
    public Person {
        if(age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
    }
}
