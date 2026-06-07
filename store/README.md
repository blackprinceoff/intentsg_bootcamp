# Store — Spring Boot REST Application

REST додаток на Spring Boot — магазин продуктів з MVC структурою.

## Як запустити

```bash
./mvnw spring-boot:run
```

## Endpoints

- `GET /api/products` — повернути всі продукти
- `GET /api/products/{id}` — повернути один по ID (404 якщо не знайдено)
- `POST /api/products` — створити новий продукт

### Приклад POST запиту

```json
{
  "name": "Keyboard",
  "price": 79.99,
  "description": "Mechanical keyboard",
  "category": "Electronics"
}
```

## Task 2 — обраний компонент: Spring Validation

Обрав Spring Validation (`spring-boot-starter-validation`), бо хотілося зробити нормальну перевірку вхідних даних.
Додав анотації `@NotBlank` та `@Positive` на поля Product, а в контролері `@Valid` перед `@RequestBody`.
Якщо відправити продукт з порожнім name або від'ємною ціною — повернеться 400 з описом помилки.

## Task 3 — AOP

Зробив два аспекти:
- **LoggingAspect** — `@Before`, логує виклики методів Service шару (назва методу + аргументи)
- **ExecutionTimeAspect** — `@Around`, вимірює час виконання методів Controller шару в мілісекундах

### Чому через аспекти, а не просто в сервісі?

Логування і метрики — це не бізнес-логіка, а допоміжна функціональність. Якщо це все засунути прямо в сервіс чи контролер, то код стає захаращеним і його важче підтримувати. З аспектами можна додати або прибрати логування для всього шару одним рядком, не чіпаючи сам сервіс. Плюс не треба копіпастити один і той же log.info() в кожен метод.
