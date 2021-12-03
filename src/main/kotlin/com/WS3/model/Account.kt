package com.WS3.model

import javax.persistence.*

@Entity
class Account {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(unique = true)
    var username: String? = null
    var password: String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    var roles: Set<Role>? = null
    var isEnabled = true
}