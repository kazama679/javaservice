package com.ra.ss14.repository;

import com.ra.ss14.model.entity.AppUser;
import com.ra.ss14.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(AppUser user);
}
