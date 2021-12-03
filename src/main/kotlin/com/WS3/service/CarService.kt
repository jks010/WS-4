package com.WS3.service

import com.WS3.model.Cars
import com.WS3.repository.CarRep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class CarService {
    @Autowired
    private val carRep: CarRep? = null
    fun findAll(): List<Cars?> {
        return carRep!!.findAll() as List<Cars?>
    }

    fun findbyId(id: Int): Cars {
        return carRep!!.findById(id)
            .orElseThrow { RuntimeException() }!!
    }

    fun savebyId(
        id: Int,
        factoryId: Int,
        model: String?,
        year: Int,
        fuel: String?,
        doors: Int,
        cost: Double,
        color: String?
    ): Cars {
        val car = carRep!!.findById(id)
            .orElseThrow { RuntimeException() }!!
        //(id,factoryId,model,year,fuel,doors,cost,color)
        /*
        car.setFactoryId(factoryId)
        car.setModel(model)
        car.setYear(year)
        car.setFuel(fuel)
        car.setDoors(doors)
        car.setCost(cost)
        car.setColor(color)
        */
        carRep.save(Cars(id,factoryId,model,year,fuel,doors,cost,color))

        return car
    }

    fun Add(car: Cars): List<Cars?> {
        // TODO Auto-generated method stub
        carRep?.save(car)
        return carRep?.findAll() as List<Cars?>
    }

    fun delete(id: Int): List<Cars?> {
        val car: Cars? = carRep?.findById(id)
            ?.orElseThrow(Supplier { RuntimeException() })
        carRep?.deleteById(id)
        return carRep?.findAll() as List<Cars?>
    }
}