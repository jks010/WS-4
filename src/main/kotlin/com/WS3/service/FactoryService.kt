package com.WS3.service

import com.WS3.model.Factories
import com.WS3.repository.FactoryRep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class FactoryService {
    @Autowired
    private val factoryRep: FactoryRep? = null
    fun findAll(): List<Factories?> {
        return factoryRep!!.findAll() as List<Factories?>
    }

    fun findbyId(id: Int): Factories {
        return factoryRep!!.findById(id)
            .orElseThrow { RuntimeException() }!!
    }

    fun savebyId(id: Int, name: String?, countryCode: Int): Factories? {
        val factory: Factories? = factoryRep?.findById(id)
            ?.orElseThrow(Supplier { RuntimeException() })

        factoryRep?.save(Factories(id,name,countryCode))
        return factory
    }

    fun Add(factory: Factories): List<Factories?> {
        // TODO Auto-generated method stub

        factoryRep?.save(factory)

            return factoryRep?.findAll() as List<Factories?>

    }

    fun delete(id: Int): List<Factories?> {
        val factory: Factories? = factoryRep?.findById(id)
            ?.orElseThrow(Supplier { RuntimeException() })

        factoryRep?.deleteById(id)


            return factoryRep?.findAll() as List<Factories?>

    }
}