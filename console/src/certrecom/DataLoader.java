package certrecom;

import java.util.ArrayList;
import java.util.List;

/**
 * 자료2(자격증), 자료4(과목 키워드) 하드코딩 데이터 로더
 * 각 과목·자격증의 시험 범위·강의계획서에서 핵심 키워드를 최대한 추출
 */
public class DataLoader {

    // 자료4: 30개 수강과목 키워드 (강의계획서 기반 최대 추출)
    public static List<CourseSubject> loadSubjects() {
        List<CourseSubject> list = new ArrayList<>();

        // 1
        list.add(new CourseSubject("객체지향프로그래밍", "CS101", 3, "컴퓨터공학",
            new String[]{
                "클래스","인스턴스","객체","상속","다형성","캡슐화","Java","인터페이스","추상클래스",
                "컬렉션","제네릭","이벤트처리","GUI","예외처리","패키지","오버로딩","오버라이딩",
                "static","final","람다","스트림","디자인패턴","접근제어자","생성자","소멸자"
            }));

        // 2
        list.add(new CourseSubject("자료구조", "CS102", 3, "컴퓨터공학",
            new String[]{
                "배열","연결리스트","스택","큐","덱","트리","이진트리","힙","우선순위큐",
                "그래프","해시","해시테이블","정렬","탐색","BFS","DFS",
                "삽입정렬","버블정렬","선택정렬","합병정렬","퀵정렬","힙정렬",
                "시간복잡도","공간복잡도","빅오","이진탐색","알고리즘","완전탐색"
            }));

        // 3
        list.add(new CourseSubject("데이터베이스", "CS103", 3, "컴퓨터공학",
            new String[]{
                "SQL","DDL","DML","DCL","ERD","정규화","역정규화","인덱스","트랜잭션","ACID",
                "뷰","저장프로시저","트리거","관계형모델","DBMS","질의처리","조인","서브쿼리",
                "집계함수","무결성","동시성제어","데이터모델링","윈도우함수","회복","락킹"
            }));

        // 4
        list.add(new CourseSubject("기계학습", "AI201", 3, "인공지능",
            new String[]{
                "지도학습","비지도학습","분류","회귀","군집","의사결정트리","랜덤포레스트",
                "XGBoost","LightGBM","SVM","KNN","나이브베이즈","CNN","RNN","신경망",
                "딥러닝","Python","scikit-learn","교차검증","하이퍼파라미터","특성공학",
                "과적합","앙상블","머신러닝","부스팅","배깅","모델평가"
            }));

        // 5
        list.add(new CourseSubject("텍스트마이닝", "AI202", 3, "인공지능",
            new String[]{
                "NLP","자연어처리","토픽모델링","TF-IDF","감성분석","Transformer","BERT","GPT",
                "전처리","형태소분석","품사태깅","개체명인식","텍스트분류","워드임베딩","Word2Vec",
                "언어모델","Python","문서유사도","시퀀스모델링","어텐션","토크나이저"
            }));

        // 6
        list.add(new CourseSubject("딥러닝프로그래밍", "AI203", 3, "인공지능",
            new String[]{
                "CNN","RNN","LSTM","GRU","강화학습","GAN","오토인코더","VAE",
                "TensorFlow","Keras","PyTorch","역전파","경사하강법","활성화함수",
                "드롭아웃","배치정규화","전이학습","파인튜닝","딥러닝","신경망",
                "Python","과적합","정규화","모델최적화"
            }));

        // 7
        list.add(new CourseSubject("비주얼컴퓨팅", "AI204", 3, "인공지능",
            new String[]{
                "영상처리","특징검출","CNN","딥러닝","카메라보정","Homography","OpenCV",
                "SIFT","HOG","엣지검출","이미지분류","객체검출","시맨틱분할",
                "컴퓨터비전","컨볼루션","Optical Flow","영상분할","영상인식"
            }));

        // 8
        list.add(new CourseSubject("컴퓨터비전", "AI205", 3, "인공지능",
            new String[]{
                "OpenCV","물체인식","영상처리","카메라","2D","3D","YOLO","SSD",
                "특징추출","이미지처리","딥러닝","객체추적","CNN","컴퓨터비전",
                "Faster RCNN","이미지분류","깊이추정"
            }));

        // 9
        list.add(new CourseSubject("이산수학", "CS104", 3, "컴퓨터공학",
            new String[]{
                "명제","집합","함수","관계","그래프","트리","오토마타","부울대수",
                "정수론","조합론","확률","논리","귀납법","점화식","알고리즘",
                "경우의수","행렬","암호학"
            }));

        // 10
        list.add(new CourseSubject("오픈소스소프트웨어실습", "CS201", 3, "컴퓨터공학",
            new String[]{
                "리눅스","명령어","Git","GitHub","Docker","컨테이너","FastAPI","Streamlit",
                "HuggingFace","REST API","가상환경","pip","버전관리","쉘스크립트",
                "서버","파일시스템","브랜치","CI/CD","패키지관리"
            }));

        // 11
        list.add(new CourseSubject("데이터시각화", "DA101", 3, "데이터분석",
            new String[]{
                "Tableau","시각화","SQL","Python","차트","대시보드","matplotlib","seaborn",
                "plotly","탐색적데이터분석","EDA","히스토그램","산점도","박스플롯",
                "인터랙티브","데이터스토리텔링","집계","필터","비즈니스인텔리전스"
            }));

        // 12
        list.add(new CourseSubject("컴퓨터네트워크", "CS105", 3, "컴퓨터공학",
            new String[]{
                "OSI7계층","TCP/IP","프로토콜","라우팅","DNS","HTTP","HTTPS","IP주소",
                "서브넷","방화벽","VPN","소켓","네트워크보안","LAN","WAN","VLAN",
                "스위칭","ARP","ICMP","무선네트워크","네트워크장비","MAC"
            }));

        // 13
        list.add(new CourseSubject("IoT시스템설계및실습", "IOT101", 3, "IoT",
            new String[]{
                "Arduino","ESP8266","ESP32","RaspberryPi","센서","액추에이터","IoT",
                "네트워크","MQTT","임베디드","GPIO","클라우드연동","스마트홈",
                "무선통신","프로토콜","시리얼통신","PWM"
            }));

        // 14
        list.add(new CourseSubject("UX/UI디자인", "UX101", 3, "UX디자인",
            new String[]{
                "Figma","와이어프레임","프로토타입","페르소나","사용성테스트","HCD","인간중심설계",
                "사용자리서치","IA","인터랙션디자인","접근성","반응형디자인","UI컴포넌트",
                "디자인시스템","UX","UI","정보구조","서비스디자인","사용자시나리오"
            }));

        // 15
        list.add(new CourseSubject("HCI와UX평가", "UX102", 3, "UX디자인",
            new String[]{
                "사용성","휴리스틱평가","UX","인터페이스","프로토타입","HCI","인간공학",
                "사용자테스트","A/B테스트","인지부하","멘탈모델","접근성","사용성평가",
                "피츠의법칙","눈추적","피드백","사용자행동"
            }));

        // 16
        list.add(new CourseSubject("AI수학", "MA201", 3, "수학",
            new String[]{
                "선형대수","행렬","벡터","최적화","미분","편미분","확률","통계",
                "베이즈정리","정보이론","엔트로피","Gradient","경사하강법",
                "고유값","고유벡터","볼록함수","확률분포","기댓값","분산","공분산"
            }));

        // 17
        list.add(new CourseSubject("빅데이터알고리즘", "DA201", 3, "데이터분석",
            new String[]{
                "알고리즘","정렬","탐색","그래프","추천시스템","동적프로그래밍","Python",
                "탐욕알고리즘","분할정복","MapReduce","분산처리","병렬처리","빅데이터",
                "그리디","완전탐색","최단경로","최소신장트리","네트워크플로우"
            }));

        // 18
        list.add(new CourseSubject("빅데이터프로그래밍", "DA202", 3, "데이터분석",
            new String[]{
                "Python","Pandas","Numpy","크롤링","데이터분석","BeautifulSoup","Selenium",
                "데이터전처리","결측치처리","이상치탐지","통계분석","scikit-learn",
                "머신러닝","빅데이터","API호출","데이터정제","Scipy","R"
            }));

        // 19
        list.add(new CourseSubject("인터랙티브미디어개론", "WD101", 3, "웹디자인",
            new String[]{
                "HTML","CSS","JavaScript","웹","반응형","DOM","애니메이션","웹표준",
                "접근성","CSS3","HTML5","이벤트","인터랙션","UI","jQuery","레이아웃"
            }));

        // 20
        list.add(new CourseSubject("그래픽디자인", "GD101", 3, "그래픽디자인",
            new String[]{
                "Figma","UI","디자인시스템","색상","타이포그래피","레이아웃","색채이론",
                "브랜딩","아이콘","벡터그래픽","그래픽","디자인","일러스트레이션","포스터"
            }));

        // 21
        list.add(new CourseSubject("컴퓨터그래픽스", "GD201", 3, "그래픽디자인",
            new String[]{
                "WebGL","쉐이더","렌더링","애니메이션","3D","OpenGL","광선추적","래스터화",
                "변환행렬","텍스처","조명모델","Three.js","그래픽","모델링","뷰포트"
            }));

        // 22
        list.add(new CourseSubject("MLOps엔지니어링", "ML301", 3, "인공지능",
            new String[]{
                "AWS","클라우드","AutoML","MLOps","Kubeflow","Kafka","모델배포","CI/CD",
                "Docker","Kubernetes","모니터링","데이터파이프라인","피처스토어",
                "인프라","컨테이너","모델서빙","AB테스트","버전관리","실험추적"
            }));

        // 23
        list.add(new CourseSubject("인공지능응용", "AI301", 3, "인공지능",
            new String[]{
                "AI","클라우드","빅데이터","IoT","비즈니스모델","자동화","추천시스템",
                "자연어처리","컴퓨터비전","머신러닝","딥러닝","AI윤리","산업AI"
            }));

        // 24
        list.add(new CourseSubject("ICT융합전략", "ICT101", 3, "경영",
            new String[]{
                "ICT","클라우드","빅데이터","디지털경영","IoT","디지털전환","플랫폼",
                "스마트팩토리","데이터경영","AI전략","4차산업혁명","DX","스마트시티"
            }));

        // 25
        list.add(new CourseSubject("영상AI생성모델", "AI401", 3, "인공지능",
            new String[]{
                "TensorFlow","GAN","Stable Diffusion","VAE","생성모델","이미지생성",
                "Diffusion","딥러닝","잠재공간","스타일전이","PyTorch","DALL-E",
                "조건부생성","텍스트생성","확산모델"
            }));

        // 26
        list.add(new CourseSubject("인터랙티브심리학", "UX201", 3, "UX디자인",
            new String[]{
                "HCI","인간공학","정보처리","신호감지","인지심리학","사용자행동",
                "감성공학","피드백","사용성","인터페이스","UX","행동경제학","동기이론"
            }));

        // 27
        list.add(new CourseSubject("데이터마이닝", "DA301", 3, "데이터분석",
            new String[]{
                "회귀","분류","군집","SVM","나이브베이즈","KNN","차원축소","PCA",
                "연관규칙","Apriori","이상탐지","앙상블","부스팅","배깅","특성선택",
                "Python","머신러닝","XGBoost","LightGBM","데이터마이닝"
            }));

        // 28
        list.add(new CourseSubject("산학협력캡스톤설계1", "CAP101", 3, "캡스톤",
            new String[]{
                "프로젝트","설계","구현","SQL","요구사항분석","시스템설계","소프트웨어개발",
                "팀프로젝트","소프트웨어공학","발표","문서화","데이터베이스","인터페이스"
            }));

        // 29
        list.add(new CourseSubject("산학협력캡스톤설계2", "CAP102", 3, "캡스톤",
            new String[]{
                "프로젝트","알고리즘","자료구조","정보처리","소프트웨어공학","테스트",
                "구현","팀프로젝트","설계","배포","유지보수","Python","Java"
            }));

        // 30
        list.add(new CourseSubject("네트워크데이터분석", "NW301", 3, "컴퓨터공학",
            new String[]{
                "GNN","그래프신경망","노드임베딩","GCN","GAT","그래프","딥러닝",
                "링크예측","커뮤니티탐지","소셜네트워크분석","지식그래프","머신러닝",
                "PyTorch","GraphSAGE","임베딩"
            }));
        // 31
        list.add(new CourseSubject("모바일프로그래밍", "CS301", 3, "컴퓨터공학",
            new String[]{
                "Android","iOS","Kotlin","Swift","Java","React Native","Flutter",
                "모바일UI","액티비티","인텐트","레이아웃","뷰","이벤트","API",
                "HTTP","JSON","SQLite","데이터바인딩","앱개발","생명주기"
            }));

        return list;
    }

    // 자료2: 17개 자격증 키워드 (시험 범위 기반 최대 추출)
    public static List<Certification> loadCertifications() {
        List<Certification> list = new ArrayList<>();

        // ── 국가자격증 ──────────────────────────────────────────────────────

        list.add(new NationalCertification(
            "정보처리기사", 22.0, 3.5, 2,
            "소프트웨어설계, 소프트웨어개발, 데이터베이스 구축, 프로그래밍언어활용, 정보시스템 구축관리",
            "한국산업인력공단", true, true,
            new String[]{
                "소프트웨어설계","알고리즘","자료구조","운영체제","데이터베이스","네트워크",
                "정보보안","소프트웨어공학","프로그래밍언어","SQL","정보처리","시스템분석",
                "UML","요구사항분석","테스트","인터페이스","ERD","DBMS","정규화","인덱스",
                "디자인패턴","트랜잭션","애플리케이션","화면설계","무결성","소프트웨어개발"
            }));

        list.add(new NationalCertification(
            "리눅스마스터 2급", 25.82, 2.0, 3,
            "리눅스 일반, 리눅스 운영 및 관리, 리눅스 활용",
            "한국정보통신진흥협회", true, false,
            new String[]{
                "리눅스","운영체제","명령어","파일시스템","네트워크","쉘스크립트","서버관리",
                "프로세스","패키지관리","사용자관리","권한","마운트","시스템보안","디렉토리",
                "cron","서비스관리","로그관리","vim","bash"
            }));

        list.add(new NationalCertification(
            "빅데이터분석기사", 50.0, 2.5, 2,
            "빅데이터 분석 기획, 빅데이터 탐색, 빅데이터 모델링, 빅데이터 결과 해석",
            "한국데이터산업진흥원", true, true,
            new String[]{
                "빅데이터","Python","R","통계","머신러닝","데이터전처리","시각화","분류",
                "회귀","군집","데이터분석","탐색적데이터분석","EDA","모델평가","표본추출",
                "가설검정","회귀분석","scikit-learn","분산분석","상관분석","기초통계"
            }));

        list.add(new NationalCertification(
            "SQLD", 48.79, 0.5, 2,
            "데이터 모델링의 이해, SQL 기본, SQL 활용, SQL 최적화",
            "한국데이터산업진흥원", true, false,
            new String[]{
                "SQL","데이터모델링","ERD","정규화","조인","서브쿼리","인덱스","성능최적화",
                "DBMS","DDL","DML","집계함수","윈도우함수","트랜잭션","뷰","무결성",
                "옵티마이저","실행계획","계층쿼리","집합연산"
            }));

        list.add(new NationalCertification(
            "ADsP", 63.0, 0.75, 1,
            "데이터 이해, 데이터 분석 기획, 데이터 분석, 데이터 시각화",
            "한국데이터산업진흥원", true, false,
            new String[]{
                "데이터분석","통계","확률","데이터마이닝","시각화","SQL","R","Python",
                "비즈니스분석","기초통계","회귀분석","분류","군집","연관분석","의사결정트리",
                "탐색적데이터분석","EDA","표본추출","가설검정","데이터이해"
            }));

        list.add(new NationalCertification(
            "네트워크관리사 2급", 30.0, 1.5, 3,
            "네트워크 일반, TCP/IP, 네트워크 운용기기, 네트워크 보안",
            "한국정보통신자격협회", true, true,
            new String[]{
                "네트워크","TCP/IP","OSI","라우팅","스위칭","보안","서버","프로토콜","DNS",
                "VLAN","방화벽","VPN","IP주소","서브네팅","무선LAN","네트워크장비",
                "ARP","ICMP","DHCP","NAT","ACL","QoS"
            }));

        list.add(new NationalCertification(
            "컴퓨터그래픽스운용기능사", 76.0, 1.5, 1,
            "컴퓨터그래픽스 작업, 사진편집, 색상, 레이아웃, 타이포그래피",
            "한국산업인력공단", true, true,
            new String[]{
                "포토샵","일러스트레이터","색상","그래픽","디자인","이미지편집","레이아웃",
                "타이포그래피","합성","마스킹","레이어","필터","색보정","벡터","래스터",
                "색채이론","보정","선택영역","패스","브러시"
            }));

        list.add(new NationalCertification(
            "사용자중심디자인기사", 50.0, 3.0, 2,
            "사용자 리서치, UX 설계, 프로토타입, 사용성 평가, HCI",
            "한국산업인력공단", true, true,
            new String[]{
                "UX","UI","사용성","사용자조사","프로토타입","와이어프레임","인터페이스",
                "HCI","인간공학","페르소나","정보구조","IA","접근성","사용성평가",
                "서비스디자인","디자인시스템","인터랙션디자인","Figma","사용자리서치",
                "사용자시나리오","HCD"
            }));

        list.add(new NationalCertification(
            "COS Pro 1급", 35.0, 2.0, 3,
            "프로그래밍 언어, 알고리즘, 자료구조, 코딩 구현",
            "YBM", true, true,
            new String[]{
                "프로그래밍","알고리즘","자료구조","Python","Java","C++","코딩","문제해결",
                "함수","반복문","조건문","재귀","정렬","탐색","배열","리스트",
                "스택","큐","트리","그래프","해시","동적프로그래밍"
            }));

        list.add(new NationalCertification(
            "GTQ 1급", 60.0, 0.75, 1,
            "포토샵을 활용한 그래픽 이미지 편집 및 합성",
            "한국생산성본부", true, true,
            new String[]{
                "포토샵","그래픽","이미지편집","색상","레이어","효과","디자인","합성",
                "마스킹","선택영역","보정","타이포그래피","필터","레이아웃","색보정"
            }));

        list.add(new NationalCertification(
            "웹디자인기능사", 55.0, 1.5, 1,
            "웹 표준, HTML/CSS/JavaScript, 웹디자인, 웹퍼블리싱",
            "한국산업인력공단", true, true,
            new String[]{
                "HTML","CSS","JavaScript","웹디자인","레이아웃","반응형","UI","웹퍼블리싱",
                "웹표준","접근성","색상","타이포그래피","DOM","이벤트","CSS3","HTML5",
                "애니메이션","jQuery","그리드"
            }));

        // ── 국제/민간자격증 ─────────────────────────────────────────────────

        list.add(new InternationalCertification(
            "AICE Professional", 52.0, 1.5, 2,
            "머신러닝, 딥러닝, Python, AI 응용, 데이터 분석, 자연어처리, 컴퓨터비전",
            "KT", "한국어", true,
            new String[]{
                "머신러닝","딥러닝","Python","데이터분석","AI","TensorFlow","자연어처리",
                "컴퓨터비전","분류","회귀","군집","NLP","CNN","데이터전처리","모델평가",
                "scikit-learn","Keras","앙상블","교차검증","특성공학","이미지분류"
            }));

        list.add(new InternationalCertification(
            "TensorFlow Developer", 50.0, 3.0, 2,
            "TensorFlow 기반 딥러닝, CNN, RNN, NLP, 시계열 분석, 전이학습",
            "Google", "영어", true,
            new String[]{
                "TensorFlow","딥러닝","CNN","RNN","신경망","Python","이미지분류","NLP",
                "시계열","LSTM","GAN","Keras","전이학습","데이터파이프라인","자연어처리",
                "VAE","GRU","드롭아웃","배치정규화","모델최적화","오토인코더"
            }));

        list.add(new InternationalCertification(
            "Tableau Desktop Specialist", 62.0, 1.5, 1,
            "Tableau 데이터 연결, 시각화, 대시보드, 계산 필드, 집계",
            "Salesforce", "영어", true,
            new String[]{
                "Tableau","시각화","대시보드","데이터연결","차트","분석","비즈니스인텔리전스",
                "SQL","Python","계산필드","집계","지도시각화","탐색적데이터분석","EDA",
                "필터","워크시트","스토리","LOD표현식"
            }));

        list.add(new InternationalCertification(
            "AICE Associate", 70.0, 1.0, 1,
            "AI 기초, 머신러닝 기본, Python 기초 활용, 데이터 분석",
            "KT", "한국어", true,
            new String[]{
                "AI","머신러닝","데이터분석","Python","딥러닝","통계","분류","회귀",
                "기초통계","데이터전처리","scikit-learn","탐색적데이터분석","EDA","군집"
            }));

        list.add(new InternationalCertification(
            "AWS Certified Cloud Practitioner", 67.0, 0.75, 1,
            "AWS 핵심 서비스, 클라우드 개념, 보안, 요금 모델, 글로벌 인프라",
            "Amazon", "영어", true,
            new String[]{
                "AWS","클라우드","EC2","S3","IAM","가용성","비용최적화","보안","인프라",
                "VPC","RDS","Lambda","클라우드컴퓨팅","컨테이너","글로벌인프라",
                "공동책임모델","스토리지","데이터베이스","네트워크","모니터링"
            }));

        list.add(new InternationalCertification(
            "Google UX Design Certificate", 70.0, 6.0, 2,
            "UX 리서치, 와이어프레임, 프로토타입, Figma, 사용성 테스트, 포트폴리오",
            "Google", "영어", false,
            new String[]{
                "UX","디자인씽킹","프로토타입","사용성테스트","Figma","와이어프레임",
                "사용자리서치","포트폴리오","페르소나","HCD","접근성","인터랙션디자인",
                "반응형디자인","디자인시스템","IA","사용자시나리오","서비스디자인","UI"
            }));


        // 자격증 한 줄 설명
        for (Certification c : list) {
            switch (c.getName()) {
                case "정보처리기사":
                    c.setDescription("소프트웨어 개발, 데이터베이스, 운영체제 등 IT 전반 역량을 평가하는 대표 국가기술자격"); break;
                case "SQLD":
                    c.setDescription("SQL 활용 능력과 데이터베이스 기본 개념을 평가하는 데이터베이스 자격증"); break;
                case "ADsP":
                    c.setDescription("데이터 분석 기초, 통계, 데이터 처리 개념을 평가하는 데이터 분석 입문 자격증"); break;
                case "빅데이터분석기사":
                    c.setDescription("빅데이터 수집, 처리, 분석, 머신러닝 활용 능력을 평가하는 데이터 분석 국가기술자격"); break;
                case "네트워크관리사 2급":
                    c.setDescription("TCP/IP, 네트워크 장비, 서버 기초 등 네트워크 관리 능력을 평가하는 자격증"); break;
                case "리눅스마스터 2급":
                    c.setDescription("Linux 명령어, 파일 권한, 서버 환경 등 리눅스 활용 능력을 평가하는 자격증"); break;
                case "COS Pro 1급":
                    c.setDescription("프로그래밍 언어와 알고리즘 문제 해결 능력을 평가하는 코딩 전문 자격증"); break;
                case "GTQ 1급":
                    c.setDescription("포토샵 등 그래픽 편집 도구 활용 능력을 평가하는 디자인 실기 중심 자격증"); break;
                case "웹디자인기능사":
                    c.setDescription("HTML, CSS, 웹디자인 기초 능력을 평가하는 웹디자인 국가기술자격"); break;
                case "컴퓨터그래픽스운용기능사":
                    c.setDescription("그래픽 제작 도구를 활용한 시각 디자인 제작 능력을 평가하는 실기 중심 자격증"); break;
                case "사용자중심디자인기사":
                    c.setDescription("사용자 중심 설계, UX 분석, 인터페이스 평가 능력을 평가하는 디자인 국가기술자격"); break;
                case "AICE Professional":
                    c.setDescription("AI 이해, 데이터 처리, 머신러닝 활용 능력을 평가하는 KT AI 자격증"); break;
                case "TensorFlow Developer":
                    c.setDescription("TensorFlow를 활용한 딥러닝 모델 개발 능력을 평가하는 Google 인증"); break;
                case "Tableau Desktop Specialist":
                    c.setDescription("Tableau를 활용한 데이터 시각화, 대시보드 구성 능력을 평가하는 자격증"); break;
                case "AICE Associate":
                    c.setDescription("AI 기본 개념과 활용 능력을 평가하는 KT AI 입문형 자격증"); break;
                case "AWS Certified Cloud Practitioner":
                    c.setDescription("AWS 클라우드 기본 개념, 서비스, 보안, 비용 구조를 평가하는 입문형 클라우드 자격증"); break;
                case "Google UX Design Certificate":
                    c.setDescription("사용자 조사, 와이어프레임, 프로토타입, UX 평가 등 UX 디자인 과정을 다루는 Google 인증"); break;
            }
        }
        return list;
    }
}
