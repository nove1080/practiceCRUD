# Practice CRUD

## 프로젝트 설정

### 프로젝트를 Fork 합니다.

<img width="1715" alt="스크린샷 2023-09-22 오후 4 03 59" src="https://github.com/belljun3395/practiceCRUD/assets/102807742/4bf1e237-a6ee-47f8-8c28-b6b8204d2d3d">

### Fork한 프로젝트를 clone 합니다.

```
https://github.com/{githubID}/practiceCRUD.git
```

### 프로젝트 환경을 구성합니다.

해당 프로젝트는 docker-compose를 이용하여 개발환경을 구성합니다.
따라서 docker-compose를 설치해야 합니다.

```
cd scripts/

./demo-develop-env-reset
```

## 프로젝트 구현 목표

캡스톤 프로젝트를 구현하기전 CURD 기능을 구현해보고자 합니다.

### 구현 요구 사항

Foo 클래스는 예제입니다.

Foo 클래스를 참고하지만 그 방식으로 구현하지 않아도 됩니다.

참고한 이후 삭제하셔도 됩니다.

<br>

깃 훅을 적용합니다. (커밋 하기전에 훅을 적용하여 코드를 정렬해줍니다.)
```
./gradlew :installGitHooks
```

<br>

커밋 단위는 최대한 적게 유지할 수 있도록 노력합니다. (한번에 여러 기능을 구현하지 않습니다.)

다만 여러 파일을 한번에 커밋하여야 한다면 패키지 단위로 커밋 합니다.

<br>

indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다.

2까지만 허용한다.

예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.

힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.

<br>

3항 연산자를 쓰지 않는다.

<br>

함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.

### 구현 API 스펙

```
POST /api/v1/users

회원가입과 로그인 모두 하나의 API를 통해 처리합니다.

요청
{
    "name" : "name",
    "age" : age,
    "email" : "email@email.com",
    "password" : "password"
}

응답
{
  "data": {
    "id": 1,
    "authToken": {
      "accessToken": "accessToken",
      "refreshToken": "refreshToken"
    }
  },
  "message": "성공",
  "code": "success"
}
```

```
POST /api/v1/record

요청
멤버 식별을 Bearer 토큰을 통하여 식별합니다.
{
    "title" : "title",
    "content" : "content"
}

응답
{
  "message": "성공",
  "code": "success"
}
```

```
GET /api/v1/record?id={id}

요청
멤버 식별을 Bearer 토큰을 통하여 식별합니다.

응답
{
  "data": {
    "rid": 1,
    "title": "title",
    "content": "content"
  },
  "message": "성공",
  "code": "success"
}
```

```
PUT /api/v1/record

요청
멤버 식별을 Bearer 토큰을 통하여 식별합니다.
{
    "rid" : id,
    "title" : "title",
    "content" : "content"
}

응답
{
  "data": {
    "rid": 1,
    "title": "edited title",
    "content": "edited content"
  },
  "message": "성공",
  "code": "success"
}
```

```
DELET /api/v1/record

요청
멤버 식별을 Bearer 토큰을 통하여 식별합니다.
{
    "rid" : id
}

응답
{
  "message": "성공",
  "code": "success",
  "data" : null
}
```

### 프로젝트 구현 후 제출

https://github.com/belljun3395/practiceCRUD 에 PR를 보냅니다.

PR 형식은 아래와 같습니다.

``` markdown
## 🤷🏻 어떤 코드 인가요?

자신이 작성한 코드에 대해서 전반적인 설명을 작성해 주세요

## 👀 무엇을 의주로 보면 될까요

그 중에서 조금 더 중점적으로 보면 좋을 코드를 알려주세요
```
