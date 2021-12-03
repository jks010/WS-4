package com.WS3.controller

import com.WS3.model.Factories
import com.WS3.service.*
import com.opencsv.CSVReader
import com.opencsv.exceptions.CsvException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FactoryController {
    @Autowired
    private val factoryService: FactoryService? = null
    @GetMapping("/getF")
    fun findit(): List<Factories?>? {
        return factoryService?.findAll()
    }

    @GetMapping("/getF/{id}")
    fun findCar(@PathVariable(value = "id") id: Int): Factories? {
        return factoryService?.findbyId(id)
    }

    @PostMapping("/createF")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    fun save(
        @RequestParam("id") id: Int,
        @RequestParam("name") name: String?,
        @RequestParam("countryCode") countryCode: Int
    ): List<Factories?>? {
        val factory = Factories(id,name,countryCode)
        return factoryService?.Add(factory)
    }

    @PutMapping("/updateF/{id}")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    fun put(
        @PathVariable(value = "id") id: Int,
        @RequestParam("name") name: String?,
        @RequestParam("countryCode") countryCode: Int
    ): Factories {
        var factory = Factories()
        factory = factoryService?.savebyId(id, name, countryCode)!!
        return factory
    }

    @DeleteMapping("/deleteF/{id}")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    fun deleteCar(@PathVariable(value = "id") id: Int): List<Factories?>? {
        return factoryService?.delete(id)
    }
}