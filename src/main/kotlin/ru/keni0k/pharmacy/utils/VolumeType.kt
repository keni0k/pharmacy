package ru.keni0k.pharmacy.utils

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "volume_type")
class VolumeType(val name: String) {
    @Id
    @GeneratedValue
    var id: Long = 0
}