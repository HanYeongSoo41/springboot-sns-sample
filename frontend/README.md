# frontend

Next.js 프런트엔드입니다. 백엔드(Spring Boot)는 저장소 루트에 있습니다.

## 요구 사항

- Node.js 22 (`.nvmrc` 참고). Next.js 16은 Node 20.9 이상이 필요합니다.

## 실행 방법

```bash
npm ci        # 의존성 설치
npm run dev   # 개발 서버 실행 → http://localhost:3000
```

## 백엔드 연동

`/api/*` 요청은 Next.js 개발 서버가 백엔드로 프록시합니다(`next.config.mjs`의 `rewrites`).
브라우저 입장에서는 같은 주소라 CORS 설정이 필요 없고, 세션 쿠키도 그대로 동작합니다.

| 환경 변수 | 기본값 | 설명 |
|---|---|---|
| `BACKEND_URL` | `http://localhost:8080` | 프록시가 요청을 넘길 백엔드 주소 |
| `NEXT_PUBLIC_API_BASE_URL` | (비어 있음) | 지정하면 프록시 대신 이 주소로 직접 요청합니다 |
