#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=== 회원가입 ==="
curl -s -X POST "$BASE_URL/api/users" \
  -H "Content-Type: application/json" \
  -d '{"username": "alice", "password": "password1234"}'
echo -e "\n"

echo "=== 회원가입 (중복 username → 409) ==="
curl -s -X POST "$BASE_URL/api/users" \
  -H "Content-Type: application/json" \
  -d '{"username": "alice", "password": "password1234"}'
echo -e "\n"

echo "=== 회원가입 (유효성 실패 → 400) ==="
curl -s -X POST "$BASE_URL/api/users" \
  -H "Content-Type: application/json" \
  -d '{"username": "a", "password": "short"}'
echo -e "\n"

echo "=== 회원가입 (비밀번호 72바이트 초과: 한글 25자 → 400) ==="
curl -s -X POST "$BASE_URL/api/users" \
  -H "Content-Type: application/json" \
  -d '{"username": "kim", "password": "가가가가가가가가가가가가가가가가가가가가가가가가가"}'
echo -e "\n"

echo "=== 회원가입 (잘못된 JSON → 400) ==="
curl -s -X POST "$BASE_URL/api/users" \
  -H "Content-Type: application/json" \
  -d '{"username": '
echo -e "\n"

echo "=== 사용자 단건 조회 (id) ==="
curl -s "$BASE_URL/api/users/1"
echo -e "\n"

echo "=== 사용자 단건 조회 (username) ==="
curl -s "$BASE_URL/api/users/username/alice"
echo -e "\n"

echo "=== 사용자 목록 조회 ==="
curl -s "$BASE_URL/api/users"
echo -e "\n"

echo "=== 존재하지 않는 사용자 조회 → 404 ==="
curl -s "$BASE_URL/api/users/9999"
echo -e "\n"

echo "=== 잘못된 id 형식 → 400 ==="
curl -s "$BASE_URL/api/users/abc"
echo -e "\n"

echo "=== 지원하지 않는 메서드 → 405 ==="
curl -s -X DELETE "$BASE_URL/api/users"
echo -e "\n"

echo "=== 없는 경로 → 404 ==="
curl -s "$BASE_URL/api/unknown"
echo -e "\n"
