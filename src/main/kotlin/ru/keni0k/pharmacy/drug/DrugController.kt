package ru.keni0k.pharmacy.drug

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.keni0k.pharmacy.drug.DrugController.Companion.TEMPLATE

@Controller
@RequestMapping(TEMPLATE)
class DrugController(val repository: DrugRepository) {

    companion object {
        const val TEMPLATE = "drugs"
    }

    @GetMapping
    fun template(modelMap: ModelMap): String {
        modelMap.addAttribute(TEMPLATE, repository.findAll())
        return TEMPLATE
    }

}