package ru.keni0k.pharmacy.client

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import ru.keni0k.pharmacy.client.ClientController.Companion.TEMPLATE

@Controller
@RequestMapping(TEMPLATE)
class ClientController(val repository: ClientRepository) {

    companion object {
        const val TEMPLATE = "clients"
    }

    @GetMapping
    fun template(
        modelMap: ModelMap,
        @RequestParam(required = false)
        kidals: Boolean?,
        @RequestParam(required = false)
        waiters: Boolean?,
    ): String {
        kidals?.let {
            modelMap.addAttribute(TEMPLATE, repository.findAllKidals())
            modelMap.addAttribute("kidals", true)
        } ?:
        waiters?.let {
            modelMap.addAttribute(TEMPLATE, repository.findAllWaiters())
            modelMap.addAttribute("waiters", true)
        } ?:
        run {
            modelMap.addAttribute(
                TEMPLATE,
                repository.findAll()
            )
        }
        return TEMPLATE
    }

    @PostMapping
    fun addClient(modelMap: ModelMap, @ModelAttribute client: Client): RedirectView {
        repository.save(client)
        return RedirectView("/clients")
    }

    @GetMapping("/remove")
    fun remove(
        modelMap: ModelMap,
        @RequestParam id: String
    ): RedirectView {
        repository.deleteById(id)
        return RedirectView("/$TEMPLATE")
    }

}