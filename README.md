# API de Hotéis

## Descrição

Esta API fornece funcionalidades para cadastrar, buscar e fazer reservas em hotéis com base em localizações.

## Tecnologias

- **JDK**: 17
- **Spring Boot**: 3.x
- **Banco de Dados**: PostgreSQL

## Endpoints - Consulte nosso Swagger

- **URL**: baseUrl + `/swagger-ui`

## SMTP - Configure no application.properties

```
spring.mail.host=seu_smtp
spring.mail.port=587
spring.mail.username=seu_email
spring.mail.password=sua_senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

## INSERTs - SQL'S disponibilizados para facilitar testes

```
-- Inserindo dados na tabela de hoteis
INSERT INTO hotel (id, name, location)
VALUES 
    (1, 'Hotel Copacabana Palace', 'Rio de Janeiro'),
    (2, 'Hotel Fasano', 'São Paulo'),
    (3, 'Hotel Bahia Othon Palace', 'Salvador'),
    (4, 'Hotel Unique', 'São Paulo'),
    (5, 'Hotel PortoBay Búzios', 'Búzios');


-- Inserindo dados na tabela de quartos
INSERT INTO room (id, type, price, hotel_id)
VALUES 
    (1, 'Single', 450.00, 1), -- Quarto Single no Hotel Copacabana Palace
    (2, 'Double', 600.00, 1), -- Quarto Double no Hotel Copacabana Palace
    (3, 'Single', 300.00, 2),  -- Quarto Single no Hotel Fasano
    (4, 'Double', 500.00, 2),  -- Quarto Double no Hotel Fasano
    (5, 'Suite', 1000.00, 3),  -- Suite no Hotel Bahia Othon Palace
    (6, 'Double', 700.00, 3),  -- Quarto Double no Hotel Bahia Othon Palace
    (7, 'Suite', 1200.00, 4),  -- Suite no Hotel Unique
    (8, 'Single', 550.00, 5),  -- Quarto Single no Hotel PortoBay Búzios
    (9, 'Double', 750.00, 5);  -- Quarto Double no Hotel PortoBay Búzios

```


