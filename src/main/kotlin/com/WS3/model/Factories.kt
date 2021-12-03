package com.WS3.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "factories")
class Factories {
    @Id
    var id = 0
    var name: String? = null
    var countryCode = 0

    constructor() {}
    constructor(id: Int, name: String?, country_code: Int) {
        this.id = id
        this.name = name
        countryCode = country_code
    }
}