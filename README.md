# springboot-sns-sample

Spring Boot 백엔드와 Next.js 프런트엔드로 구성된 SNS 샘플 프로젝트입니다.

```
springboot-sns-sample/
├── src/          # Spring Boot 백엔드 (Java 25)
├── frontend/     # Next.js 프런트엔드 (Node 22)
└── build.gradle.kts
```

## 실행 방법

터미널 두 개에서 각각 실행합니다.

```bash
# 1) 백엔드 → http://localhost:8080
./gradlew bootRun

# 2) 프런트엔드 → http://localhost:3000
cd frontend
npm ci
npm run dev
```

브라우저에서는 http://localhost:3000 으로 접속합니다. 프런트의 `/api/*` 요청은 백엔드로 프록시됩니다.
자세한 내용은 [frontend/README.md](frontend/README.md)를 참고하세요.

## API 테스트

`src/main/resources/http/*.sh`에 curl 스크립트가 있습니다.
