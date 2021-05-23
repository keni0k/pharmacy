package ru.keni0k.pharmacy.receipt

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.keni0k.pharmacy.drug.Drug

@Repository
interface ReceiptRepository: JpaRepository<Ingredient, Long> {

    fun findAllByParentDrugId(drugId: String): List<Ingredient>

}