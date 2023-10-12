# Процедура запуска автотестов

1. На компьютере должен быть установлен Docker.
2. На компьютере должна быть установлена программа IntelliJ Idea.
3. Открываем проект в IntelliJ Idea.
4. Запускаем контейнеры в Docker. Для этого в терминале IntelliJ Idea вводим команду `docker compose up -d`
5. Открываем приложение-эмулятор банковских сервисов.
Открываем второй терминал в IntelliJ Idea и вводим команду:
`java -jar artifacts/aqa-shop.jar`
6. Запускаем автотесты.
В командной строке вводим команду:
`gradlew test`

### Запуск PostgreSQL

Для запуска данной БД достаточно проделанных действий, так как в файле 'application.properties' уже прописан запуск именно PostgheSQL.

```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=app
spring.datasource.password=pass
```

### Запуск MySQL

Для запуска данной БД необходимо изменить файл 'application.properties' следующим образом:

```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
spring.datasource.url=jdbc:mysql://localhost:3306/app
spring.datasource.username=app
spring.datasource.password=pass
```

И далее повторяем действия по запуску автотестов 1-6.
