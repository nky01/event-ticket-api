package com.nkydev.controller;

import com.nkydev.dto.tickettype.TicketTypeResponse;
import com.nkydev.entity.TicketType;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket-types")
public class TicketTypeController {

    public void createTicketType (@Valid @RequestBody TicketType ticketType){

    }

}
