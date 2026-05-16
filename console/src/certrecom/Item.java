package certrecom;

/**
 * 최상위 추상 클래스 - 캡슐화(encapsulation) + 다형성(polymorphism) 기반
 * 과목(Subject)과 자격증(Certification) 모두 이 클래스를 상속(inheritance)
 */
public abstract class Item {

    // 캡슐화: private 필드
    private String name;
    private String[] keywords;

    public Item(String name, String[] keywords) {
        this.name = name;
        this.keywords = keywords;
    }

    // Getters
    public String getName() { return name; }
    public String[] getKeywords() { return keywords; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setKeywords(String[] keywords) { this.keywords = keywords; }

    // 다형성: 하위 클래스가 반드시 구현
    public abstract String getType();
    public abstract String getSummary();

    @Override
    public String toString() {
        return "[" + getType() + "] " + name;
    }
}
