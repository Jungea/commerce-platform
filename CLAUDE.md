# CLAUDE.md

## 리포 목적

학습한 기술을 실제 서비스에 적용하는 메인 프로젝트.
3개 리포의 관계: `developer-roadmap`(이론/ADR) → `developer-labs`(기술 검증) → `commerce-platform`(실제 적용)

---

## 패키지 구조

```
src/main/java/
└── org/example/commerceplatform/
    ├── member/
    │   ├── domain/         # 엔티티, 레포지토리 인터페이스
    │   ├── application/    # 서비스
    │   └── api/            # 컨트롤러, DTO
    ├── product/
    │   ├── domain/
    │   ├── application/
    │   └── api/
    ├── cart/
    │   ├── domain/
    │   ├── application/
    │   └── api/
    ├── order/
    │   ├── domain/
    │   ├── application/
    │   └── api/
    └── common/             # 공통 유틸, 예외, 응답 형식
```

---

## 개발 규칙

- 레이어 의존 방향: `api` → `application` → `domain`
- 역방향 의존 금지
- 도메인 엔티티에 비즈니스 로직 우선 배치 (Service 비대화 방지)

---

## 로드맵 준수 규칙

- 모든 작업은 `developer-roadmap/docs/roadmap/fullstack-roadmap.md` 및 각 Phase 상세 계획을 기준으로 진행한다
- 로드맵과 다른 방향으로 진행해야 할 경우, 먼저 사용자에게 내용을 설명하고 확인을 받은 후 진행한다
- 커밋 전에 작업 내용이 로드맵과 다른 점이 있는지 확인하고, 차이가 있으면 사용자에게 알린다

---

## 커밋 메시지 규칙

`developer-roadmap/docs/conventions.md` 참고.

- 제목 한 줄만 작성 (`<type>: <subject>`)
- 상세 메시지(body) 작성 금지

---

## 브랜치 전략

`developer-roadmap/docs/conventions.md` 참고.

- 작업은 항상 `feature/기능명` 브랜치에서 진행
- `main` 브랜치에 직접 커밋 금지
- PR 머지 전 self-review 1회 필수
