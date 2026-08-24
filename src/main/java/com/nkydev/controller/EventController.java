package com.nkydev.controller;

import com.nkydev.dto.event.EventRequestDTO;
import com.nkydev.dto.event.EventResponseDTO;
import com.nkydev.entity.Event;
import com.nkydev.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventResponseDTO createEvent(@RequestBody EventRequestDTO eventRequestDTO){
        return eventService.createEvent(eventRequestDTO);
    }

    @GetMapping
    public List<EventResponseDTO> getEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventResponseDTO getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public EventResponseDTO updateEvent(@PathVariable Integer id, @RequestBody EventRequestDTO eventRequestDTO){
        return eventService.updateEvent(id, eventRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id){
        eventService.deleteEvent(id);
    }
}
