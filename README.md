# auth-service
Authentication microservice built with Spring Boot implementing token-based security, OTP validation and authentication flow orchestration.

```mermaid
flowchart LR

Client[Client / Frontend]

Gateway[API Gateway]

AuthController[AuthController]

AuthService[Authentication Service]

JWT[JWT Token Generator]

OTP[OTP Service]

ExternalOTP[External OTP Provider\nSMS / Email]

SessionRepo[AuthSessionRepository]

SessionDB[(PostgreSQL\nAuth Sessions)]

UserRepo[UserRepository]

UserDB[(Users Table)]

Client --> Gateway
Gateway --> AuthController

AuthController --> AuthService

AuthService --> JWT
AuthService --> OTP
AuthService --> SessionRepo
AuthService --> UserRepo

OTP --> ExternalOTP

SessionRepo --> SessionDB
UserRepo --> UserDB
