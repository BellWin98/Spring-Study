package com.basic.core.singleton;

public class SingletonService {

    /**
     * 클래스 자기 자신을 내부에서 static 으로 가지고 있음 -> 변수가 클래스 레벨에 올라가기 때문에, static 영역에 딱 1개만 존재
     * Java가 실행될 때, 바로 생성됨
     */
    private static final SingletonService instance = new SingletonService();

    /**
     * public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
     * 이 메서드를 호출하면 항상 같은 인스턴스를 반환
     */

    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * 외부에서 new 키워드로 이 객체를 생성하지 못하도록 private 생성자 선언
     */
    private SingletonService() {}
}
