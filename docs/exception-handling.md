# 예외 처리 규칙

## 예외 체계

| 예외 | 상태코드 | 의미 | 사용 예 |
|---|---|---|---|
| `InvalidRequestException` | 400 | 입력값이 비즈니스 규칙을 위반 (Bean Validation 통과했지만 서비스 레벨에서 거부) | 재고 수량 음수 |
| `AuthenticationFailedException` | 401 | 인증 실패 | 이메일 없음, 비밀번호 불일치 |
| `NotFoundException` | 404 | 요청한 리소스가 존재하지 않음 | 상품 ID 없음, 회원 ID 없음 |
| `IllegalStateException` | 409 | 리소스 충돌 | 이메일 중복 |

모든 커스텀 예외 클래스는 `common/exception/` 패키지에 위치한다.

---

## 도메인별 사용 기준

| 상황 | 사용 예외 |
|---|---|
| 회원·상품·주문 등 ID로 조회 실패 | `NotFoundException` |
| 재고 부족, 이미 취소된 주문 등 상태 충돌 | `IllegalStateException` |
| 수량 0 이하 등 입력값 비즈니스 규칙 위반 | `InvalidRequestException` |
| 로그인 실패 (JWT 전환 후에도 동일) | `AuthenticationFailedException` |

---

## 계층별 역할

- **Controller(DTO)**: `@NotBlank`, `@Min` 등 Bean Validation — HTTP 입력 형식 검사
- **Service**: 커스텀 예외 — 비즈니스 규칙 위반 검사
- **GlobalExceptionHandler**: 예외 → HTTP 상태코드 변환
