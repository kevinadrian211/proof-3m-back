package com.example.moviback.entity

import jakarta.persistence.*

@Entity
@Table(name = "films")
class Films {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long?=null
    var titlef: String?=null
    var duration: Int?=null
    var description: String?=null

}