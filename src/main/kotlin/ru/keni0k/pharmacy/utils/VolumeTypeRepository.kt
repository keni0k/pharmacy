package ru.keni0k.pharmacy.utils

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VolumeTypeRepository: JpaRepository<VolumeType, Long> {
}