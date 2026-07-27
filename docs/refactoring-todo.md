# 리팩토링 목록

## 08.05 ~ 08.11 (Spring Security / JWT 전환 주차)

- [ ] `CartController`, `OrderController`의 `getMemberId()` 중복 제거
  - 현재 두 Controller에 동일한 세션 추출 코드가 중복되어 있음
  - JWT 전환 시 Interceptor 또는 ArgumentResolver로 공통화
  - 전환 후 `HttpSession` 의존 코드 전체 제거
