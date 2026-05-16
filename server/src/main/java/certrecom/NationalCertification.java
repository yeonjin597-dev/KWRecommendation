package certrecom;

/**
 * 국가자격증 구체 클래스 - Certification 상속(inheritance)
 * 캡슐화: issuingAgency, hasWrittenExam, hasPracticalExam은 private
 * 다형성: getIssuer(), getCertType(), getSummary() 오버라이딩
 */
public class NationalCertification extends Certification {

    private String issuingAgency;    // 시행기관
    private boolean hasWrittenExam;  // 필기 시험 여부
    private boolean hasPracticalExam; // 실기 시험 여부

    public NationalCertification(String name, double passRate, double prepMonths,
                                  int difficulty, String examScope,
                                  String issuingAgency,
                                  boolean hasWrittenExam, boolean hasPracticalExam,
                                  String[] keywords) {
        super(name, passRate, prepMonths, difficulty, examScope, keywords);
        this.issuingAgency = issuingAgency;
        this.hasWrittenExam = hasWrittenExam;
        this.hasPracticalExam = hasPracticalExam;
    }

    // Getters
    public String getIssuingAgency()   { return issuingAgency; }
    public boolean hasWrittenExam()    { return hasWrittenExam; }
    public boolean hasPracticalExam()  { return hasPracticalExam; }

    // Setters
    public void setIssuingAgency(String agency) { this.issuingAgency = agency; }

    @Override
    public String getIssuer() { return issuingAgency; }

    @Override
    public String getCertType() { return "국가자격증"; }

    @Override
    public String getSummary() {
        String examInfo = "";
        if (hasWrittenExam)   examInfo += "필기";
        if (hasWrittenExam && hasPracticalExam) examInfo += "+";
        if (hasPracticalExam) examInfo += "실기";
        return super.getSummary() + " [" + examInfo + "]";
    }
}
