SuperChat Backend Challenge **Mikhail Shapialevich**

Steps to explore app: 

1) open code in IDE
2) open terminal and run `docker compose up -d`. _Docker should be started._
3) run application by IDE tools or run `./gradlew clean bootRun`
4) go to http://localhost:8080/swagger-ui/index.html to explore endpoints and schemas for the app

**NOTE**: for secured endpoints used Basic Authentication (username:password)

Available placeholders: 
1) @fullname - fullname of user who send the message
2) @contact - fullname of the contact to whom message send
3) @bitcoin - current bitcoin price in USD