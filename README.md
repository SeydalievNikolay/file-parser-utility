# Инструкция по запуску и использованию реестра техники

## Установка и запуск

1. Убедитесь, что у вас установлены JDK версии 8.

2. Клонируйте репозиторий с GitHub:
git clone https://github.com/SeydalievNikolay/the-register-of-equipment.git cd the-register-of-equipment

4. Скомпилируйте проект:
mvn clean install

5. Запустите приложение:
java -jar target/TheRegisterOfEquipmentApplication-1.0-SNAPSHOT.jar


6. Откройте Swagger UI в браузере:
   - Перейдите по адресу http://localhost:8080/swagger-ui.html


## Настройка базы данных

При первом запуске приложения будет создана база данных PostgreSQL. Если вы хотите использовать другую базу данных, измените параметры в файле `src/main/resources/application.properties`:
spring.datasource.url=jdbc:postgresql://localhost:5432/registry
spring.datasource.username=postgres
spring.datasource.password=postgres
