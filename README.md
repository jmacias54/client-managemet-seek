# 🏦 Sistema de Gestión de Clientes - Microservicio

Microservicio para el registro, consulta y análisis de datos de clientes con Spring Boot y MySQL.

## 📋 Requisitos

- Java 21+
- MySQL 8+
- Maven 3.8+
- Docker (opcional)

## 🚀 Configuración Inicial

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/client-management-service.git
cd client-management-service

🌐 Endpoints de la API
Método	Endpoint	Descripción
POST	/api/clients	Crear nuevo cliente
GET	/api/clients	Listar todos los clientes
GET	/api/clients/metrics	Obtener métricas estadísticas
📘 Documentación completa de la API:
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs




🛠️ Configuración
Archivo principal: src/main/resources/application.yml
