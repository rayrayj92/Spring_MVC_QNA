# Spring_MVC_QNA


## 개요
Spring Framework를 사용하여 Q&A 게시판 및 로그인 기능 구현

## 기술
* Spring Framework 5.2.5
* Spring Security 5.2.3 [BCrypt Encode]
* JUnit 4
* Oracle XE 18c
* MyBatis 3.5.4
* Bootstrap 4.4.1
* CK Editor 4.14.0

## 기능
* 공통
    * 모든 페이지 별 세션 체크 및 이용자 ROLE 체크
    * Input 값 유효성 체크[Back-End, Front-End(HTML5 Attribute)]
    * Interceptor 적용[Login, Role]
* 게시판
    * 검색[제목, 작성자]
    * 게시판 페이징
    * 작성[위지위그 CK Editor], 조회[조회수 증가], 수정, 삭제[일괄삭제]
    * 관리자 페이지[Pinned기능 - 공지사항으로 등록]
    * 내가 쓴 글
    * 좋아요, 댓글 기능
* 회원가입
    * 이메일 중복 체크[Ajax]
    * 비밀번호 암호화[BCrypt]
* 로그인
    * 비밀번호 암호화[BCrypt]
    * 세션 타이머 값 지정
    * Input 값 유효성 체크[Back-End, Front-End(HTML5 Attribute)]
    * 로그아웃 [세션 파괴]
* 에러 페이지
    * 커스텀 404 및 자바 예외처리 에러 페이지
    
## 데모
링크 -> 
    
## 코드
* **QNA Controller**

