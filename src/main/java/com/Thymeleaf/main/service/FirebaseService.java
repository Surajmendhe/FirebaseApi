package com.Thymeleaf.main.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.Thymeleaf.main.entity.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {

	public String saveUserDetails(Person person) throws InterruptedException, ExecutionException {
		Firestore dbfirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbfirestore.collection("users").document(person.getName()).set(person);
		
		return collectionsApiFuture.get().getUpdateTime().toString();
	}
	
	public Person getUserDetails(String name) throws InterruptedException, ExecutionException{
		Firestore dbfirestore = FirestoreClient.getFirestore();
	    DocumentReference documentReference = dbfirestore.collection("users").document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		Person person = null;
		
		if(document.exists()) {
            person = document.toObject(Person.class);
            return person;
	    }else {
            return null;
      }
	}
	
	public String deleteUserDetails(String name) {
		Firestore dbfirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbfirestore.collection("users").document(name).delete();
		
		return name + " Data is Deleted Successfully...";
	}
	
	
	public String UpdateUserDetails(Person person) throws InterruptedException, ExecutionException
	{
		Firestore dbfirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbfirestore.collection("users").document(person.getName()).set(person);
		
		return collectionsApiFuture.get().getUpdateTime().toString();
	}
}
