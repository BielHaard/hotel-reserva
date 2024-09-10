# API de Hotéis

## Descrição

Esta API fornece funcionalidades para buscar hotéis com base em localizações. Utiliza Spring Boot 3.x, JDK 17 e banco de dados PostgreSQL.

## Tecnologias

- **JDK**: 17
- **Spring Boot**: 3.x
- **Banco de Dados**: PostgreSQL

## Endpoints

### Buscar Hotéis

- **URL**: `/hotels/search`
- **Método**: `POST`
- **Descrição**: Busca hotéis com base na localização fornecida.
- **Corpo da Requisição**:

  ```json
