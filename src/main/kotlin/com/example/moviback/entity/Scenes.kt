package com.example.moviback.entity

import jakarta.persistence.*

@Entity
@Table(name = "scenes")
class Scenes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? =null
    var titles: String?=null
    var duration: Int? =null

    @ManyToOne (fetch = FetchType.LAZY)
    val films: Films? =null

    @OneToMany(mappedBy = "scene")
    val characters: List<Characters> = mutableListOf()
}