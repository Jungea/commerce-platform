# 로컬 개발 환경 세팅 가이드

## 사전 요구사항
- WSL2 (Ubuntu)
- JDK 17 또는 21 (WSL 내부에 설치)
- IntelliJ IDEA (Windows에 설치, WSL 프로젝트 오픈 기능 사용)
- Docker Desktop (Windows에 설치, WSL Integration 활성화)

## Docker Desktop 설치 및 WSL 연동

1. https://www.docker.com/products/docker-desktop/ 에서 Windows용 다운로드
2. 설치 시 "Per-user installation (Recommended)" 선택, "Use WSL 2 instead of Hyper-V" 체크 유지
3. 설치 후 Docker Desktop 실행
4. Settings → Resources → WSL Integration
   - "Enable integration with my default WSL distro" 체크
   - 사용 중인 배포판(Ubuntu) 토글 켜기
   - Apply & Restart
5. WSL 터미널에서 확인:
```bash
   docker --version
```

## 로컬 MySQL 실행 (Docker Compose)

로컬에 이미 MySQL이 설치되어 3306 포트를 사용 중인 경우, 컨테이너는 **3307** 포트로 매핑해서 사용합니다.

```bash
cd commerce-platform
docker compose up -d      # 컨테이너 실행
docker ps                 # 실행 확인
docker compose down       # 컨테이너 종료 (데이터는 volume에 보존됨)
```

접속 정보 (local 프로필 기준):
- Host: localhost
- Port: 3307
- Database: commerce
- Username: root
- Password: 1234

## 트러블슈팅

- **포트 충돌 (`ports are not available` 에러)**: 로컬에 이미 MySQL이 설치되어 3306을 점유 중일 수 있음. `sudo lsof -i :3306`으로 확인 후, docker-compose.yml의 호스트 포트를 변경(예: 3307)하고 application.yml의 datasource url도 함께 수정.
