package com.example.NotificationService.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.NotificationService.event.NotificationEvent;

@Service
public class KafkaNotificationConsumer {

	@KafkaListener(topics = "notificationTopic", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
	public void consume(NotificationEvent message) {
		System.out.println("Consumed message: " + message);
	}
}
