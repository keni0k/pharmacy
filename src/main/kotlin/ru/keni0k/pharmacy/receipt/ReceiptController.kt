package ru.keni0k.pharmacy.receipt

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.keni0k.pharmacy.drug.DrugRepository
import ru.keni0k.pharmacy.receipt.ReceiptController.Companion.TEMPLATE

@Controller
@RequestMapping(TEMPLATE)
class ReceiptController(
    val repository: ReceiptRepository,
    val drugRepository: DrugRepository
) {

    companion object {
        const val TEMPLATE = "receipts"
    }

    @GetMapping
    fun template(
        @RequestParam(required = false) id: String?,
        modelMap: ModelMap
    ): String {
        if (id != null) {
            modelMap.addAttribute("receipt",
                repository.findAllByParentDrugId(id)
                    .sortedBy { it.step }
            )
            return "receipt"
        } else {
            modelMap.addAttribute("drugs",
                drugRepository.findAllByManufacturedIsTrue()
            )
            return "receipts"
        }
    }
}