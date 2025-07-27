package com.ra.ss14.service;

import com.ra.ss14.model.dto.request.BookTicketRequest;
import com.ra.ss14.model.entity.AppUser;
import com.ra.ss14.model.entity.Showtime;
import com.ra.ss14.model.entity.Ticket;
import com.ra.ss14.repository.ShowtimeRepository;
import com.ra.ss14.repository.TicketRepository;
import com.ra.ss14.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ShowtimeRepository showtimeRepository;

    @Override
    public Ticket bookTicket(BookTicketRequest request, String username) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Ticket ticket = Ticket.builder()
                .user(user)
                .showtime(showtime)
                .seatNumber(request.getSeatNumber())
                .bookingTime(LocalDateTime.now())
                .price(request.getPrice())
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getMyTickets(String username) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ticketRepository.findByUser(user);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
