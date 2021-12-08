package com.connecttosubh.web;

import com.connecttosubh.model.Coupon;
import com.connecttosubh.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    CouponRepo repo;

    @PostMapping("/coupons")
    public Coupon saveCoupon(@RequestBody Coupon coupon)
    {
        return repo.save(coupon);
    }

    @GetMapping("/coupons/{code}")
    public Coupon getCoupon(@PathVariable String code)
    {
        return repo.findByCode(code);
    }
}
