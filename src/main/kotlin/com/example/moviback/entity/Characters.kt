package com.example.moviback.entity

import jakarta.persistence.*

@Entity
@Table(name = "characters")
class Characters {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var namec: String? = null
    var scenes_count: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenes_id")
    val scene: Scenes? = null
}