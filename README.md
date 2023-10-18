# wanted-pre-onboarding-backend

## 서비스 개요

- 본 서비스는 기업의 채용을 위한 웹 서비스 입니다.
- 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

## 요구사항 정의 및 과정 정리

- 주요 기능 및 구성 요소들에 대한 설명
## 0. 회사는 아래 데이터와 같이 채용공고를 등록할 수 있다.
      
```
{
  "companyId":회사_id,
  "position":"백엔드 주니어 개발자",
  "compensation":1000000,
  "description":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "tech":"Python"
}
```
- 회사 id와 보상금은 Null일 수 없다.
- 채용포지션은 공백일 수 없으며 최대 64글자여야 한다.
- 설명은 공백일 수 없으며 최대 200글자여야 한다.
- 사용기술은 공백일 수 없으며 최대 10글자여야 한다.

### 구현 과정
0-1. 회사가 없다면 CompanyNotFoundException 을 던지게 구현  
0-2. @Valid 어노테이션으로 body 검증 

## 1. 회사는 아래 데이터와 같이 채용공고를 수정할 수 있으며 회사 id이외 모두 수정이 가능하다.

```
{
  "position":"백엔드 주니어 개발자",
  "compensation":1500000, # 변경됨
  "description":"원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..", # 변경됨
  "tech":"Python"
}
```

### 구현 과정
1-1. 채용공고가 없다면 JobOpeningNotFoundException 을 던지게 구현  
1-2. 마찬가지로 @Valid 어노테이션으로 body 검증  
1-3. jpa 더티체킹으로 값 수정

## 2. 채용 공고 삭제가 가능하다.
### 구현 과정
2-1. 채용공고가 없다면 JobOpeningNotFoundException 을 던지게 구현  

## 3. 채용 공고 목록 확인 및 search param으로 검색이 가능하다.
- 검색시 모든 String 필드에 대하여 검색한다.
```
# ?search=원티드  
[
  {
    "id": 채용공고_id,
    "name":"원티드랩",
    "country":"한국",
    "region":"서울",
    "position":"백엔드 주니어 개발자",
    "compensation":1500000,
    "tech":"Python"
  },
  {
    "id": 채용공고_id,
    "name":"원티드코리아",
    "country":"한국",
    "region":"부산",
    "position":"프론트엔드 개발자",
    "compensation":500000,
    "tech":"javascript"
  }
]
```
### 구현 과정
3-1. 채용 공고 목록 확인은 기본 jpa에서 제공하는 findAll메서드로 구현  
3-2. search param 검색시 querydsl을 사용하여 구현

```java
// JobOpeningRepositoryImpl.java
...
@Override
    public List<JobOpening> findJobOpeningListBySearch(String search) {
        return jpaQueryFactory
            .selectFrom(jobOpening)
            .where(searchEq(search))
            .fetch();
    }

    private BooleanExpression searchEq(String search) {
        if (search == null) {
            return Expressions.TRUE;
        }
        return jobOpening.company.name.contains(search)
            .or(jobOpening.company.country.contains(search))
            .or(jobOpening.company.region.contains(search))
            .or(jobOpening.description.contains(search))
            .or(jobOpening.position.contains(search))
            .or(jobOpening.tech.contains(search));
    }
```

## 4. 채용상세 조회가 가능하다.
  - 채용내용과 해당 회사가 올린 다른 채용공고 가 추가적으로 포함된다.

### 구현 과정
4-1. 채용공고가 없다면 JobOpeningNotFoundException 을 던지게 구현  
4-2. getAnotherJobOpening 메서드로 회사가 올린 다른 채용공고를 포함하도록 구현
```java
// JobOpeningDetailResponseDto
...
private static List<Long> getAnotherJobOpening(Company company) {
      return company.getJobOpeningList()
          .stream()
          .map(JobOpening::getId)
          .collect(Collectors.toList());
}
```

## 5. 사용자는 아래 데이터와 같이 채용공고에 지원할 수 있다.
```
{
  "jobOpeningId": 채용공고_id,
  "memberId": 사용자_id
}
```
- 채용공고 id와 사용자 id는 Null일 수 없다.
  
### 구현 과정
5-1. 채용공고가 없다면 JobOpeningNotFoundException 을 던지게 구현  
5-2. 멤버가 없다면 MemberNotFoundException 을 던지게 구현  
5-3. @Valid 어노테이션으로 body 검증 

## Database  
<img width="419" alt="image" src="https://github.com/min050410/wanted-pre-onboarding-backend/assets/45661217/d69e2260-7f10-4ec1-9a7c-9ce3d67a466d">

## Swagger  
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
<img width="1443" alt="image" src="https://github.com/min050410/wanted-pre-onboarding-backend/assets/45661217/04d01199-6007-48bc-bb48-fa30c880568a">

## Code Convention
구글 자바 코드 컨벤션을 준수하였습니다.  
[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
