package com.example.cinema.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@Table(name = "branch")
@Entity
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 2000)
    private String imgURL;
    private String name;
    private String diaChi;
    private String phoneNo;
    @OneToMany(mappedBy = "branch",fetch = FetchType.LAZY)
//    stop
    @JsonBackReference
    private List<Schedule> scheduleList;
}
