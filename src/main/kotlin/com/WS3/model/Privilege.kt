package com.WS3.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Privilege {
    @Id
    @GeneratedValue
    var privilegeId: Long = 0
    var name: String? = null
    var description: String? = null
}