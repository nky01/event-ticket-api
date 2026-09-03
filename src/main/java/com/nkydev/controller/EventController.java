package com.nkydev.controller;

import com.nkydev.dto.event.EventRequestDTO;
import com.nkydev.dto.event.EventResponseDTO;
import com.nkydev.dto.tickettype.TicketTypeResponseDTO;
import com.nkydev.entity.Event;
import com.nkydev.service.EventService;
import com.nkydev.service.TicketTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;
    private final TicketTypeService ticketTypeService;

    public EventController(EventService eventService, TicketTypeService ticketTypeService) {
        this.eventService = eventService;
        this.ticketTypeService = ticketTypeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZER')")
    public EventResponseDTO createEvent(@Valid @RequestBody EventRequestDTO request) {
        return eventService.createEvent(request);
    }

    @GetMapping
    public List<EventResponseDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventResponseDTO getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @eventSecurity.isOrganizer(#id, authentication.name)")
    public EventResponseDTO updateEvent(@PathVariable Integer id, @Valid @RequestBody EventRequestDTO request){
        return eventService.updateEvent(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or @eventSecurity.isOrganizer(#id, authentication.name)")
    public void deleteEvent(@PathVariable Integer id){
        eventService.deleteEvent(id);
    }

    @GetMapping("/{eventId}/ticket-types")
    public List<TicketTypeResponseDTO> getTicketTypesByEventId(@PathVariable Integer eventId){
        return ticketTypeService.getTicketTypesByEventId(eventId);
    }
}
