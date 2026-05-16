package certrecom;

import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in, "UTF-8");
    private static RecommendationEngine engine;
    private static List<Subject> selectedSubjects = new ArrayList<>(); // 메뉴2 입력 상태 유지

    public static void main(String[] args) {
        List<CourseSubject> courses = DataLoader.loadSubjects();
        List<Certification> certs   = DataLoader.loadCertifications();
        engine = new RecommendationEngine(new ArrayList<>(certs), new ArrayList<>(courses));

        System.out.println();
        System.out.println("  ================================");
        System.out.println("      IT 자격증 추천 시스템");
        System.out.println("  ================================");

        boolean running = true;
        while (running) {
            printMenu();
            String input = sc.nextLine().trim();
            System.out.println();
            switch (input) {
                case "1": menuAllCertifications(); break;
                case "2": menuSelectSubjects();    break;
                case "3": menuRecommendRoadmap();  break;
                case "4": menuDetail();            break;
                case "5": running = false;         break;
                default:
                    System.out.println("  1~5 사이의 번호를 입력하세요.");
                    System.out.println();
            }
        }
        System.out.println("  프로그램을 종료합니다.");
        sc.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("  --------------------------------");
        if (!selectedSubjects.isEmpty()) {
            StringJoiner sj = new StringJoiner(", ");
            for (Subject s : selectedSubjects) sj.add(s.getName());
            System.out.println("  [입력된 과목: " + sj + "]");
        }
        System.out.println("  1. 전체 자격증 목록 보기");
        System.out.println("  2. 수강과목 입력하기");
        System.out.println("  3. 추천 자격증 로드맵");
        System.out.println("  4. 자격증 상세 정보 조회");
        System.out.println("  5. 종료");
        System.out.println("  --------------------------------");
        System.out.print("  선택 >> ");
    }

    // 한글(CJK)=2칸, 그 외(★●○ 포함)=1칸 기준 실제 출력 너비 계산
    private static int displayWidth(String s) {
        int w = 0;
        for (char c : s.toCharArray()) {
            if ((c >= 0xAC00 && c <= 0xD7AF) ||  // 한글 음절
                (c >= 0x1100 && c <= 0x11FF) ||  // 한글 자모
                (c >= 0x3130 && c <= 0x318F) ||  // 한글 호환 자모
                (c >= 0x4E00 && c <= 0x9FFF) ||  // CJK 한자
                (c >= 0xFF01 && c <= 0xFF60)) {   // 전각 문자
                w += 2;
            } else {
                w += 1;
            }
        }
        return w;
    }

    // 연관도(0~100%) → 1~5 단계 ●●●○○ 표시
    private static String toRelevanceBar(double score) {
        int level;
        if      (score < 10) level = 1;
        else if (score < 25) level = 2;
        else if (score < 45) level = 3;
        else if (score < 65) level = 4;
        else                 level = 5;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++)     sb.append("●");
        for (int i = level; i < 5; i++)     sb.append("○");
        return sb.toString();
    }

    // 지정 너비에 맞게 우측 공백 패딩
    private static String pad(String s, int width) {
        int space = Math.max(0, width - displayWidth(s));
        return s + " ".repeat(space);
    }

    // ── 메뉴 1: 전체 자격증 목록 ─────────────────────────────────────────

    private static void menuAllCertifications() {
        List<Certification> all = engine.getCertifications();
        System.out.println("  전체 자격증 목록  (" + all.size() + "개)");
        System.out.println("  " + "=".repeat(74));
        System.out.println("  " + pad("No", 4)
                         + pad("자격증명", 34)
                         + pad("합격률", 8)
                         + pad("준비기간", 10)
                         + pad("난이도", 9)
                         + "유형");
        System.out.println("  " + "-".repeat(74));
        int no = 1;
        for (Certification cert : all) {
            System.out.println("  "
                + pad(no++ + ".", 4)
                + pad(cert.getName(), 34)
                + pad(String.format("%.1f%%", cert.getPassRate()), 8)
                + pad(String.format("%.1f개월", cert.getPrepMonths()), 10)
                + pad(cert.getDifficultyStars() + "(" + cert.getDifficultyLabel() + ")", 9)
                + cert.getCertType());
        }
        System.out.println("  " + "=".repeat(74));
        System.out.println();
    }

    // ── 메뉴 2: 수강과목 입력하기 (누적 추가 방식) ──────────────────────

    private static void menuSelectSubjects() {
        List<Subject> subjects = engine.getSubjects();

        // 현재 선택 현황 표시
        if (!selectedSubjects.isEmpty()) {
            System.out.println("  현재 선택된 과목 (" + selectedSubjects.size() + "개):");
            for (int i = 0; i < selectedSubjects.size(); i++) {
                System.out.printf("    %2d. %s%n", i + 1, selectedSubjects.get(i).getName());
            }
            System.out.println();
        }

        // 전체 과목 목록 (이미 선택된 항목에 * 표시)
        System.out.println("  전체 과목 목록  (" + subjects.size() + "개)");
        System.out.println("  " + "-".repeat(50));
        for (int i = 0; i < subjects.size(); i++) {
            boolean already = selectedSubjects.contains(subjects.get(i));
            System.out.printf("  %2d. %s%s%n",
                i + 1,
                subjects.get(i).getName(),
                already ? "  ✓" : "");
        }
        System.out.println("  " + "-".repeat(50));
        System.out.println("  추가할 과목 번호 입력  (쉼표 구분, 예: 2,7,12)");
        System.out.println("  [0 입력 = 전체 초기화  |  Enter = 변경 없이 유지]");
        System.out.print("  >> ");

        String line = sc.nextLine().trim();
        System.out.println();

        if (line.isEmpty()) {
            // 변경 없이 유지
            if (selectedSubjects.isEmpty()) {
                System.out.println("  선택된 과목이 없습니다. 번호를 입력해 과목을 추가하세요.");
            } else {
                System.out.println("  과목 선택을 유지합니다.");
            }
        } else if (line.equals("0")) {
            selectedSubjects.clear();
            System.out.println("  선택된 과목을 모두 초기화했습니다.");
        } else {
            int added = 0;
            for (String p : line.split(",")) {
                try {
                    int idx = Integer.parseInt(p.trim()) - 1;
                    if (idx >= 0 && idx < subjects.size()) {
                        Subject s = subjects.get(idx);
                        if (!selectedSubjects.contains(s)) {
                            selectedSubjects.add(s);
                            added++;
                        }
                    }
                } catch (NumberFormatException e) { /* skip */ }
            }
            System.out.println("  " + added + "개 과목 추가됨.");
        }

        System.out.println();
        if (!selectedSubjects.isEmpty()) {
            System.out.println("  현재 선택된 과목 (" + selectedSubjects.size() + "개):");
            for (Subject s : selectedSubjects) System.out.println("    - " + s.getName());
            System.out.println();
            System.out.println("  메뉴 3에서 추천 자격증 로드맵을 확인하세요.");
        }
        System.out.println();
    }

    // ── 메뉴 3: 추천 자격증 로드맵 ──────────────────────────────────────

    private static void menuRecommendRoadmap() {
        if (selectedSubjects.isEmpty()) {
            System.out.println("  먼저 메뉴 2에서 수강 과목을 입력해 주세요.");
            System.out.println();
            return;
        }

        System.out.println("  수강과목 기반 추천 자격증 로드맵");
        System.out.println();
        System.out.print("  선택 과목: ");
        StringJoiner sj = new StringJoiner(", ");
        for (Subject s : selectedSubjects) sj.add(s.getName());
        System.out.println(sj);
        System.out.println();

        // 연관도 내림차순 정렬 후 연관도 > 0 필터
        List<RecommendationResult> all = engine.recommendByMultiple(selectedSubjects, 0);
        List<RecommendationResult> filtered = new ArrayList<>();
        for (RecommendationResult r : all) {
            if (r.getJaccardScore() > 0) filtered.add(r);
        }

        if (filtered.isEmpty()) {
            System.out.println("  선택한 과목과 연관된 자격증이 없습니다. 과목을 더 추가해 보세요.");
            System.out.println();
            return;
        }

        // 헤더
        System.out.println("  " + "=".repeat(68));
        System.out.println("  " + pad("순위", 6)
                         + pad("자격증명", 34)
                         + pad("연관도", 12)
                         + pad("합격률", 8)
                         + pad("준비기간", 10)
                         + "난이도");
        System.out.println("  " + "-".repeat(68));

        int rank = 1;
        for (RecommendationResult r : filtered) {
            Certification cert = r.getCertification();
            System.out.println("  "
                + pad(rank++ + "위", 6)
                + pad(cert.getName(), 34)
                + pad(toRelevanceBar(r.getJaccardScore()), 12)
                + pad(String.format("%.1f%%", cert.getPassRate()), 8)
                + pad(String.format("%.1f개월", cert.getPrepMonths()), 10)
                + cert.getDifficultyStars());
        }

        System.out.println("  " + "=".repeat(68));
        System.out.println("  ※ 연관도 높은 순 정렬  |  연관도: ●○○○○~●●●●●  |  난이도: ★☆☆ 하  ★★☆ 중  ★★★ 상");
        System.out.println();
    }

    // ── 메뉴 4: 자격증 상세 정보 조회 ────────────────────────────────────

    private static void menuDetail() {
        System.out.print("  자격증 이름 입력 (예: SQLD, 빅데이터, AWS): ");
        String query = sc.nextLine().trim();
        System.out.println();

        Certification cert = engine.findByName(query);
        if (cert == null) {
            System.out.println("  '" + query + "'에 해당하는 자격증을 찾을 수 없습니다.");
            System.out.println();
            return;
        }

        System.out.println("  " + "=".repeat(55));
        System.out.println("  " + cert.getName());
        if (!cert.getDescription().isEmpty()) System.out.println("  " + cert.getDescription());
        System.out.println("  " + "=".repeat(55));
        System.out.println("  유형     : " + cert.getCertType());
        System.out.println("  발급기관 : " + cert.getIssuer());
        System.out.printf ("  합격률   : %.1f%%%n", cert.getPassRate());
        System.out.printf ("  준비기간 : %.1f개월%n", cert.getPrepMonths());
        System.out.println("  난이도   : " + cert.getDifficultyStars() + " (" + cert.getDifficultyLabel() + ")");
        System.out.println("  시험범위 : " + cert.getExamScope());
        System.out.println("  키워드   : " + String.join(", ", cert.getKeywords()));
        System.out.println();

        System.out.println("  관련 과목 Top 3");
        System.out.println("  " + "-".repeat(50));
        List<String> related = engine.getRelatedSubjects(cert, 3);
        if (related.isEmpty()) {
            System.out.println("  (관련 과목 없음)");
        } else {
            for (String s : related) System.out.println("  " + s);
        }
        System.out.println();
    }
}
