## Spring security 구현
### 구현조건
1. 사용자 정보
   1. 기본정보: 이메일, 이름, 생년월일(YYYYMMDD)
      1. 로그인 아이디는 이메일로 대체
   2. 회원가입 시 이메일, 이름, 생년월일으로 가입
      1. 가입 시 정보 검증 필수
2. 구현규칙
   1. lombok은 사용하지 않는다.
   2. tab indent 2로 설정한다.
   3. spring security 설정은 5.x 이후로 구현한다.
   4. entity, dto 관계를 구분지어서 구현한다.
      1. Service - Dao: entity
      2. Controller - Service: Dto
   5. 유효성 검증은 dto <> entity 변환 간 검증한다.
   6. 각 계층 별 단위테스트 코드를 작성한다.
