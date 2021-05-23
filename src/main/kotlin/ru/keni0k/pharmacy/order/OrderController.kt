package ru.keni0k.pharmacy.order

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.keni0k.pharmacy.order.OrderController.Companion.TEMPLATE

@Controller
@RequestMapping(TEMPLATE)
class OrderController(val repository: OrderRepository) {

    companion object {
        const val TEMPLATE = "orders"
    }

    @GetMapping
    fun template(modelMap: ModelMap): String {
        modelMap.addAttribute(TEMPLATE, repository.findAll().sortedBy { it.id })
        return TEMPLATE
    }

}