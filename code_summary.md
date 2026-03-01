# Spring day1~day12 코드 작성 패턴/양식 분석

- 분석 일시: 2026-03-01
- 분석 경로: `C:\Users\pigch\Desktop\gb_0090_kyc\Spring\강의자료`
- 결과 파일: `C:\Users\pigch\Desktop\trycatch_copy\code_summary.md`

## 1) 분석 범위와 기준

- 요청 범위는 day1~day12였지만, 실제 폴더는 `spring_day02`~`spring_day12`가 존재합니다.
- `spring_day01` 폴더는 존재하지 않았습니다.
- 본 분석은 `spring_day02`~`spring_day12`의 소스 파일 기준으로 집계했습니다.
- 집계 제외: `build/`, `.gradle/`, `target/`, `out/`, `bin/`, `.idea/`, `node_modules/`.
- ZIP 파일은 별도로 엔트리 메타데이터(개수/확장자 분포)까지 확인했습니다.

## 2) 전체 파일 통계 (소스 기준)

- 총 분석 파일: **899개**
- 주요 확장자:
  - `.java`: 500
  - `.html`: 100
  - `.js`: 66
  - `.sql`: 56
  - `.xml`: 48
  - `.gradle`: 30
  - `.css`: 28
  - `.yml`: 15

일자별 특징:
- `spring_day09`, `spring_day11`은 소스 폴더보다 ZIP 중심 구성입니다.
- `spring_day10`, `spring_day12`에서 코드량이 크게 증가합니다.

## 3) 패키지/레이어 구조 패턴

상위 패키지 빈도:
- `com.app.threetier.dto` (54)
- `com.app.threetier.domain` (46)
- `com.app.threetier.mapper` (46)
- `com.app.threetier.repository` (38)
- `com.app.threetier.common.*`, `com.app.threetier.mybatis.*` 계층 분리가 명확

디렉터리 역할 빈도:
- `controller` 71, `dto` 67, `common` 66, `domain` 66, `mapper` 66
- `repository` 38, `service` 34, `mybatis` 48

핵심 레이어 흐름:
- `Controller -> Service -> DAO(Repository) -> Mapper(MyBatis XML) -> DB`
- DTO/VO 변환 메서드(`toVO`, `toDTO`)를 서비스/DTO 내부에서 자주 사용

## 4) 네이밍 컨벤션 패턴

클래스 suffix 빈도:
- `DTO` 68, `VO` 56
- `Controller` 51
- `Mapper` 48
- `DAO` 38
- `Service` 34
- `Exception/ExceptionHandler`, `Criteria/Search` 등 공통 모듈 분리

DB/SQL 네이밍:
- 테이블 접두: `tbl_` (예: `tbl_member`, `tbl_post`, `tbl_reply`)
- 컬럼: snake_case (예: `created_datetime`, `updated_datetime`)
- 감사 컬럼 패턴: `created_datetime`, `updated_datetime` 반복 사용

## 5) 어노테이션/프레임워크 사용 패턴

상위 어노테이션 빈도:
- `@NoArgsConstructor` 163
- `@Getter` 153
- `@GetMapping` 138
- `@Override` 133
- `@EqualsAndHashCode` 117
- `@RequiredArgsConstructor` 112
- `@Mapper` 48
- `@Repository` 38, `@Controller` 35, `@Service` 34
- `@RestController` 16 (후반부 증가)

실무형 스타일:
- Lombok 적극 사용(`@Getter/@Setter/@RequiredArgsConstructor/@SuperBuilder`)
- Spring MVC + Thymeleaf + MyBatis 조합
- REST API는 `/api/...` 경로로 분리해 fetch 기반 JS와 연계

## 6) 빌드/설정 패턴

공통 의존성:
- `spring-boot-starter-web`, `thymeleaf`, `web-services`
- `mybatis-spring-boot-starter` + `mysql-connector-j`
- `lombok`, `spring-boot-starter-test`

후반부 확장 의존성:
- `spring-boot-starter-mail`
- `gson`, `thumbnailator`
- `com.solapi:sdk` (SMS)

`application.yml` 패턴:
- `server.port`
- `spring.datasource` (MySQL)
- `mybatis.type-handlers-package`
- 후반부에 `spring.servlet.multipart`, `spring.mail` 추가

## 7) MyBatis/SQL 작성 양식

Mapper XML 공통:
- `namespace`를 Java `Mapper` 인터페이스와 1:1 대응
- CRUD id 규칙: `insert`, `selectById`, `selectAll`, `update`, `delete`
- 동적 쿼리: `<if>`, `<choose>`, `<foreach>`, `<trim>`, `<where>` 적극 활용
- 페이징: `limit #{criteria.count} offset #{criteria.offset}`

SQL 설계 경향:
- PK `auto_increment`
- 상태값 enum/문자열 + type handler 매핑
- FK 기반 연관(`post`, `tag`, `file`, `reply`) 정규화

## 8) 프론트(템플릿/JS) 패턴

Thymeleaf:
- `th:replace`, `th:if`, `th:unless`, `th:each` 중심
- 공통 헤더 fragment 사용

JS 모듈 패턴(IIFE):
- `service.js`: fetch 통신 전담
- `layout.js`: 렌더링 전담
- `event.js`: DOM 이벤트/상태(page 등) 전담

REST 연계:
- `GET/POST/PUT/DELETE` 메서드 분리
- JSON body 송수신 + 서버 `@RequestBody` 매핑

## 9) day 흐름별 진화 요약

- day02~03: DI, MVC 바인딩, 기본 Controller 중심
- day04~06: MySQL/MyBatis 도입, DTO/VO/Mapper 정착
- day07~08: 3-tier(Controller-Service-Repository-Mapper) 본격화, OAuth/파일 업로드
- day10: 검색/페이징/무한스크롤, API 컨트롤러 확장
- day12: REST 댓글, 인터셉터, 메일/SMS, 예외 처리 고도화
- day09/day11: ZIP 제공 중심(학습 산출물 압축본)

## 10) 재사용 가능한 실전 코딩 규칙 (추출본)

1. 패키지 구조를 `controller/service/repository/mapper/dto/domain/common`으로 유지한다.
2. Controller는 요청/응답과 검증 흐름만 두고 비즈니스는 Service에 위임한다.
3. Service는 트랜잭션 경계를 가지며(`@Transactional`) DAO를 조합한다.
4. DAO는 Mapper 호출만 담당하고 SQL 세부는 XML로 분리한다.
5. DTO/VO 변환 메서드(`toVO`, `toDTO`)를 명시적으로 둔다.
6. 예외는 도메인별 custom exception + `@ControllerAdvice`로 처리한다.
7. 파일 업로드는 물리경로/DB메타를 분리 저장하고 삭제 시 DB+파일 동기 정리한다.
8. 페이징은 `Criteria` 같은 공통 객체로 통일하고 API 응답 DTO에 묶는다.
9. 프론트 JS는 `service/layout/event` 3분리로 유지한다.
10. SQL 네이밍은 `tbl_` + snake_case + audit 컬럼 규칙을 일관 적용한다.

## 11) ZIP 파일 분석 메모

- ZIP 파일(중첩 포함)도 확인했습니다.
- 대표적으로 day09/day11은 ZIP 엔트리 기반 학습 자료 비중이 높고, 내부에는 `.java/.class/.xml/.html`이 함께 존재합니다.
- 중복 ZIP(`spring_day10` 계열)은 버전별(v1~v4) 진화 샘플 보관 형태입니다.

