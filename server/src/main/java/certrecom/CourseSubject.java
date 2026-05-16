package certrecom;

/**
 * 수강 과목 구체 클래스 - Subject 상속
 * 자료4의 30개 과목을 표현
 */
public class CourseSubject extends Subject {

    private String department;
    private String description;

    public CourseSubject(String name, String courseCode, int credits,
                         String department, String[] keywords) {
        super(name, courseCode, credits, keywords);
        this.department = department;
        this.description = "";
    }

    // Getters
    public String getDepartment() { return department; }
    public String getDescription() { return description; }

    // Setters
    public void setDepartment(String department) { this.department = department; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String getSummary() {
        return String.format("%-26s | 학점: %d | 학과: %s | 코드: %s",
            getName(), getCredits(), department, getCourseCode());
    }
}
