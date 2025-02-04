package com.example.cinema.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class ResetPasswordToken {
    private static final int EXPIRATION_MINUTE = 10;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public ResetPasswordToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate=new Date(Calendar.getInstance().getTime().getTime()+ EXPIRATION_MINUTE*60*1000);
    }
}
