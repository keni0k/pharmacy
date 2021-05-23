package ru.keni0k.pharmacy.ticket

import ru.keni0k.pharmacy.client.Client
import ru.keni0k.pharmacy.drug.Drug
import java.util.*
import javax.persistence.*

@Entity(name = "ticket")
class Ticket(
    @ManyToOne
    val drug: Drug,
    val cnt: Int,
    @ManyToOne
    val status: TicketStatus,
    val isNeedToMake: Boolean
) {
    @Id
    var id: Long = 0

    @Entity(name = "ticket_status")
    class TicketStatus(val name: String) {
        @Id
        var id: Long = 0
    }

}