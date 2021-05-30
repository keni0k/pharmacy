package ru.keni0k.pharmacy.ticket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import ru.keni0k.pharmacy.client.Client
import ru.keni0k.pharmacy.drug.DrugController
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

    @GetMapping("/remove")
    fun remove(
        modelMap: ModelMap,
        @RequestParam id: Long
    ): RedirectView {
        repository.deleteById(id)
        return RedirectView("/${TEMPLATE}")
    }

    @PostMapping
    fun addTicket(modelMap: ModelMap, @ModelAttribute ticketRequest: TicketRequest): RedirectView {
        val drug = drugRepository.getOne(ticketRequest.drugId)
        repository.save(
            Ticket(
                drug,
                ticketRequest.cnt,
                ticketStatusRepository.getOne(1),
                drug.manufactured,
            )
        )
        return RedirectView("/tickets")
    }

}