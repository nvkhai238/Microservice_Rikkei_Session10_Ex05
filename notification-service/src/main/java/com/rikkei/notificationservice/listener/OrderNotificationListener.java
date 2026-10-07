package com.rikkei.notificationservice.listener;

import com.rikkei.pharmacyservice.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderNotificationListener {
    private static final Logger log = LoggerFactory.getLogger(OrderNotificationListener.class);

    @KafkaListener(topics = "medicine-stock-events", groupId = "notification-service")
    public void onOrder(OrderEvent event) {
        log.info("Hóa đơn cho đơn hàng {} đã được gửi tới khách hàng", event.orderId());
    }
}
