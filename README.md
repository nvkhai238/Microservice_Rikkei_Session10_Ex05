# Session 10 — Exercise 05: Kafka config từ Git qua Config Server

Pharmacy-Service lấy broker và topic từ `pharmacy-config-repo/pharmacy-service.properties`. Config Server dùng chính Git repository này làm Git backend và đọc `pharmacy-config-repo/` bằng search path. `OrderProducerService` dùng `@RefreshScope`, nên thay topic có thể nhận cấu hình mới qua `/actuator/refresh`.

## Chạy

Đảm bảo repo đã có commit trên nhánh `main`, rồi mở ba terminal:

```bash
docker compose up -d
gradle :config-server:bootRun
gradle :pharmacy-service:bootRun
gradle :inventory-service:bootRun
gradle :notification-service:bootRun
```

Kiểm tra Config Server: `curl http://localhost:8888/pharmacy-service/default`.

Đổi `spring.kafka.template.default-topic` trong `pharmacy-config-repo/pharmacy-service.properties`, commit thay đổi, rồi làm mới Pharmacy-Service:

```bash
git add pharmacy-config-repo/pharmacy-service.properties && git commit -m 'Update pharmacy Kafka topic'
curl -X POST http://localhost:8090/actuator/refresh
```

Topic mới phải tồn tại trên broker trước khi gửi đơn. Broker mặc định là `localhost:9092`; có thể thay bằng biến `KAFKA_BOOTSTRAP_SERVERS`.
