package com.WS3.model

import java.util.*
import javax.persistence.*

@Entity
class Role {
    @Id
    @GeneratedValue
    var roleId: Long? = null
    var name: String? = null
    var description: String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    var privileges: Set<Privilege> = HashSet(0)
}