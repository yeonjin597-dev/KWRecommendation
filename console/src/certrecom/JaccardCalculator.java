package certrecom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Jaccard 유사도 계산 유틸리티
 * 공식: 연관도 = (과목키워드 ∩ 자격증키워드) / (과목키워드 ∪ 자격증키워드) × 100
 */
public class JaccardCalculator {

    // 단일 과목 vs 자격증 Jaccard 유사도 계산
    public static double calculate(String[] setA, String[] setB) {
        if (setA == null || setB == null || setA.length == 0 || setB.length == 0) {
            return 0.0;
        }

        Set<String> a = new HashSet<>(Arrays.asList(setA));
        Set<String> b = new HashSet<>(Arrays.asList(setB));

        Set<String> intersection = new HashSet<>(a);
        intersection.retainAll(b);  // 교집합

        Set<String> union = new HashSet<>(a);
        union.addAll(b);            // 합집합

        if (union.isEmpty()) return 0.0;

        return (double) intersection.size() / union.size() * 100.0;
    }

    // 복수 과목 통합 키워드 vs 자격증 Jaccard 유사도 계산
    public static double calculateMulti(String[] mergedSubjectKeywords, String[] certKeywords) {
        return calculate(mergedSubjectKeywords, certKeywords);
    }

    // 공통 키워드(교집합) 반환
    public static Set<String> getCommonKeywords(String[] setA, String[] setB) {
        if (setA == null || setB == null) return new HashSet<>();
        Set<String> a = new HashSet<>(Arrays.asList(setA));
        Set<String> b = new HashSet<>(Arrays.asList(setB));
        Set<String> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        return intersection;
    }
}
