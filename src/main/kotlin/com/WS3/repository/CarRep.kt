package com.WS3.repository

import com.WS3.model.Cars
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@ComponentScan
@Repository
interface CarRep : CrudRepository<Cars?, Int?>