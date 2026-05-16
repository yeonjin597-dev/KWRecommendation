package certrecom;

/**
 * 국제/민간자격증 구체 클래스 - Certification 상속(inheritance)
 * 캡슐화: issuingOrg, language, isOnline은 private
 * 다형성: getIssuer(), getCertType(), getSummary() 오버라이딩
 */
public class InternationalCertification extends Certification {

    private String issuingOrg;  // 발급 기관 (예: Google, AWS, KT)
    private String language;    // 시험 언어
    private boolean isOnline;   // 온라인 시험 여부

    public InternationalCertification(String name, double passRate, double prepMonths,
                                       int difficulty, String examScope,
                                       String issuingOrg, String language, boolean isOnline,
                                       String[] keywords) {
        super(name, passRate, prepMonths, difficulty, examScope, keywords);
        this.issuingOrg = issuingOrg;
        this.language = language;
        this.isOnline = isOnline;
    }

    // Getters
    public String getIssuingOrg() { return issuingOrg; }
    public String getLanguage()   { return language; }
    public boolean isOnline()     { return isOnline; }

    // Setters
    public void setIssuingOrg(String org)   { this.issuingOrg = org; }
    public void setLanguage(String language) { this.language = language; }
    public void setOnline(boolean online)    { this.isOnline = online; }

    @Override
    public String getIssuer() { return issuingOrg; }

    @Override
    public String getCertType() {
        return isOnline ? "국제자격증(온라인)" : "국제자격증";
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " [" + language + "]";
    }
}
