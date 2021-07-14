# 메이븐 패키징

```shell
./mvnw package
```

```shell
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ reactive-5 ---
[INFO] Building jar: D:\workspace\github\spring-boot-master\reactive-5\target\reactive-5-0.0.1.jar
[INFO]
[INFO] --- spring-boot-maven-plugin:2.5.2:repackage (repackage) @ reactive-5 ---
[INFO] Replacing main artifact with repackaged archive
```
maven-jar-plugin 명령이 컴파일된 코드를 JAR 파일로 만들어 target 디렉토리 아래에 둔다.  
여기서의 JAR 파일은 아직 실행가능한 JAR 파일이 아니고, 컴파일 된 코드의 묶음이다.  

그 다음 spring-boot-maven-plugin 명령이 컴파일된 파일 모음인 JAR 파일과  
어플리케이션이 사용하는 의존 라이브러리와  
특별한 스프링 부트 코드 입루를 함께 묶어 새 JAR 파일 생성 후  
기존 JAR 파일을 대체한다.  

바로 이떄 만들어진 JAR 파일이 실행 가능한 JAR 파일이다.

### 패키징 뜯어보기
```shell
java tvf target/{application-name}.jar
```
