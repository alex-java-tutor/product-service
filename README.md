# Схема микросервисов

![MicroservicesOverview.jpg](art%2FMicroservicesOverview.jpg)

# Product Service API

- `POST /v1/products` - создать товар, информация о товаре передается в теле запроса (доступно для сотрудников магазина, информация о сотруднике передается в токене доступа)
- `DELETE /v1/products/{id}` - удалить товар (доступно для сотрудников, информация о сотруднике передается в токене доступа)
- `PATCH /v1/products/{id}` - обновить товар, параметры обновления передаются в теле запроса (доступно для сотрудников, информация о сотруднике передается в токене доступа)
- `GET /v1/products/{id}` - получить товар (доступно всем пользователям)
- `GET /v1/products?sort={sort}&from={from}&size={size}` - получить пагинированный список товаров, отсортированный или по алфавиту(AZ, ZA), или по цене (PRICE_ASC, PRICE_DESC), или по дате создания (DATE_ASC, DATE_DESC). (доступно всем пользователям)
- `POST /v1/products/product-info` - получить информацию о доступности товаров. В теле запроса передается информация об идентификаторах интересующих товаров. В ответ попадает информация о наличии или отсутствии товара и его цена.

## Как запустить проект
1. Собрать образы всех микросервисов и инфраструктуры: в корневой директории проектов Product Service, Config Service и Service Discovery выполнить команду `./gradlew bootBuildImage`
2. Перейти в директорию product-service/infra и запустить скрипт командой: `./start-containers-old-docker.sh`. Если 
   этот файл не является исполняемым, то перед запуском скрипта выполнить команду: `chmod +x start-containers-old-docker.sh`.
   Эта команде сделает файл исполняемым, тогда можно будет его запустить.
3. Запустить скрипт с запросами к Product Service: `./submit-requests.sh`


## Полезные ссылки
1. Переопределяем правильно equals и hashcode в Spring Data JPA https://jpa-buddy.com/blog/hopefully-the-final-article-about-equals-and-hashcode-for-jpa-entities-with-db-generated-ids/
2. Документация по Externalized Configuration (тут есть приоритеты конфигураций): https://docs.spring.io/spring-boot/reference/features/external-config.html
3. Проекции Spring Data JPA: https://www.baeldung.com/spring-data-jpa-projections
4. Способы создания Docker-образов Spring Boot приложений: https://www.baeldung.com/spring-boot-docker-images



# Service Discovery

![MicroservicesDiscoveryService.jpg](art%2FMicroservicesDiscoveryService.jpg)

# Config Server

Документация Spring Cloud Config Server: https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_spring_cloud_config_server

Spring Cloud Config Server может одновременно хранить конфигурационные файлы нескольких микросервисов. Для того, чтобы определить, какие файлы принадлежат какому микросервису, используются следующие параметры в названии файлов:

- `{application}`- название приложения, определенное в свойстве `spring.application.name`
- `{profile}`- название активного профиля приложения
- `{label}`- дополнительный тэг. В случае с Git репозиторием это может быть название ветки, тэг или идентификатор коммита.

# Команды Docker
- `docker ps` - вывести список всех запущенных контейнеров
- `docker ps -a` - вывести список запущенных и остановленных контейнеров
- `docker volume ls` - вывести список volumes
- `docker volume rm VOLUME_NAME` - удалить volume с названием VOLUME_NAME
- `docker image ls` - вывести список образов
- `docker image rm IMAGE_NAME` - удалить образ с названием IMAGE_NAME
- `docker logs --follow CONTAINER_NAME` - отслеживать логи контейнера с названием CONTAINER_NAME
- `docker exec -it CONTAINER_NAME COMMAND` - выполнить команду COMMAND в контейнере с названием CONTAINER_NAME

# Команды Gradle
- `./gradlew clean build` - провести чистую сборку проекта (тесты будут также запущены)
- `./gradlew clean build -x test` - провести чистую сборку без запуска тестов
- `./gradlew test` - запустить тесты
- `./gradlew bootBuildImage` - собрать образ проекта (доступно только для Spring Boot проектов)