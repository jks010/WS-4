package com.WS3.controller

import com.WS3.model.Cars
import com.WS3.service.CarService
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader

@RestController
class CarController {
    @Autowired
    private val carService: CarService? = null
    @GetMapping("/getC")
    fun findit(): List<Cars?>? {
        return carService?.findAll()
    }

    @GetMapping("/getC/{id}")
    fun findCar(@PathVariable(value = "id") id: Int): Cars? {
        return carService?.findbyId(id)
    }

    @PostMapping("/createC")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    fun save(
        @RequestParam("id") id: Int,
        @RequestParam("factory_id") factory_id: Int,
        @RequestParam("model") model: String?,
        @RequestParam("year") year: Int,
        @RequestParam("fuel") fuel: String?,
        @RequestParam("doors") doors: Int,
        @RequestParam("cost") cost: Double,
        @RequestParam("color") color: String?
    ): List<Cars?>? {
        val car = Cars()
        return carService?.Add((Cars(id,factory_id,model,year,fuel,doors,cost,color)))
    }

    @PutMapping("/updateC/{id}")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    fun put(
        @PathVariable(value = "id") id: Int,
        @RequestParam("factoryId") factoryId: Int,
        @RequestParam("model") model: String?,
        @RequestParam("year") year: Int,
        @RequestParam("fuel") fuel: String?,
        @RequestParam("doors") doors: Int,
        @RequestParam("cost") cost: Double,
        @RequestParam("color") color: String?
    ): Cars {
        var car = Cars()
        if (carService != null) {
            car = carService.savebyId(id, factoryId, model, year, fuel, doors, cost, color)
        }
        return car
    }

    @DeleteMapping("/deleteC/{id}")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    fun deleteCar(@PathVariable(value = "id") id: Int): List<Cars?>? {
        return carService?.delete(id)
    }

    @PostMapping("/file")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    //@Throws(IOException::class, CsvException::class)
    fun file(@RequestParam("file") file: MultipartFile) {
        val fileReader = file.inputStream
        val fileReaderr = BufferedReader(InputStreamReader(fileReader, "UTF-8"))
        val csvReader = CSVReaderBuilder(fileReaderr)
            .build()

        val header = csvReader.readNext()

        var strings: Array<String>? = csvReader.readNext()
        while (strings != null) {
            // Do something with the data

            if (strings[0] == "id" == false) {
                    /*val car = Cars()
                    car.setId(Integer.valueOf(strings[0]))
                    car.setFactoryId(Integer.valueOf(strings[1]))
                    car.setModel(strings[2])
                    car.setYear(Integer.valueOf(strings[4]))
                    car.setFuel(strings[5])
                    car.setDoors(Integer.valueOf(strings[6]))
                    car.setCost(java.lang.Double.valueOf(strings[7]))
                    car.setColor(strings[8])*/
                carService?.Add(((Cars(Integer.valueOf(strings[0]),Integer.valueOf(strings[1]),strings[2],Integer.valueOf(strings[4]),
                    strings[5],Integer.valueOf(strings[6]),java.lang.Double.valueOf(strings[7]),strings[8]))))
                }
            }

        return
    }

}
