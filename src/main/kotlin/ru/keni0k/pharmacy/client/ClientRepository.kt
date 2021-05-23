package ru.keni0k.pharmacy.client

import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, String> {

    @Query(kidalsQuery, nativeQuery = true)
    fun findAllKidals(): List<Client>

    @Query(waitersQuery, nativeQuery = true)
    fun findAllWaiters(): List<Client>

    companion object {

        @Language("SQL")
        const val commonQuery: String =
            "SELECT DISTINCT client.id, client.fio, client.phone, client.address\n" +
            "FROM order_summary\n" +
            "JOIN client on order_summary.client_id = client.id\n" +
            "WHERE order_summary.status_id ="

        const val kidalsQuery: String = "$commonQuery 6"

        const val waitersQuery: String = "$commonQuery 3"
    }

}