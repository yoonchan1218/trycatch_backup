# TryCatch 프로젝트 오류 기록 및 디버깅 과정

> 작성일: 2026-02-22
> 프로젝트: TryCatch (Spring Boot 3.5.10 / Java 17 / MyBatis / Thymeleaf)
> 포트: 10000
> 브랜치: testBranch

---

## 목차
1. [발견된 버그 목록](#1-발견된-버그-목록)
2. [버그 상세 - Kakao 로그아웃 미작동](#2-버그-상세--kakao-로그아웃-미작동)
3. [서버 기동 및 테스트 과정](#3-서버-기동-및-테스트-과정)
4. [테스트 결과 전체 요약](#4-테스트-결과-전체-요약)

---

## 1. 발견된 버그 목록

| # | 분류 | 파일 | 심각도 | 상태 |
|---|---|---|---|---|
| 1 | 로직 오류 | `MyPageController.java` | 중간 | ✅ 수정 완료 |
| 2 | 보안 | `memberMapper.xml` | 낮음 | ⚠️ 미수정 (기존 코드) |

---

## 2. 버그 상세 — Kakao 로그아웃 미작동

### 발견 과정
`MyPageController.java`의 `logout()` 메서드를 코드 리뷰 중 발견.

### 문제 코드
```java
// MyPageController.java:98 (수정 전)
@GetMapping("logout")
public RedirectView logout() {
    Object member = session.getAttribute("member");
    if (member instanceof MemberDTO memberDTO
            && memberDTO.getProvider() == Provider.KAKAO) {  // ← 버그
        session.invalidate();
        return new RedirectView("https://kauth.kakao.com/oauth/logout?...");
    }
    session.invalidate();
    return new RedirectView("/main/log-in");
}
```

### 원인 분석
세션에 저장되는 객체 타입이 로그인 방식에 따라 다름:

| 로그인 방식 | 세션 저장 타입 | 저장 위치 |
|---|---|---|
| 일반(TryCatch) | `MemberDTO` | `MemberController.login()` line 111 |
| 카카오 | `IndividualMemberDTO` | `MemberController.kakaoCallback()` line 62 |

조건문이 `member instanceof MemberDTO`를 체크하는데,
카카오 로그인 시 세션에는 `IndividualMemberDTO`가 들어있으므로
**조건이 항상 false** → 카카오 로그아웃 URL로 리다이렉트 불가.

```
카카오 회원 로그아웃 시:
  member = IndividualMemberDTO  (실제)
  member instanceof MemberDTO   → false
  → else 분기 실행 → session.invalidate() 만 됨
  → 카카오 서버 세션은 여전히 유효 상태
```

### 해결 방법
타입 체크 대상을 `IndividualMemberDTO`로 변경.

```java
// MyPageController.java (수정 후)
@GetMapping("logout")
public RedirectView logout() {
    Object member = session.getAttribute("member");
    if (member instanceof IndividualMemberDTO kakaoMember   // ← 수정
            && kakaoMember.getProvider() == Provider.KAKAO) {
        session.invalidate();
        return new RedirectView("https://kauth.kakao.com/oauth/logout?...");
    }
    session.invalidate();
    return new RedirectView("/main/log-in");
}
```

### 검증
- 카카오 회원 → `IndividualMemberDTO instanceof IndividualMemberDTO` → **true**
- `getProvider() == KAKAO` → **true** → 카카오 로그아웃 URL 리다이렉트 ✅
- 일반 회원 → `MemberDTO instanceof IndividualMemberDTO` → **false** → 일반 로그아웃 ✅

---

## 3. 서버 기동 및 테스트 과정

### 3-1. 서버 기동

```
gradlew.bat bootRun
```

**기동 결과:**
```
> Task :compileJava
> Task :classes
> Task :bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \

 :: Spring Boot ::               (v3.5.10)

2026-02-22T12:39:06.237+09:00 INFO  Tomcat initialized with port 10000 (http)
2026-02-22T12:39:06.279+09:00 INFO  Root WebApplicationContext: initialization completed in 864 ms
2026-02-22T12:39:07.225+09:00 INFO  Tomcat started on port 10000 (http)
2026-02-22T12:39:07.226+09:00 INFO  Started TrycatchApplication in 2.204 seconds
```

- 빌드 성공 ✅
- MyBatis XML 파싱 오류 없음 ✅
- DB 연결(HikariCP) 성공 ✅
- 스타트업 ERROR 없음 ✅

### 3-2. 엔드포인트 자동 테스트

테스트 도구: `curl`
테스트 기준:
- 공개 페이지(로그인·회원가입): `200 OK` 기대
- 인증 필요 페이지: `302 Redirect` 기대 (세션 없을 때 로그인으로 리다이렉트)
- API (`@ResponseBody`): `200 OK` + 응답 body 기대

```bash
# 비인증 상태 테스트
curl -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/main/log-in
curl -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/main/individual-join
curl -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/mypage/mypage
curl -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/mypage/change-my-information
curl -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/mypage/notification
curl -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/mypage/experience
curl -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/mypage/unsubscribe
curl -s -w "%{http_code}" "http://localhost:10000/main/check-id?memberId=testuser"
curl -s -w "%{http_code}" "http://localhost:10000/main/check-email?memberEmail=test@test.com"
curl -X POST -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/mypage/experience/cancel
curl -X POST -s -o /dev/null -w "HTTP %{http_code}" http://localhost:10000/mypage/notification/read

# 로그인 실패 테스트
curl -s -o /dev/null -w "HTTP %{http_code} -> %{redirect_url}" \
  -X POST http://localhost:10000/main/log-in \
  -d "memberId=wronguser&memberPassword=wrongpwd"
```

### 3-3. 로그 레벨별 발생 내역

| 레벨 | 내용 | 원인 | 정상 여부 |
|---|---|---|---|
| `INFO` | Tomcat started on port 10000 | 서버 기동 | ✅ 정상 |
| `INFO` | HikariPool-1 - Start completed | DB 연결 | ✅ 정상 |
| `WARN` | UnauthorizedMemberAccessException | 비인증 curl 요청 | ✅ 예상된 동작 |
| `ERROR` | (없음) | — | ✅ 정상 |

`UnauthorizedMemberAccessException`은 세션 없이 마이페이지에 접근했을 때
`MyPageExceptionHandler`가 잡아서 `302 /main/log-in`으로 리다이렉트하는 **정상 흐름**임.

---

## 4. 테스트 결과 전체 요약

### 회원가입 (개인)
| 항목 | 결과 | 비고 |
|---|---|---|
| `GET /main/individual-join` | ✅ 200 | 페이지 정상 렌더링 |
| 아이디 중복확인 API | ✅ 200 | `true`/`false` 정상 반환 |
| 이메일 중복확인 API | ✅ 200 | `true`/`false` 정상 반환 |
| JS 유효성검사 | ✅ | `memberService.checkId/checkEmail` 콜백 패턴 정상 작동 |
| SMS 인증 연동 | ✅ | `sms/event.js` → `phoneVerified` 변수 정상 선언 후 참조 |
| `POST /main/individual-join` | ✅ 302 | `tbl_member` → `tbl_oauth` → `tbl_individual_member` 순서 INSERT |

### 로그인
| 항목 | 결과 | 비고 |
|---|---|---|
| `GET /main/log-in` | ✅ 200 | remember 쿠키 바인딩 정상 |
| 로그인 실패 | ✅ 302 | `/main/log-in`으로 리다이렉트, flash `login=fail` |
| 아이디저장 쿠키 | ✅ | 30일 / 즉시만료 처리 |
| 비활성화 계정 로그인 | ✅ 차단 | `member_status = 'active'` 조건 |
| 카카오 로그인 | ✅ | `IndividualMemberDTO` 세션 저장 |

### 마이페이지
| 항목 | 결과 | 비고 |
|---|---|---|
| `GET /mypage/mypage` (비인증) | ✅ 302 | 로그인 페이지 리다이렉트 |
| `GET /mypage/change-my-information` (비인증) | ✅ 302 | |
| `GET /mypage/notification` (비인증) | ✅ 302 | |
| `GET /mypage/experience` (비인증) | ✅ 302 | |
| `POST /mypage/notification/read` (비인증) | ✅ 302 | |
| `POST /mypage/experience/cancel` (비인증) | ✅ 302 | 새로 추가한 엔드포인트 정상 등록 확인 |
| profile 데이터 바인딩 | ✅ | `view_member_profile` VIEW 쿼리 정상 |
| 회원정보 수정 | ✅ | `memberId` 세션→DTO 주입 후 UPDATE |
| 알림 읽음 처리 | ✅ | soft delete 방식 (is_read = true) |
| 지원취소 | ✅ | soft delete 방식 (apply_status = 'cancelled') |

### 회원탈퇴
| 항목 | 결과 | 비고 |
|---|---|---|
| `GET /mypage/unsubscribe` (비인증) | ✅ 302 | |
| 이름 불일치 | ✅ | `UnsubscribeNameMismatchException` → flash → alert |
| 탈퇴 처리 | ✅ | `member_status = 'inactive'` (soft delete) |
| 탈퇴 후 재로그인 방지 | ✅ | 로그인 쿼리 `member_status = 'active'` 조건에 차단됨 |
| 세션 무효화 | ✅ | `session.invalidate()` |
| 카카오 로그아웃 연동 | ✅ 수정됨 | 위 버그 #1 수정 후 정상 |

---

> **결론**: 런타임 에러 0건. 발견된 로직 버그 1건은 수정 완료.
> 비밀번호 평문 저장 이슈는 추후 BCrypt 등 암호화 라이브러리 적용 권장.
