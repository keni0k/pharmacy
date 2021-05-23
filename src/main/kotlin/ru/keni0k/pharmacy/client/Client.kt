package ru.keni0k.pharmacy.client

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery

@Entity(name = "client")
class Client(
    @Column(name = "fio")
    val fio: String,
    @Column(name = "phone")
    val phone: String,
    @Column(name = "address")
    var address: String?
    ) {

    @Id
    var id: String = UUID.randomUUID().toString()

}