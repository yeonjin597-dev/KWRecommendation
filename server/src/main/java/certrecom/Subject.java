package certrecom;

/**
 * 과목 추상 클래스 - Item 상속(inheritance)
 * 캡슐화: courseCode, credits, professor는 private
 * 다형성: getDepartment()는 하위 클래스에서 구현
 */
public abstract class Subject extends Item {

    private String courseCode;
    private int credits;
    private String professor;

    public Subject(String name, String courseCode, int credits, String[] keywords) {
        super(name, keywords);
        this.courseCode = courseCode;
        this.credits = credits;
        this.professor = "미지정";
    }

    // Getters
    public String getCourseCode() { return courseCode; }
    public int getCredits() { return credits; }
    public String getProfessor() { return professor; }

    // Setters
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setProfessor(String professor) { this.professor = professor; }

    @Override
    public String getType() { return "과목"; }

    // 다형성: 하위 클래스가 구현
    public abstract String getDepartment();
}
