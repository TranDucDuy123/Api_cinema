package com.example.cinema.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255, nullable = true)
    private String name;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @Column(name = "is_active", columnDefinition = "BIT(1)", nullable = false)
    private boolean isActive;

    @Column(name = "is_vip", columnDefinition = "BIT(1)", nullable = false)
    private boolean isVip;

    // Các cột khác có thể thêm vào tương tự
}
