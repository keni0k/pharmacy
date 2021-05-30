package ru.keni0k.pharmacy.drug

import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DrugRepository : JpaRepository<Drug, String> {
    fun findAllByManufacturedIsTrue(): List<Drug>

    @Query(CRITICAL_QUERY, nativeQuery = true)
    fun findAllCriticalDrugs(): List<Drug>

    @Query(WARNING_QUERY, nativeQuery = true)
    fun findAllWarningDrugs(): List<Drug>

    @Query(SOLD_CNT, nativeQuery = true)
    fun getSoldVolume(@Param("drugId") drugId: String,
                      @Param("from") from: String = "01.01.2021",
                      @Param("to") to: String = "01.01.2022"): Int?

    companion object {
        @Language("SQL")
        const val WARNING_QUERY = """
            SELECT * FROM DRUG
            WHERE CNT <= MIN_CNT
        """

        @Language("SQL")
        const val CRITICAL_QUERY = """
            SELECT * FROM DRUG
            WHERE CNT = 0
        """

        @Language("SQL")
        const val SOLD_CNT = """
            SELECT SUM(OI.CNT) AS CNT
            FROM DRUG
            JOIN ORDER_ITEM OI on DRUG.ID = OI.DRUG_ID
            JOIN ORDER_SUMMARY O on O.ID = OI.ORDER_SUMMARY_ID
            WHERE O.STATUS_ID = 4
            AND DRUG_ID = :drugId
            AND O.CLOSE_TIME BETWEEN TO_DATE(:from, 'dd.mm.yyyy') AND
            TO_DATE(:to, 'dd.mm.yyyy')
        """
    }
}

@Repository
interface DrugTypeRepository : JpaRepository<Drug.DrugType, String>