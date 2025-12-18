package com.eventmanage.controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eventmanage.entity.Event;
import com.eventmanage.service.EventService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/events")
public class EventController {
	 @Autowired private EventService service;
	 
	/* @PostMapping
	 public ResponseEntity<?> create(@RequestBody Event event, @RequestParam String role) {

	     if (!role.equals("ADMIN")) {
	         return ResponseEntity.status(403).body("Access Denied");
	     }

	     return ResponseEntity.ok(service.createEvent(event));
	 }*/
	 @PostMapping(consumes = {"multipart/form-data"})
	 public ResponseEntity<?> createEvent(
	         @RequestPart("event") Event event,
	         @RequestPart(value = "imageFile", required = false) MultipartFile image,
	         @RequestParam String role) {

	     try {
	         if (!role.equals("ADMIN")) {
	             return ResponseEntity.status(403).body("Access Denied");
	         }

	         // Save image if provided
	         String imageUrl = service.storeImage(image);
	         event.setImageUrl(imageUrl);

	         Event savedEvent = service.createEvent(event);
	         return ResponseEntity.ok(savedEvent);

	     } catch (Exception e) {
	         return ResponseEntity.status(500).body("Error saving event: " + e.getMessage());
	     }
	 }



	 @GetMapping public List<Event> all(){ return service.getAllEvents(); }
	 @GetMapping("/{id}") public Event get(@PathVariable Long id){ return service.getEventById(id); }
	 @PutMapping("/{id}")
	 public ResponseEntity<?> updateEvent(
	         @PathVariable Long id,
	         @RequestBody Event updatedEvent,
	         @RequestParam String role) {

	     if (!"ADMIN".equals(role)) {
	         return ResponseEntity.status(403)
	                 .body("Access Denied: Only ADMIN can update events");
	     }

	     Event updated = service.updateEvent(id, updatedEvent);
	     return ResponseEntity.ok(updated);
	 }
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> deleteEvent(
	         @PathVariable Long id,
	         @RequestParam String role) {

	     if (!"ADMIN".equals(role)) {
	         return ResponseEntity.status(403)
	                 .body("Access Denied: Only ADMIN can delete events");
	     }

	     service.deleteEvent(id);
	     return ResponseEntity.ok("Event deleted successfully");
	 }

	 


}
