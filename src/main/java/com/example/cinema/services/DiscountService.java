package com.example.cinema.services;

import com.example.cinema.dtos.response.DiscountDTO;
import com.example.cinema.dtos.response.DiscountStatisticsDTO;
import com.example.cinema.entities.Discount;
import com.example.cinema.repositories.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

//    public Discount getDiscountById(Integer id) {
//        return discountRepository.findById(id);
//    }

    public Discount addDiscount(DiscountDTO discountDTO) {
        Discount discount = new Discount();
        discount.setCode(discountDTO.getCode());
        discount.setPercentage(discountDTO.getPercentage());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());
        discount.setActive(discountDTO.isActive());
        return discountRepository.save(discount);
    }

    public Discount updateDiscount(Integer id, DiscountDTO discountDTO) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));
        discount.setCode(discountDTO.getCode());
        discount.setPercentage(discountDTO.getPercentage());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());
        discount.setActive(discountDTO.isActive());
        return discountRepository.save(discount);
    }

    public void deleteDiscount(Integer id) {
        discountRepository.deleteById(id);
    }

    public DiscountStatisticsDTO getDiscountStatistics() {
        long totalDiscounts = discountRepository.count();
        double averageDiscount = discountRepository.findAverageDiscount();
        double maxDiscount = discountRepository.findMaxDiscount();
        double minDiscount = discountRepository.findMinDiscount();

        return DiscountStatisticsDTO.builder()
                .averageDiscount(averageDiscount)
                .totalDiscounts(totalDiscounts)
                .maxDiscount(maxDiscount)
                .minDiscount(minDiscount)
                .build();
    }
}
