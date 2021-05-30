package ru.keni0k.pharmacy.drug

import ru.keni0k.pharmacy.utils.VolumeType
import java.util.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity(name = "drug")
class Drug(
    val name: String,
    @ManyToOne
    @JoinColumn(name = "type")
    val type: DrugType,
    var price: Double,
    val isNeedReceipt: Boolean,
    val manufactured: Boolean,
    val cnt: Int,
    val volume: Double,
    @ManyToOne
    @JoinColumn(name = "volume_type")
    val volumeType: VolumeType,
    val minCnt: Int,
    val isForSale: Boolean,
    val targetCnt: Int,
    @Transient
    var critical: Boolean = false,
    @Transient
    var warning: Boolean = false,
    @Transient
    var sold: Int = 0
) {

    @Id
    var id: String = UUID.randomUUID().toString()


    @Entity(name = "DRUG_TYPE")
    class DrugType(val name: String) {

        @Id
        @GeneratedValue
        var id: Long = 0
    }

}