# Auth8

**Auth8** (short for **Auth Infinity**) is a lightweight, secure, and fully OAuth2-compliant **Token Exchange Authorization Server**, built using **Spring Boot 3+**. 

The "8" in the name represents the **infinity symbol (∞)** rotated horizontally — highlighting the continuous, extensible nature of token-based identity.

---

## ✨ Functional Overview

### Purpose
Auth8 serves as a **token exchange server** that:
- Receives an incoming identity token (subject_token) from an external IdP
- Verifies its authenticity using the IdP's JWKS endpoint
- Issues a new access token, tailored to internal microservices
- Optionally issues a refresh token
- Optionally manages refresh token rotation
- Supports secure client authentication using client_id and client_secret

### Key Features
- ✅ **100% OAuth 2.0 Token Exchange compliant** (per [RFC 8693](https://tools.ietf.org/html/rfc8693))
- ✅ Compatible with any OIDC IdP (Keycloak, Auth0, Azure AD, Okta, etc.)
- ✅ JWT-based custom access token generation
- ✅ Optional refresh token support
- ✅ Role and scope mapping
- ✅ Built-in client authentication
- ✅ Secure-by-default with fine-grained HTTP method and endpoint restrictions

---

## 🧱 Technical Architecture

### Stack
- Java 23
- Spring Boot 3+
- Spring Security + OAuth2 Resource Server
- Jackson (JSON serialization)
- JWT via Nimbus (JOSE/JWT)
- Data provider NoSQL, using MongoDB

### Token Flow
```mermaid
graph TD
    A[Client] -->|POST /oauth2/token| B[Auth8 Server]
    B -->|Validate subject_token (JWT)| C[External IdP (e.g., Keycloak)]
    C -->|JWKS| B
    B -->|Custom JWT| A
```

### Authentication
- Clients authenticate via HTTP Basic (`client_id`:`client_secret`)
- Client credentials are validated against a **custom service layer**, supported by MongoDB.

### Token Exchange Support
- Grant Type: `urn:ietf:params:oauth:grant-type:token-exchange`
- Subject Token Type: `urn:ietf:params:oauth:token-type:access_token`
- Supports validation of `subject_token_type`
- JWTs issued contain custom claims (`aud`, `scope`, `client_id`, etc.)

---

## 📝 Token Endpoint

### ✨ Request: `/oauth2/token`
- Method: `POST`
- Content-Type: `application/x-www-form-urlencoded`
- Authentication: HTTP Basic

### 👀 Input Parameters
| Parameter              | Required | Description                                     |
|------------------------|----------|-------------------------------------------------|
| `grant_type`           | Yes      | Must be `urn:ietf:params:oauth:grant-type:token-exchange` |
| `subject_token`        | Yes      | JWT token from external IdP                    |
| `subject_token_type`   | Yes      | Usually `urn:ietf:params:oauth:token-type:access_token` |
| `scope`                | Optional | Space-separated scopes to request              |

---

### 📃 Output (Response Body)
| Field             | Description                                      |
|------------------|--------------------------------------------------|
| `access_token`    | Newly issued JWT access token                   |
| `token_type`      | Always `Bearer`                                 |
| `expires_in`      | Token lifetime in seconds                       |
| `issued_token_type` | Usually `urn:ietf:params:oauth:token-type:access_token` |
| `refresh_token`   | Optional, if supported                          |
| `scope`           | Granted scope                                   |
| `aud`             | Audience of the issued token (custom claim)     |

---

## 🌐 Environment Config
Defined via `application.yml`:
```yaml
oauth2:
  token:
    jwks-uri: http://localhost:8080/realms/myrealm/protocol/openid-connect/certs
    access-token:
      ttl-minutes: 720 # 12 hours
    refresh-token:
      ttl-minutes: 1440 # 1 day
      rotation: true
    claims:
      issuer: "com.playground.auth8"
      audience: "com.playground.*"
```

---

## 🤔 Future Enhancements
- ✅ JWKS endpoint for issued tokens
- ✅ Dynamic IdP discovery
- ✅ Per-client policies (scope restriction, aud mapping)
- ✅ Multi-tenancy support
- ✅ Admin UI / dashboard

## 🤔 Technical Enhancements
- ✅ Database encryption / hashing
- ✅ HTTPS support
- ✅ Logging

---

## 🌟 Contributing
Contributions are welcome! 

Open an issue or submit a pull request to improve the spec compliance, security, or developer experience.

---

## ✉ Contact
Created with ❤ by [Ricardo Petronilho](https://www.linkedin.com/in/ricardo-petronilho-126a511b2)