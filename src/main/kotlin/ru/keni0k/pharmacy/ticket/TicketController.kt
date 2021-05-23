package ru.keni0k.pharmacy.ticket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import ru.keni0k.pharmacy.client.Client
import ru.keni0k.pharmacy.drug.DrugRepository
import ru.keni0k.pharmacy.ticket.TicketController.Companion.TEMPLATE
import java.util.logging.Logger
import javax.websocket.server.PathParam

@Controller
@RequestMapping(TEMPLATE)
class TicketController(
    val repository: TicketRepository,
    val drugRepository: DrugRepository,
    val ticketStatusRepository: TicketStatusRepository
) {

    companion object {
        const val TEMPLATE = "tickets"
    }

    @GetMapping
    fun template(modelMap: ModelMap): String {
        modelMap.addAttribute(TEMPLATE, repository.findAll().sortedBy { it.id })
        return TEMPLATE
    }

    @GetMapping("/{ticketId}")
    fun templateOne(
        modelMap: ModelMap,
        @PathVariable
        ticketId: Long
    ): String {
        modelMap.addAttribute(TEMPLATE, listOf(repository.getOne(ticketId)))
        return TEMPLATE
    }

    @PostMapping
    fun addTicket(modelMap: ModelMap, @ModelAttribute ticketRequest: TicketRequest): RedirectView {
        val logger = LoggerFactory.getLogger(TicketController::class.java)
        logger.info("${ticketRequest.drugId} : ${ticketRequest.cnt} : ${ticketRequest.manufacture}")
        repository.save(
            Ticket(
                drugRepository.getOne(ticketRequest.drugId),
                ticketRequest.cnt,
                ticketStatusRepository.getOne(1),
                ticketRequest.manufacture
            )
        )
        return RedirectView("/tickets")
    }

}