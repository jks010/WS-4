package com.WS3.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "cars")
class Cars {

    @Id
    private var id = 0
    private var factoryId = 0
    private var model: String? = null
    private var year = 0
    private var fuel: String? = null
    private var doors = 0
    private var cost = 0.0
    private var color: String? = null

    constructor() {}
    constructor(
        id: Int, factory_id: Int, model: String?, year: Int,
        fuel: String?, doors: Int, cost: Double, color: String?
    ) {
        this.id = id
        factoryId = factory_id
        this.model = model
        this.year = year
        this.fuel = fuel
        this.doors = doors
        this.cost = cost
        this.color = color
    }
}