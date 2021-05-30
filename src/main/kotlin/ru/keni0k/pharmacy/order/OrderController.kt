package ru.keni0k.pharmacy.order

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView
import ru.keni0k.pharmacy.order.OrderController.Companion.TEMPLATE

@Controller
@RequestMapping(TEMPLATE)
class OrderController(val repository: OrderRepository) {

    companion object {
        const val TEMPLATE = "orders"
    }

    @GetMapping
    fun template(
        modelMap: ModelMap,
        @RequestParam(required = false)
        inProgress: Boolean?
    ): String {
        inProgress?.let {
            modelMap.addAttribute(TEMPLATE, repository.findAllInProgress().sortedBy { it.id })
            modelMap.addAttribute("inProgress", true)
        } ?:
        run {
            modelMap.addAttribute(TEMPLATE,
                repository.findAll().sortedBy { it.id }
            )
        }
        return TEMPLATE
    }

    @GetMapping("/remove")
    fun remove(
        modelMap: ModelMap,
        @RequestParam id: String
    ): RedirectView {
        repository.deleteById(id)
        return RedirectView("/${TEMPLATE}")
    }

}