# 🎟️ Event & Ticket Purchase API

<p align="left">
  <img src="https://img.shields.io/badge/JAVA_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/SPRING_BOOT-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/SPRING_SECURITY-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security" />
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JWT" />
  <img src="https://img.shields.io/badge/POSTGRESQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/HIBERNATE-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate" />
  <img src="https://img.shields.io/badge/DOCKER-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
  <img src="https://img.shields.io/badge/POSTMAN-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="Postman" />
  <img src="https://img.shields.io/badge/GIT-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git" />
</p>

API REST robusta y lista para producción diseñada para la gestión de eventos, tipos de tickets, autenticación de usuarios mediante JWT y procesamiento completo de compras con validación y descuento en tiempo real de inventario.

---

## 🛠️ Tecnologías

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3+ (Spring Web, Spring Data JPA, Spring Security)
* **Seguridad:** JSON Web Tokens (JWT) & Role-Based Access Control (`USER`, `ORGANIZER`, `ADMIN`)
* **Base de Datos:** PostgreSQL (Dockerized)
* **Mapeo y Formato:** Jackson (`@JsonFormat` para `LocalDateTime`)
* **Manejo de Errores:** Centralizado vía `@RestControllerAdvice`
* **Herramientas:** Docker, Git, Postman, Maven

---

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas basada en **Controller-Service-Repository**, garantizando un bajo acoplamiento y transaccionalidad atómica (`@Transactional`):

```text
com.nkydev
├── config          # Configuración de Seguridad, JWT Filter y Beans
├── controller      # Endpoints REST de la API
├── dto             # Data Transfer Objects (Requests y Responses)
├── entity          # Entidades JPA, Enums y mapeos relacionales
├── exception       # Manejador global de excepciones (@RestControllerAdvice)
├── repository      # Interfaces Spring Data JPA
└── service         # Lógica de negocio, validaciones y transacciones
```

## 🔌 API Endpoints

### Authentication (/api/v1/auth)

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/auth/register` | Public | Register a new user |
| `POST` | `/api/v1/auth/login` | Puublic | Authenticate user and return JWT token |

### Categories (/api/v1/categories)

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/categories` | Admin / Organizer | Create a category |
| `GET` | `/api/v1/categories` | Public | Get all categories |
| `GET` | `/api/v1/categories/{id}` | Public | Get a category by ID |
| `PUT` | `/api/v1/categories/{id}` | Admin / Organizer | Update a category |
| `DELETE` | `/api/v1/categories/{id}` | Admin / Organizer | Delete a category |

### Events (/api/v1/events)

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/events` | Admin / Organizer | Create an event |
| `GET` | `/api/v1/events` | Public | Get all events |
| `GET` | `/api/v1/events/{id}` | Public | Get an event by ID |
| `PUT` | `/api/v1/events/{id}` | Admin / Organizer | Update an event |
| `DELETE` | `/api/v1/events/{id}` | Admin / Organizer | Delete an event |
| `GET` | `/api/v1/events/{eventId}/ticket-types` | Public | Get all ticket types for a specific event |

### Ticket Types (/api/v1/ticket-types)

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/ticket-types` | Admin / Organizer | Create a new ticket type |
| `GET` | `/api/v1/ticket-types` | Public | Get all ticket types |
| `GET` | `/api/v1/ticket-types/{id}` | Public | Get a ticket type by ID |

### Purchases (/api/v1/purchases)

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/purchases` | Authenticated | Create a purchase order |
| `GET` | `/api/v1/purchases` | Admin / Organizer | Get all purchases |
| `GET` | `/api/v1/purchases/{id}` | Authenticated | Get a purchase by ID |


### Payments (/api/v1/payments)

| Method | Endpoint | Access | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/payments` | Authenticated | Process payment for a purchase |
| `GET` | `/api/v1/payments/{id}` | Authenticated | Get payment details by ID |


## Ejemplos de Petición (Payload)

### Creacion de Evento (POST /api/v1/events):
```
{
  "name": "Lollapalooza Argentina",
  "description": "Festival de música",
  "date": "2026-11-15 18:00:00",
  "location": "Buenos Aires",
  "capacity": 50000,
  "category": {
    "id": 1
  }
}
```

### Creacion de Compra (POST /api/v1/purchases):
#### Requiere Header de Autorizacion Bearer JWT
```
{
  "items": [
    {
      "ticketTypeId": 1,
      "quantity": 2
    }
  ]
}
```

### Creacion de Pago (POST /api/v1/payments):
```
{
  "purchaseId": 1,
  "paymentMethod": "CREDIT_CARD",
  "cardNumber": "45321******..."
}
```

## ⚙️ Configuración e Instalación
Clonar repositorio:

```
git clone https://github.com/nky01/event-ticket-api.git
```

Levantar PostgreSQL con Docker:
```
docker compose up -d
```

Ejecutar la aplicación:
```
./mvnw spring-boot:run
```

## 👩‍💻 Autora

* **Nicole Belen Cayo** — Backend Developer
* **GitHub:** [@nky01](https://github.com/nky01)
