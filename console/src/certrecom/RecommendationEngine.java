package certrecom;

import java.util.*;

/**
 * 추천 엔진 - Jaccard 유사도 기반 자격증 추천 + 로드맵 출력
 */
public class RecommendationEngine {

    private List<Certification> certifications;
    private List<Subject> subjects;

    public RecommendationEngine(List<Certification> certifications, List<Subject> subjects) {
        this.certifications = certifications;
        this.subjects = subjects;
    }

    // Getters
    public List<Certification> getCertifications() { return certifications; }
    public List<Subject> getSubjects()             { return subjects; }

    // 단일 과목 기반 자격증 추천 (상위 N개)
    public List<RecommendationResult> recommend(Subject subject, int topN) {
        List<RecommendationResult> results = new ArrayList<>();

        for (Certification cert : certifications) {
            double score = JaccardCalculator.calculate(subject.getKeywords(), cert.getKeywords());
            Set<String> common = JaccardCalculator.getCommonKeywords(subject.getKeywords(), cert.getKeywords());
            results.add(new RecommendationResult(cert, score, common));
        }

        Collections.sort(results);
        return topN > 0 && results.size() > topN ? results.subList(0, topN) : results;
    }

    // 복수 과목 통합 기반 자격증 추천 (상위 N개)
    public List<RecommendationResult> recommendByMultiple(List<Subject> selectedSubjects, int topN) {
        Set<String> merged = new HashSet<>();
        for (Subject s : selectedSubjects) {
            if (s.getKeywords() != null) {
                merged.addAll(Arrays.asList(s.getKeywords()));
            }
        }
        String[] mergedArr = merged.toArray(new String[0]);

        List<RecommendationResult> results = new ArrayList<>();
        for (Certification cert : certifications) {
            double score = JaccardCalculator.calculate(mergedArr, cert.getKeywords());
            Set<String> common = JaccardCalculator.getCommonKeywords(mergedArr, cert.getKeywords());
            results.add(new RecommendationResult(cert, score, common));
        }

        Collections.sort(results);
        return topN > 0 && results.size() > topN ? results.subList(0, topN) : results;
    }

    // 자격증 이름으로 검색
    public Certification findByName(String query) {
        for (Certification cert : certifications) {
            if (cert.getName().contains(query) || query.contains(cert.getName())) {
                return cert;
            }
        }
        return null;
    }

    // 특정 자격증과 관련도 높은 과목 반환 (과목명 + 공통 키워드)
    public List<String> getRelatedSubjects(Certification cert, int topN) {
        List<double[]> scores = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
            Subject s = subjects.get(i);
            double score = JaccardCalculator.calculate(s.getKeywords(), cert.getKeywords());
            if (score > 0) scores.add(new double[]{i, score});
        }
        scores.sort((a, b) -> Double.compare(b[1], a[1]));

        List<String> result = new ArrayList<>();
        int limit = Math.min(topN, scores.size());
        for (int i = 0; i < limit; i++) {
            int idx = (int) scores.get(i)[0];
            Set<String> common = JaccardCalculator.getCommonKeywords(
                subjects.get(idx).getKeywords(), cert.getKeywords());
            result.add(subjects.get(idx).getName()
                + "  (공통 키워드: " + String.join(", ", common) + ")");
        }
        return result;
    }

    // 난이도별 로드맵 출력
    public void printRoadmap() {
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════════════");
        System.out.println("              IT 자격증 취득 로드맵  (난이도 1단계 → 3단계)");
        System.out.println("══════════════════════════════════════════════════════════════════════");

        Map<Integer, List<Certification>> grouped = new TreeMap<>();
        for (Certification cert : certifications) {
            grouped.computeIfAbsent(cert.getDifficulty(), k -> new ArrayList<>()).add(cert);
        }

        String[] labels = {"", "[★☆☆] 1단계 (하) - 입문", "[★★☆] 2단계 (중) - 중급", "[★★★] 3단계 (상) - 심화"};

        for (Map.Entry<Integer, List<Certification>> entry : grouped.entrySet()) {
            int level = entry.getKey();
            System.out.println();
            System.out.println("  " + labels[level]);
            System.out.println("  " + "─".repeat(65));
            for (Certification cert : entry.getValue()) {
                System.out.printf("  %-30s  %-14s  합격률: %5.1f%%  준비: %.1f개월%n",
                    cert.getName(), cert.getCertType(),
                    cert.getPassRate(), cert.getPrepMonths());
            }
        }

        System.out.println();
        System.out.println("  ※ 권장 취득 경로: 1단계 완료 후 2단계 → 2단계 완료 후 3단계 도전");
        System.out.println("══════════════════════════════════════════════════════════════════════");
    }
}
