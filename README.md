# NewMock Project

Учебный проект на Spring Boot, который реализует эмулятор сервиса, возвращающий данные, сгенерированные на основе входящих запросов.

## Использование

### Установка и запуск (в Linux)

```bash
mvn clean package
java -jar target/newMock-0.0.1-SNAPSHOT.jar
```

### Пример использования

```bash
curl -X POST http://localhost:8080/info/postBalances \
-H "Content-Type: application/json" \
-d '{
"rqUID": "58dgtf565j8547f64ke7",
"clientId": "3050000000000000000",
"account": "30500000000000000001",
"openDate": "2020-01-01",
"closeDate": "2025-01-01"
}'
```

### Дополнтительная настройка приложения
#### Переопределение порта

По умолчанию приложение запускается на порту 8080. Его можно изменить с помощью параметра самой java `-Dserver.port`:

```bash
java -Dserver.port=8880 -jar target/newMock-0.0.1-SNAPSHOT.jar
```

или с помощью параметра Spring Boot `--server.port`:

```bash
java -jar target/newMock-0.0.1-SNAPSHOT.jar --server.port=8880
```

#### Настройка памяти

Настройка выделенной памяти для приложения:

```bash
java -jar target/newMock-0.0.1-SNAPSHOT.jar --server.port=8880 -Xms1g -Xmx2g
```

где:

- Xms1g задает начальный размер кучи (1 ГБ).
- Xmx2g задает максимальный размер кучи (2 ГБ).

#### Запуск в фоновом режиме

Для запуска приложения в фоновом режиме используется утилита `nohup`:

```bash
nohup java -jar target/newMock-0.0.1-SNAPSHOT.jar --server.port=8880 -Xms1g -Xmx1g &
```

#### Вывод логов

Для сохранения логов в файл используется перенаправление:

```bash
nohup java -jar target/newMock-0.0.1-SNAPSHOT.jar --server.port=8880 -Xms1g -Xmx1g > newMock8880.log &
```

где `newMock8880.log` — это файл, в который будут записываться логи приложения.
