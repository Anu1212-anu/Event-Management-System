package com.eventmanage.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventmanage.entity.Event;
import com.eventmanage.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event updatedEvent) {

        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event Not Found"));

        existing.setEventName(updatedEvent.getEventName());
        existing.setEventDate(updatedEvent.getEventDate());
        existing.setLocation(updatedEvent.getLocation());
        existing.setOrganizer(updatedEvent.getOrganizer());
        existing.setDescription(updatedEvent.getDescription());
        existing.setCategory(updatedEvent.getCategory());

        return eventRepository.save(existing);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event Not Found"));
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    /*public String storeImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        String folder = "uploads/"; // relative to project root (or choose absolute)
        File dir = new File(folder);
        if (!dir.exists()) dir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(dir, fileName);
        file.transferTo(dest);

        // Return URL to access the file (WebConfig below will serve /uploads/**)
        return "http://localhost:8080/uploads/" + fileName;
    }*/
    public String storeImage(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        String folder = System.getProperty("user.dir") + "/uploads/";
        File directory = new File(folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(folder + fileName);
        file.transferTo(dest);

        return "http://localhost:8080/uploads/" + fileName;
    }

}
