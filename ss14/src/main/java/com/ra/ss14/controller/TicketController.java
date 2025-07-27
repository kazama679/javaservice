package com.ra.ss14.controller;

import com.ra.ss14.model.dto.request.BookTicketRequest;
import com.ra.ss14.model.entity.Ticket;
import com.ra.ss14.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Ticket> bookTicket(@RequestBody BookTicketRequest request, Principal principal) {
        Ticket ticket = ticketService.bookTicket(request, principal.getName());
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Ticket>> getMyTickets(Principal principal) {
        List<Ticket> tickets = ticketService.getMyTickets(principal.getName());
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }
}
