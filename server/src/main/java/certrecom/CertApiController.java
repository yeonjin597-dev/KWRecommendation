package certrecom;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CertApiController {

    private final RecommendationEngine engine;

    public CertApiController() {
        DataLoader loader = new DataLoader();
        List<Subject> subjects = new ArrayList<>(DataLoader.loadSubjects());
        this.engine = new RecommendationEngine(
            DataLoader.loadCertifications(),
            subjects
        );
    }

    // GET /api/certifications  →  모든 자격증 정보 JSON
    @GetMapping("/certifications")
    public List<Map<String, Object>> getCertifications() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Certification cert : engine.getCertifications()) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("name",             cert.getName());
            map.put("certType",         cert.getCertType());
            map.put("issuer",           cert.getIssuer());
            map.put("passRate",         cert.getPassRate());
            map.put("prepMonths",       cert.getPrepMonths());
            map.put("difficulty",       cert.getDifficulty());
            map.put("difficultyLabel",  cert.getDifficultyLabel());
            map.put("difficultyStars",  cert.getDifficultyStars());
            map.put("examScope",        cert.getExamScope());
            map.put("description",      cert.getDescription());
            list.add(map);
        }
        return list;
    }

    // GET /api/subjects  →  전체 과목 이름 목록
    @GetMapping("/subjects")
    public List<String> getSubjects() {
        List<String> names = new ArrayList<>();
        for (Subject s : engine.getSubjects()) {
            names.add(s.getName());
        }
        return names;
    }

    // POST /api/recommend
    // Body: { "subjects": ["과목1", "과목2", ...] }
    // Response: { "perSubject": [...], "ranking": [...] }
    @PostMapping("/recommend")
    public Map<String, Object> recommend(@RequestBody Map<String, List<String>> body) {
        List<String> selectedNames = body.getOrDefault("subjects", Collections.emptyList());

        // 선택된 과목 객체 수집
        List<Subject> selectedSubjects = new ArrayList<>();
        for (Subject s : engine.getSubjects()) {
            if (selectedNames.contains(s.getName())) {
                selectedSubjects.add(s);
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();

        // 과목별 상위 1개 추천
        List<Map<String, Object>> perSubject = new ArrayList<>();
        for (Subject s : selectedSubjects) {
            List<RecommendationResult> top1 = engine.recommend(s, 1);
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("subject", s.getName());
            if (!top1.isEmpty()) {
                RecommendationResult r = top1.get(0);
                item.put("certName",   r.getCertification().getName());
                item.put("score",      Math.round(r.getJaccardScore() * 10.0) / 10.0);
                item.put("commonKeywords", new ArrayList<>(r.getCommonKeywords()));
            } else {
                item.put("certName",   "-");
                item.put("score",      0.0);
                item.put("commonKeywords", Collections.emptyList());
            }
            perSubject.add(item);
        }
        response.put("perSubject", perSubject);

        // 통합 Jaccard 순위 (전체)
        List<RecommendationResult> ranking = engine.recommendByMultiple(selectedSubjects, 0);
        List<Map<String, Object>> rankList = new ArrayList<>();
        for (RecommendationResult r : ranking) {
            if (r.getJaccardScore() <= 0) continue;
            Certification cert = r.getCertification();
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("name",             cert.getName());
            item.put("score",            Math.round(r.getJaccardScore() * 10.0) / 10.0);
            item.put("commonKeywords",   new ArrayList<>(r.getCommonKeywords()));
            item.put("certType",         cert.getCertType());
            item.put("issuer",           cert.getIssuer());
            item.put("passRate",         cert.getPassRate());
            item.put("prepMonths",       cert.getPrepMonths());
            item.put("difficulty",       cert.getDifficulty());
            item.put("difficultyLabel",  cert.getDifficultyLabel());
            item.put("difficultyStars",  cert.getDifficultyStars());
            item.put("examScope",        cert.getExamScope());
            item.put("description",      cert.getDescription());
            rankList.add(item);
        }
        response.put("ranking", rankList);

        return response;
    }
}
