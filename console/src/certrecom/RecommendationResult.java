package certrecom;

import java.util.Set;

/**
 * 추천 결과 DTO - 자격증 + Jaccard 점수 + 공통 키워드
 * Comparable 구현으로 점수 내림차순 정렬 지원 (다형성 활용)
 */
public class RecommendationResult implements Comparable<RecommendationResult> {

    private Certification certification;
    private double jaccardScore;
    private Set<String> commonKeywords;

    public RecommendationResult(Certification certification,
                                 double jaccardScore,
                                 Set<String> commonKeywords) {
        this.certification = certification;
        this.jaccardScore = jaccardScore;
        this.commonKeywords = commonKeywords;
    }

    // Getters
    public Certification getCertification() { return certification; }
    public double getJaccardScore()         { return jaccardScore; }
    public Set<String> getCommonKeywords()  { return commonKeywords; }

    // 내림차순 정렬 (점수 높은 것이 앞)
    @Override
    public int compareTo(RecommendationResult other) {
        return Double.compare(other.jaccardScore, this.jaccardScore);
    }

    @Override
    public String toString() {
        return String.format("연관도: %5.1f%% | %s", jaccardScore, certification.getSummary());
    }
}
