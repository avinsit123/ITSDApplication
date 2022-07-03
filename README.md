# Helpr: IT Service Desk for all your needs

With the explosion in IT services, an IT Service Desk application to help respond to customer requests is the need of the hour. During my time at Atlassian, I contributed to one of the product leaders in this field, Jira Service Management. During our new grad showdown, I built a miniature version of JSM. This project is an extended version of the same to take care of the following needs :-
1. User Management Service(UMS) - Integrated Service to manage Customer, Users and Support People who will triage support requests.
2. Ticketing Application - Help customer, users to lodge, view and comment on support tickets. 
3. Triaging Service - Assist responders in triaging, commenting and responding to supoort tickets filed by customer

## Start-up
Currently, the repository only supports running your app in the local environment. 

### 1. Install Java on your local ( Skip this step if you already have java 11)
Install java 11 using sdkman

``` 
curl -s "https://get.sdkman.io" | bash
sdk install 11.0.15.1-librca
sdk use java 11.0.15.1-librca
```

### 2. Start up-postgres DB

```
docker-compose up itsddb
```

### 3. Startup the ITSD Application

```
./mvnw spring-boot:run  
```

## Using the Application

### Roles
Every user has one of the following roles
1. SUPER_ADMIN
2. ADMIN
3. USER
4. RESPONDER

| Actions         | Roles                  |
|-----------------|------------------------|
| Create Request  | USER, ADMIN            |
| View Request    | USER, ADMIN, RESPONDER |
| Triage Request  | RESPONDER              |
| Create User     | CUSTOMER               |
| Create Customer | CUSTOMER               |
| Add Responder   | SUPER_ADMIN            |
| *All actions*   | SUPER_ADMIN            |




