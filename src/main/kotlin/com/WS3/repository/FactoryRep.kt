package com.WS3.repository

import com.WS3.model.Factories
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@ComponentScan
@Repository
interface FactoryRep : CrudRepository<Factories?, Int?> {
    abstract fun save(factory: Factories?)
}