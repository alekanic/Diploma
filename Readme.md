# Процедура запуска автотестов

1. На компьютере должен быть установлен Docker.
2. На компьютере должна быть установлена программа IntelliJ Idea.
3. Открываем проект в IntelliJ Idea.
4. Запускаем контейнеры в Docker. Для этого в терминале IntelliJ Idea последовательно вводим команды:
`docker build .`, `docker compose up`
5. Открываем приложение-эмулятор банковских сервисов.
Открываем второй терминал в IntelliJ Idea и вводим команду:
`java -jar artifacts/aqa-shop.jar`
6. Запускаем автотесты.
В командной строке вводим команду:
`gradlew test`
