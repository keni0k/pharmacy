package ru.keni0k.pharmacy.drug

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.view.RedirectView
import ru.keni0k.pharmacy.drug.DrugController.Companion.TEMPLATE

@Controller
@RequestMapping(TEMPLATE)
class DrugController(val repository: DrugRepository) {

    companion object {
        const val TEMPLATE = "drugs"
    }

    @GetMapping
    fun template(modelMap: ModelMap): String {
        val drugs = repository.findAll()
        val criticalDrugs = repository.findAllCriticalDrugs()
        val warningDrugs = repository.findAllWarningDrugs()
        drugs.forEach {
            if (it in criticalDrugs) it.critical = true
            if (it in warningDrugs) it.warning = true
            it.sold = repository.getSoldVolume(it.id) ?: 0
        }
        drugs.sortBy { it.critical }
        drugs.sortBy { it.warning }
        modelMap.addAttribute(TEMPLATE, drugs)
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