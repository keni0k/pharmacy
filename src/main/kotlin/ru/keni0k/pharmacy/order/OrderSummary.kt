package ru.keni0k.pharmacy.order

import ru.keni0k.pharmacy.client.Client
import ru.keni0k.pharmacy.drug.Drug
import ru.keni0k.pharmacy.ticket.Ticket
import java.math.RoundingMode
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity(name = "order_summary")
class OrderSummary(
    val discount: Int,
    @ManyToOne
    val client: Client,
    @Column(name = "client_comment")
    val clientComment: String,
    @ManyToOne
    val status: OrderStatus,
    @OneToMany(mappedBy = "orderSummary")
    val items: List<OrderItem>,
    @Column(name = "is_paid")
    val isPaid: Boolean,
    @Column(name = "create_time")
    val createTime: LocalDateTime
) {

    val cost
        get() =
            items.sumOf { it.price * it.cnt } * ((100f - discount) / 100)
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()


    @Id
    var id: String = UUID.randomUUID().toString()

    @Entity(name = "order_status")
    class OrderStatus(val name: String) {
        @Id
        var id: Long = 0
    }

    @Entity(name = "order_item")
    class OrderItem(
        val cnt: Int,
        @ManyToOne
        val drug: Drug,
        val price: Double,
        @ManyToOne(fetch = FetchType.EAGER)
        val orderSummary: OrderSummary,
        @OneToOne
        val ticket: Ticket?
    ) {
        @Id
        var id: Long = 0
    }

}