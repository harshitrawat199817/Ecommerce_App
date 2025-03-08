package com.example.NotificationService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {

	String userId;
	String type;
	String message;
	String recipient;
	
	
	
}
