package com.nkydev.service;

import com.nkydev.repository.TicketTypeRepository;

public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }
}
