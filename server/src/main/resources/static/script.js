// ── 서버에서 받아온 자격증 정보 캐시
let certInfoCache = {};

// ── 페이지 로드 시 자격증 정보를 서버에서 미리 받아옴
fetch("/api/certifications")
  .then(res => res.json())
  .then(data => {
    data.forEach(cert => {
      certInfoCache[cert.name] = cert;
    });
  })
  .catch(err => console.error("자격증 정보 로드 실패:", err));

// ── DOM 요소
const showBtn = document.getElementById("showBtn");
const result  = document.getElementById("result");
const ranking = document.getElementById("ranking");
const detail  = document.getElementById("detail");

// ── 연관도 점수 → 막대 표시
function toRelevanceBar(score) {
  const level = score < 10 ? 1 : score < 25 ? 2 : score < 45 ? 3 : score < 65 ? 4 : 5;
  return "●".repeat(level) + "○".repeat(5 - level);
}

// ── 추천 버튼 클릭
showBtn.addEventListener("click", () => {
  result.innerHTML  = "";
  ranking.innerHTML = "";
  detail.innerHTML  = "";

  const prev = document.getElementById("selectedBadge");
  if (prev) prev.remove();

  const checked = [...document.querySelectorAll('input[type="checkbox"]:checked')];
  if (checked.length === 0) {
    result.innerHTML = "<p>과목을 하나 이상 선택해 주세요.</p>";
    return;
  }

  const selectedNames = checked.map(cb => cb.value);

  // 서버에 POST /api/recommend 요청
  fetch("/api/recommend", {
    method: "POST",
    headers: { "Content-Type": "application/json; charset=UTF-8" },
    body: JSON.stringify({ subjects: selectedNames })
  })
    .then(res => res.json())
    .then(data => renderResult(data, selectedNames))
    .catch(err => {
      result.innerHTML = `<p style="color:red;">서버 오류: ${err.message}</p>`;
    });
});

// ── 결과 렌더링
function renderResult(data, selectedNames) {
  // 선택 과목 배지
  const badge = document.createElement("div");
  badge.id = "selectedBadge";
  badge.innerHTML = `선택 과목: <b>${selectedNames.join(", ")}</b>`;
  ranking.insertAdjacentElement("beforebegin", badge);

  // ── 과목별 추천 (perSubject)
  data.perSubject.forEach(item => {
    const p = document.createElement("p");
    const bar = toRelevanceBar(item.score);
    p.innerHTML = `<b>${item.subject}</b> → ${item.certName}
      &nbsp; <span class="relevance" title="${item.score}%">${bar}</span>`;
    result.appendChild(p);
  });

  // ── 최종 순위 테이블
  if (data.ranking.length === 0) {
    ranking.innerHTML = "<p>연관된 자격증이 없습니다.</p>";
    return;
  }

  const table = document.createElement("table");
  table.className = "rank-table";
  table.innerHTML = `
    <thead>
      <tr>
        <th>순위</th>
        <th>자격증명</th>
        <th>연관도</th>
        <th>합격률</th>
        <th>준비기간</th>
        <th>난이도</th>
      </tr>
    </thead>
    <tbody></tbody>
  `;
  const tbody = table.querySelector("tbody");

  data.ranking.forEach((item, idx) => {
    const tr = document.createElement("tr");
    const bar = toRelevanceBar(item.score);
    tr.innerHTML = `
      <td>${idx + 1}</td>
      <td class="cert-btn" onclick="showDetail('${item.name.replace(/'/g, "\\'")}')">
        ${item.name}
      </td>
      <td>
        <span class="relevance" title="${item.score}%">${bar}</span>
      </td>
      <td>${item.passRate.toFixed(1)}%</td>
      <td>${item.prepMonths.toFixed(1)}개월</td>
      <td>
        <span class="stars">${item.difficultyStars}</span>
        <small>(${item.difficultyLabel})</small>
      </td>
    `;
    tbody.appendChild(tr);
  });

  ranking.appendChild(table);
}

// ── 상세 정보 표시
function showDetail(certName) {
  const info = certInfoCache[certName];
  if (!info) {
    detail.innerHTML = "<p>해당 자격증의 상세 정보를 불러오는 중입니다...</p>";
    // 캐시에 없으면 서버에서 직접 조회
    fetch("/api/certifications")
      .then(res => res.json())
      .then(data => {
        data.forEach(c => { certInfoCache[c.name] = c; });
        renderDetail(certInfoCache[certName]);
      });
    return;
  }
  renderDetail(info);
}

function renderDetail(info) {
  if (!info) {
    detail.innerHTML = "<p>해당 자격증의 상세 정보가 없습니다.</p>";
    return;
  }
  detail.innerHTML = `
    <div class="detail-box">
      <h3>${info.name}</h3>
      <p><b>설명:</b> ${info.description}</p>
      <p><b>유형:</b> ${info.certType}</p>
      <p><b>발급기관:</b> ${info.issuer}</p>
      <p><b>합격률:</b> ${info.passRate.toFixed(1)}%</p>
      <p><b>평균 준비기간:</b> ${info.prepMonths.toFixed(1)}개월</p>
      <p><b>난이도:</b>
        <span class="stars">${info.difficultyStars}</span>
        (${info.difficultyLabel})
      </p>
      <p><b>시험 범위:</b> ${info.examScope}</p>
    </div>
  `;
}
