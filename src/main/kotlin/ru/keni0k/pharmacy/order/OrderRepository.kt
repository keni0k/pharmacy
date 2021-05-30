package ru.keni0k.pharmacy.order

import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository : JpaRepository<OrderSummary, String> {

    @Query(QUERY, nativeQuery = true)
    fun findAllInProgress(): List<OrderSummary>

    companion object {
        @Language("SQL")
        const val QUERY = """
            SELECT *
            FROM ORDER_SUMMARY
            WHERE STATUS_ID <= 3
        """
    }
}

interface OrderStatusRepository : JpaRepository<OrderSummary.OrderStatus, Long>

interface OrderItemRepository : JpaRepository<OrderSummary.OrderItem, Long> {
    fun findAllByOrderSummaryId(orderSummaryId: String): List<OrderSummary.OrderItem>
}