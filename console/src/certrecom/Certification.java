package certrecom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 자격증 추상 클래스 - Item 상속(inheritance)
 * 캡슐화: passRate, prepMonths, difficulty, examScope는 private
 * 다형성: getIssuer(), getCertType()은 하위 클래스에서 구현
 */
public abstract class Certification extends Item {

    private double passRate;      // 합격률 (%)
    private double prepMonths;    // 준비기간 (개월)
    private int difficulty;       // 난이도 1~3
    private String examScope;     // 시험 범위
    private String description;   // 자격증 한 줄 설명

    public Certification(String name, double passRate, double prepMonths,
                         int difficulty, String examScope, String[] keywords) {
        super(name, keywords);
        this.passRate = passRate;
        this.prepMonths = prepMonths;
        this.difficulty = difficulty;
        this.examScope = examScope;
        this.description = "";
    }

    // Getters
    public double getPassRate()     { return passRate; }
    public double getPrepMonths()   { return prepMonths; }
    public int getDifficulty()      { return difficulty; }
    public String getExamScope()    { return examScope; }
    public String getDescription()  { return description; }

    // Setters
    public void setPassRate(double passRate)       { this.passRate = passRate; }
    public void setPrepMonths(double prepMonths)   { this.prepMonths = prepMonths; }
    public void setDifficulty(int difficulty)      { this.difficulty = difficulty; }
    public void setExamScope(String examScope)     { this.examScope = examScope; }
    public void setDescription(String description) { this.description = description; }

    // 다형성: 하위 클래스에서 구현
    public abstract String getIssuer();
    public abstract String getCertType();

    @Override
    public String getType() { return "자격증"; }

    public String getDifficultyStars() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < difficulty; i++) sb.append("★");
        for (int i = difficulty; i < 3; i++) sb.append("☆");
        return sb.toString();
    }

    public String getDifficultyLabel() {
        switch (difficulty) {
            case 1: return "하";
            case 2: return "중";
            case 3: return "상";
            default: return "?";
        }
    }

    @Override
    public String getSummary() {
        return String.format("%-30s | 합격률: %5.1f%% | 준비: %4.1f개월 | 난이도: %s(%s) | %s",
            getName(), passRate, prepMonths,
            getDifficultyStars(), getDifficultyLabel(), getCertType());
    }
}
