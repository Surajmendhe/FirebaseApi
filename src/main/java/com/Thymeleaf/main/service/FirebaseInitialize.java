package com.Thymeleaf.main.service;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitialize {
   
	@PostConstruct
	public void initialize()
	{
		try {
		FileInputStream serviceAccount =
				new FileInputStream("./serviceAccount.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://demofire-c19c6.firebaseio.com")
				  .build();

				FirebaseApp.initializeApp(options);

	}catch(Exception e) {
		e.printStackTrace();
	}
	}
}
