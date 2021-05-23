package ru.keni0k.pharmacy.receipt

import ru.keni0k.pharmacy.drug.Drug
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "ingredient")
class Ingredient(
    val step: Int,
    @ManyToOne
    val drug: Drug,
    @ManyToOne
    val parentDrug: Drug,
    val cnt: Int,
    val description: String
) {
    @Id
    @GeneratedValue
    private val id: Long = 0
}