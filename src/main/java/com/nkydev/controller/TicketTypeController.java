package com.nkydev.controller;

import com.nkydev.dto.tickettype.TicketTypeRequestDTO;
import com.nkydev.dto.tickettype.TicketTypeResponseDTO;
import com.nkydev.service.TicketTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket-types")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketTypeResponseDTO createTicketType (@Valid @RequestBody TicketTypeRequestDTO request){
        return ticketTypeService.createTicketType(request);
    }

    @GetMapping("/{id}")
    public List<TicketTypeResponseDTO> getTicketsType (TicketTypeRequestDTO request){
        return ticketTypeService.getAllTicketsType(request);
    }

    @GetMapping("/{id}")
    public TicketTypeResponseDTO getTicketTypeById(@PathVariable Integer id){
        return ticketTypeService.getTicketTypeById(id);
    }

    @PutMapping("/{id}")
    public TicketTypeResponseDTO updateTicketType(@PathVariable Integer id, @Valid @RequestBody TicketTypeRequestDTO request){
        return ticketTypeService.updateTicketType(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicketType(@PathVariable Integer id) {
        ticketTypeService.deleteTicketType(id);
    }
}
