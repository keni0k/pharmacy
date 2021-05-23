package ru.keni0k.pharmacy.ticket

import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository: JpaRepository<Ticket, Long>

interface TicketStatusRepository: JpaRepository<Ticket.TicketStatus, Long>