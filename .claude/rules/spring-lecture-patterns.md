# Spring lecture patterns (day02~day12)

## Scope
- Source: `C:\Users\pigch\Desktop\gb_0090_kyc\Spring\강의자료\spring_day02` ~ `spring_day12`
- Use these rules as the default coding style for new Spring modules in this repo.

## Architecture
- Keep layered flow: `Controller -> Service -> Repository(DAO) -> Mapper(XML)`.
- Keep package split: `controller`, `service`, `repository`, `mapper`, `dto`, `domain`, `common`.
- Keep custom common modules: `common.exception`, `common.pagination`, `common.search`.

## Spring style
- Use constructor injection with `@RequiredArgsConstructor` and `final` fields.
- Keep controllers thin. Put business logic and orchestration in services.
- Use `@Transactional(rollbackFor = Exception.class)` on write-heavy services.
- Use `@ControllerAdvice` for domain-specific exception routing.

## MyBatis style
- Pair each mapper interface with one XML namespace 1:1.
- Keep CRUD id names predictable: `insert`, `selectById`, `selectAll`, `update`, `delete`.
- Use dynamic SQL tags (`if`, `choose`, `foreach`, `where`) for search filters.
- Use shared pagination criteria (`limit #{criteria.count} offset #{criteria.offset}`).

## DTO/VO style
- Keep DTO and VO separated.
- Provide explicit conversion methods (`toVO`, `toDTO`, `toMemberVO`, etc.).
- Keep audit/time fields consistent (`createdDatetime`, `updatedDatetime`).

## Naming and DB
- Keep table names as `tbl_*`.
- Keep DB columns in snake_case (`created_datetime`, `updated_datetime`).
- Keep entity/API names in CamelCase and suffix by role (`DTO`, `VO`, `DAO`, `Service`).

## Frontend interaction
- Keep JS split into `service.js`, `layout.js`, `event.js`.
- Use REST endpoints under `/api/*` for fetch-based async flows.
- Keep Thymeleaf for page templates and fragment reuse (`th:replace`, `th:if`, `th:each`).

## Feature progression pattern
- Start from MVC view flow, then add DB mapper layer, then add service/repository separation.
- Add pagination/search as shared common objects, then extend to REST modules.
- Add infra features (mail/sms/interceptor) without breaking base layer separation.
