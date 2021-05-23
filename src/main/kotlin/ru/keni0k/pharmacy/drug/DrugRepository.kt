package ru.keni0k.pharmacy.drug

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DrugRepository : JpaRepository<Drug, String> {
    fun findAllByManufacturedIsTrue(): List<Drug>
}

@Repository
interface DrugTypeRepository : JpaRepository<Drug.DrugType, String>