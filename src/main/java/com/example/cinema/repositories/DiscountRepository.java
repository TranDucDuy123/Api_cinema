package com.example.cinema.repositories;

import com.example.cinema.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    @Query("SELECT AVG(d.percentage) FROM Discount d")
    double findAverageDiscount();

    @Query("SELECT MAX(d.percentage) FROM Discount d")
    double findMaxDiscount();

    @Query("SELECT MIN(d.percentage) FROM Discount d")
    double findMinDiscount();

    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần

}
