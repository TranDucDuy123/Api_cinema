package com.example.cinema.controllers;

import com.example.cinema.dtos.request.ApiResponse;
import com.example.cinema.dtos.response.DiscountDTO;
import com.example.cinema.entities.Discount;
import com.example.cinema.services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("")
    public ApiResponse<List<Discount>> getAllDiscounts() {
        return ApiResponse.<List<Discount>>builder()
                .result(
                        (discountService.getAllDiscounts())
                )
                .build();
    }

    @PostMapping("")
    public ApiResponse<Discount> addDiscount(@RequestBody DiscountDTO discount) {
        return ApiResponse.<Discount>builder()
                .result(discountService.addDiscount(discount))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<Discount> updateDiscount(@PathVariable int id, @RequestBody DiscountDTO discount) {
        return ApiResponse.<Discount>builder()
                .result(discountService.updateDiscount(id, discount))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDiscount(@PathVariable int id) {
        discountService.deleteDiscount(id);
        return ApiResponse.<Void>builder().build();
    }
}
