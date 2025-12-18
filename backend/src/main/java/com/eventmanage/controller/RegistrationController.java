package com.eventmanage.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanage.entity.Registration;
import com.eventmanage.service.RegistrationService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/registrations")
public class RegistrationController {
	@Autowired 
	private RegistrationService service;
    @PostMapping("/{userId}/{eventId}")
    public Registration register(@PathVariable Long userId, @PathVariable Long eventId) {
        return service.register(userId, eventId);
    }
    
    @PostMapping("/book")
    public ResponseEntity<?> registerEvent(@RequestBody Map<String, String> body) {

    	Long userId = Long.parseLong(body.get("userId"));
        Long eventId = Long.parseLong(body.get("eventId"));
        //String selectedDate = body.get("selectedDate");
        LocalDate selectedDate = LocalDate.parse(body.get("selectedDate"));

        Registration reg = service.registerEvent(userId, eventId, selectedDate);

        return ResponseEntity.ok(reg);
    }
    
    @GetMapping("/all")
    public List<Registration> getAll() {
        return service.getAllRegistrations();
    }
    
    @GetMapping("/user/{userId}")
    public List<Registration> getUserBookings(@PathVariable Long userId) {
        return service.getBookingsByUser(userId);
    }
    
   //for cancel and update userbooked events
    @PutMapping("/cancel/{regId}")
    public ResponseEntity<?> cancel(@PathVariable Long regId) {
        service.cancelBooking(regId);
        return ResponseEntity.ok("Booking cancelled");
    }



    


}
