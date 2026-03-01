# Spring pattern alignment refactoring checklist

This checklist is tailored for the current `trycatch_copy` codebase and aligned
to the extracted lecture pattern (`3-tier + MyBatis XML + DTO/VO separation`).
Use it as an execution list for automated refactoring passes.

## Scope and goal

- Scope: `src/main/java`, `src/main/resources/mapper`, `src/main/resources/sql`
- Goal: normalize naming, enforce layer boundaries, and remove ambiguous
  duplicates without changing user-facing behavior.
- Baseline style reference:
  - `code_summary.md`
  - `.claude/rules/spring-lecture-patterns.md`

## Phase 0: safety gates

- [ ] Create a dedicated branch for refactor work.
- [ ] Run baseline verification and save output logs:
  - [ ] `./gradlew clean test`
  - [ ] `./gradlew bootJar`
- [ ] Freeze schema migration changes during naming-only refactors.

Done when:
- [ ] Baseline tests/build pass before structural edits.

## Phase 1: naming normalization (highest priority)

Current mismatch hotspots found:
- `Alram` typo appears across classes, packages, mappers, xml, and services.
- `service/Alarm` directory uses upper-case package segment.

Tasks:
- [ ] Rename `Alram` -> `Alarm` consistently across:
  - Java types (`AlramDTO`, `AlramVO`, `IndividualAlramService`, etc.)
  - Package names (`service.Alarm` -> `service.alarm`)
  - Mapper interfaces and XML files (`*AlramMapper` -> `*AlarmMapper`)
  - SQL aliases, method names, and request attributes where needed
- [ ] Fix any import/package case mismatch after rename.

Done when:
- [ ] `rg -n "Alram|alram|service\\.Alarm"` returns no result.
- [ ] Build passes after rename-only commit.

## Phase 2: duplicate model cleanup by bounded context

Current duplicate class names found:
- `PointDetailsDTO/VO/DAO/Mapper` in both `mypage` and `point`
- `AlramVO` duplicated under `domain/alarm` and `vo/alarm`
- `StatusHandler` duplicated in two packages

Tasks:
- [ ] Decide canonical owner per bounded context:
  - `mypage` projection models stay in `mypage`
  - transactional point models stay in `point`
- [ ] Rename ambiguous duplicates to explicit names where both are needed:
  - Example: `MyPagePointDetailsDTO` vs `PointLedgerDetailsDTO`
- [ ] Remove deprecated duplicate package (`vo/*`) after references are migrated.
- [ ] Keep one authoritative MyBatis handler per enum domain.

Done when:
- [ ] `Group-Object Name` on Java files has no accidental duplicates.
- [ ] No cross-context import confusion remains.

## Phase 3: config consolidation

Current state:
- `WebMvcConfig` exists in root package.
- `InterceptorConfig` exists in `config` package.

Tasks:
- [ ] Move all MVC configuration classes into `config` package.
- [ ] Keep one clear config class per responsibility:
  - resource handlers
  - interceptors
  - (optional) argument resolvers/formatters
- [ ] Validate interceptor path patterns and exclude patterns.

Done when:
- [ ] No `@Configuration` class remains in root package by accident.
- [ ] All interceptor/resource behavior is preserved in integration checks.

## Phase 4: mapper/xml contract alignment

Current mismatch examples:
- XML filename without `Mapper` suffix (`individualMember.xml`)
- Mixed naming styles in mapper xml file names

Tasks:
- [ ] Enforce naming convention: `XxxMapper.java` <-> `xxxMapper.xml`.
- [ ] Validate every mapper interface has one namespace-matching XML.
- [ ] Verify statement ids match method signatures exactly.
- [ ] Standardize mapper folder by bounded context (`member`, `qna`, `skilllog`,
      `point`, `corporate`, `experience`, `alarm`).

Done when:
- [ ] Mapper interface count equals XML mapper count per context.
- [ ] Application starts with no MyBatis binding errors.

## Phase 5: service and transaction boundary hardening

Tasks:
- [ ] Ensure controllers do not call DAO/Mapper directly.
- [ ] Ensure write use cases are wrapped with `@Transactional`.
- [ ] Keep DTO/VO conversion in explicit methods (`toVO`, `fromVO`, mapper fn).
- [ ] Extract repeated conversion blocks into shared mapper utilities where useful.

Done when:
- [ ] No controller imports repository/mapper packages directly.
- [ ] Transaction policy is explicit for write operations.

## Phase 6: exception policy normalization

Tasks:
- [ ] Group domain exceptions under `common.exception`.
- [ ] Group handlers under `common.exception.handler`.
- [ ] Ensure each handler targets explicit package scope and response style
      (redirect vs JSON) intentionally.
- [ ] Remove dead exception types and unused handler methods.

Done when:
- [ ] Exceptions follow one routing policy per web surface (MVC vs API).
- [ ] No generic `RuntimeException` remains in core flow without wrapping reason.

## Phase 7: SQL and schema consistency pass

Tasks:
- [ ] Keep table naming `tbl_*` and snake_case columns.
- [ ] Keep audit columns consistent (`created_datetime`, `updated_datetime`).
- [ ] Verify FK relations and delete policy match service delete behavior.
- [ ] Align enum storage values and type handlers.

Done when:
- [ ] Schema scripts and mapper queries share identical naming conventions.
- [ ] No broken FK or enum handler mapping in integration tests.

## Phase 8: frontend integration consistency (optional but recommended)

Tasks:
- [ ] Keep JS module split pattern where possible:
  - `service.js` for fetch calls
  - `layout.js` for DOM rendering
  - `event.js` for event binding/state
- [ ] Standardize API endpoint naming (`/api/...`) and HTTP verb usage.

Done when:
- [ ] Major pages follow one client interaction pattern.

## Automated verification checklist

Run after each phase:

- [ ] `./gradlew test`
- [ ] `./gradlew bootJar`
- [ ] `rg -n "Alram|service\\.Alarm" src/main/java src/main/resources`
- [ ] `rg -n "@Controller|@RestController" src/main/java/com/app/trycatch/controller`
- [ ] `rg -n "namespace=\\\"com\\.app\\.trycatch\\.mapper" src/main/resources/mapper`

Release gate:
- [ ] All checks above pass.
- [ ] No new warnings from IDE on package/name mismatch.
- [ ] Refactor commits are split by phase for safe rollback.

