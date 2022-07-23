# java-new-features-test
a simple project to test Java new features

# Docker Database

```docker run -d --name localdb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=appdb mysql:8.0.29```

# Initialize Database

```gradle -Dflyway.configFiles=./flyway-local.conf```

# Swagger Documentation

UI: [http://localhost:8080/api/docs/swagger-ui/index.html](http://localhost:8080/api/docs/swagger-ui/index.html)

JSON: [http://localhost:8080/api/docs/swagger.json](http://localhost:8080/api/docs/swagger.json)

# JUnit Coverage Report

```gradle jacocoRootReport```

# Health Check URL

[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
