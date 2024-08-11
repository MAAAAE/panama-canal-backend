# panama-canal-backend



## 환경변수

아래는 `application.yml`에서 사용되는 환경변수들에 대한 간단한 설명입니다. 각 환경변수의 역할과 설정 방법을 설명합니다.

---

## 환경변수 설정 목록

### 0. `ENCRYPTION_KEY`
- **설명**: 당신의 secret을 암호화하기 위한 대칭키입니다. **외부에 유출되어서는 안됩니다.**
- **설정 방법**: `ENCRYPTION_KEY=<12자리 비밀키>`

### 1. `SPRING_APPLICATION_NAME`
- **설명**: 애플리케이션의 이름을 설정합니다.
- **기본값**: `panama-canal`
- **설정 방법**: `SPRING_APPLICATION_NAME=your-app-name`

### 2. `SPRING_DATASOURCE_URL`
- **설명**: 데이터베이스 연결 URL을 설정합니다.
- **기본값**: `jdbc:sqlite:./data/panama.db?cache=shared`
- **설정 방법**: `SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydb`

### 3. `SPRING_DATASOURCE_DRIVERCLASSNAME`
- **설명**: 데이터베이스 드라이버 클래스 이름을 설정합니다.
- **기본값**: `org.sqlite.JDBC`
- **설정 방법**: `SPRING_DATASOURCE_DRIVERCLASSNAME=com.mysql.cj.jdbc.Driver`

### 4. `SPRING_JPA_HIBERNATE_DDL_AUTO`
- **설명**: Hibernate의 DDL 자동 생성 모드를 설정합니다.
- **기본값**: `update`
- **설정 방법**: `SPRING_JPA_HIBERNATE_DDL_AUTO=none`

### 5. `SPRING_JPA_SHOW_SQL`
- **설명**: SQL 쿼리 로그 출력을 활성화합니다.
- **기본값**: `true`
- **설정 방법**: `SPRING_JPA_SHOW_SQL=false`

### 6. `SPRING_JPA_DATABASE_PLATFORM`
- **설명**: Hibernate에서 사용할 데이터베이스 플랫폼을 설정합니다.
- **기본값**: `org.hibernate.community.dialect.SQLiteDialect`
- **설정 방법**: `SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.MySQL5Dialect`

### 7. `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI`
- **설명**: JWT 발급자의 URI를 설정합니다.
- **기본값**: `http://localhost:18080/realms/myrealm`
- **설정 방법**: `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI=http://your-auth-server/realms/your-realm`

### 8. `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK_SET_URI`
- **설명**: JWT 검증에 사용할 JWK 세트의 URI를 설정합니다.
- **기본값**: `http://localhost:18080/realms/myrealm/protocol/openid-connect/certs`
- **설정 방법**: `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK_SET_URI=http://your-auth-server/realms/your-realm/protocol/openid-connect/certs`

### 9. `SPRINGDOC_API_DOCS_PATH`
- **설명**: Springdoc OpenAPI API 문서의 경로를 설정합니다.
- **기본값**: `/api-docs`
- **설정 방법**: `SPRINGDOC_API_DOCS_PATH=/api-docs`

### 10. `SPRINGDOC_SWAGGER_UI_PATH`
- **설명**: Swagger UI의 경로를 설정합니다.
- **기본값**: `/swagger-ui.html`
- **설정 방법**: `SPRINGDOC_SWAGGER_UI_PATH=/swagger-ui.html`

### 11. `SPRINGDOC_OPERATIONS_SORTER`
- **설명**: Swagger UI에서의 연산 정렬 방법을 설정합니다.
- **기본값**: `method`
- **설정 방법**: `SPRINGDOC_OPERATIONS_SORTER=alpha`

### 12. `LOGGING_LEVEL_ORG_HIBERNATE_SQL`
- **설명**: Hibernate SQL 로그의 로깅 수준을 설정합니다.
- **기본값**: `debug`
- **설정 방법**: `LOGGING_LEVEL_ORG_HIBERNATE_SQL=info`

---

이 환경변수들은 `application.yml` 파일의 설정을 환경변수로 대체할 수 있게 해줍니다. 각 변수는 애플리케이션의 동작을 조정하며, 환경에 맞는 적절한 값을 설정하여 사용할 수 있습니다.