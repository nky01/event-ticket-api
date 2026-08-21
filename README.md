# 🎟️ Event & Ticket API

<p align="left">
  <img src="https://img.shields.io/badge/JAVA_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/SPRING_BOOT-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/POSTGRESQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/HIBERNATE-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate" />
  <img src="https://img.shields.io/badge/POSTMAN-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="Postman" />
  <img src="https://img.shields.io/badge/GIT-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git" />
  <img src="https://img.shields.io/badge/DOCKER-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
</p>
API REST diseñada para la gestión y venta de entradas para eventos, recitales y festivales de música 🎶

---

## 🛠️ Tecnologías

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3+ (Spring Data JPA, Spring Web)
* **Base de Datos:** PostgreSQL (Dockerized)
* **Mapeo JSON:** Jackson (`@JsonFormat` para `LocalDateTime`)
* **Herramientas:** Git, Postman, Maven

---

## 🏗️ Arquitectura

El proyecto sigue el patrón **Controller-Service-Repository**:

```text
com.nkydev
├── controller    # Endpoints REST
├── service       # Lógica de negocio y transacciones (@Transactional)
├── repository    # Interfaces Spring Data JPA
└── entity        # Entidades JPA y mapeo relacional
```

## 🔌 API Endpoints

### Categories

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/categories` | Create a category |
| `GET` | `/api/v1/categories` | Get all categories |
| `GET` | `/api/v1/categories/{id}` | Get a category by ID |
| `PUT` | `/api/v1/categories/{id}` | Update a category |
| `DELETE` | `/api/v1/categories/{id}` | Delete a category |

### Events

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/events` | Create an event |
| `GET` | `/api/v1/events` | Get all events |
| `GET` | `/api/v1/events/{id}` | Get an event by ID |
| `PUT` | `/api/v1/events/{id}` | Update an event |
| `DELETE` | `/api/v1/events/{id}` | Delete an event |

## Ejemplos de Petición (Payload POST /api/v1/events):
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
}`
```

## ⚙️ Configuración e Instalación
Clonar repositorio:

```
git clone [https://github.com/tu-usuario/event-ticket-api.git](https://github.com/tu-usuario/event-ticket-api.git)
```

Levantar PostgreSQL con Docker:
```
docker run --name postgres-db -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=ticket_db -p 5432:5432 -d postgres
```

Ejecutar la aplicación:
```
./mvnw spring-boot:run
```

## 👩‍💻 Autora

* **Nicole Belen Cayo** — Backend Developer
* **GitHub:** [@nky01](https://github.com/nky01)
