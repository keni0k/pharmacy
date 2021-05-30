package ru.keni0k.pharmacy.order

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView
import ru.keni0k.pharmacy.order.OrderItemController.Companion.TEMPLATE

@Controller
@RequestMapping(TEMPLATE)
class OrderItemController(
    val orderRepository: OrderRepository,
    val repository: OrderItemRepository
) {

    companion object {
        const val TEMPLATE = "items"
    }

    @GetMapping
    fun template(modelMap: ModelMap, @RequestParam id: String): String {
        modelMap.addAttribute("order", orderRepository.findById(id))
        modelMap.addAttribute(TEMPLATE, repository.findAllByOrderSummaryId(id))
        return TEMPLATE
    }

    @GetMapping("/remove")
    fun remove(
        modelMap: ModelMap,
        @RequestParam id: Long
    ): RedirectView {
        val item = repository.getOne(id)
        repository.delete(item)
        return RedirectView("/${TEMPLATE}?id=" + item.orderSummary.id)
    }

}
