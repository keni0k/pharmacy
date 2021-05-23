package ru.keni0k.pharmacy.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderSummary, String>

interface OrderStatusRepository: JpaRepository<OrderSummary.OrderStatus, Long>

interface OrderItemRepository: JpaRepository<OrderSummary.OrderItem, String> {
    fun findAllByOrderSummaryId(orderSummaryId: String): List<OrderSummary.OrderItem>
}