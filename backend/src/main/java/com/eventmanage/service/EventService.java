package com.eventmanage.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventmanage.entity.Event;

public interface EventService {
	Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    void deleteEvent(Long id);
    String storeImage(MultipartFile file) throws IOException;

}
