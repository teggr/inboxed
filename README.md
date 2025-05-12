# link-streams

A web app for sharing links via a feed for other services such as Feed Readers to consume

```bash
mvn verify spring-boot:build-image

docker run -e SPRING_PROFILES_INCLUDE=image -p 8080:8080 -v  C:\Users\robin\test:/tmp/app link-streams:0.0.1-SNAPSHOT
```
