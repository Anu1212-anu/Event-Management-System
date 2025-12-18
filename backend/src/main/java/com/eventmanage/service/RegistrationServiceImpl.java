package com.eventmanage.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmanage.entity.Event;
import com.eventmanage.entity.Registration;
import com.eventmanage.entity.User;
import com.eventmanage.repository.EventRepository;
import com.eventmanage.repository.RegistrationRepository;
import com.eventmanage.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Registration register(Long userId, Long eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event Not Found"));

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegistrationDate(LocalDate.now().toString());
        registration.setStatus("CONFIRMED");

        return registrationRepository.save(registration);
    }

	/*@Override
	public Registration registerEvent(Long userId, Long eventId, String selectedDate) {
		User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Event event = eventRepository.findById(eventId)
	            .orElseThrow(() -> new RuntimeException("Event not found"));

	    Registration reg = new Registration();
	    reg.setUser(user);
	    reg.setEvent(event);
	    reg.setStatus("CONFIRMED");
	    reg.setRegisteredDate(LocalDate.now());
	    reg.setSelectedEventDate(selectedDate);

	    return registrationRepository.save(reg);
	
	}*/
    
	







	@Override
	public List<Registration> getAllRegistrations() {
		// TODO Auto-generated method stub
		return registrationRepository.findAll();
	}

	@Override
	public Registration registerEvent(Long userId, Long eventId, LocalDate selectedDate) {
		// TODO Auto-generated method stub
		if (selectedDate.isBefore(LocalDate.now())) {
	        throw new RuntimeException("Past dates are not allowed");
	    }

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Event event = eventRepository.findById(eventId)
	            .orElseThrow(() -> new RuntimeException("Event not found"));

	    Registration reg = new Registration();
	    reg.setUser(user);
	    reg.setEvent(event);
	    reg.setRegistrationDate(selectedDate.toString());

	    reg.setStatus("CONFIRMED");
	    
	    if (registrationRepository.existsByUserAndEvent(user, event)) {
	        throw new RuntimeException("You have already booked this event");
	    }


	    return registrationRepository.save(reg);
	}

	@Override
	public List<Registration> getBookingsByUser(Long userId) {
		 User user = userRepository.findById(userId)
		            .orElseThrow(() -> new RuntimeException("User not found"));

		    return registrationRepository.findByUser(user);
	}

	@Override
	public void cancelBooking(Long regId) {
	    Registration reg = registrationRepository.findById(regId)
	            .orElseThrow(() -> new RuntimeException("Booking not found"));

	        reg.setStatus("CANCELLED");
	        registrationRepository.save(reg);
	    }
	
	
	


	
}

