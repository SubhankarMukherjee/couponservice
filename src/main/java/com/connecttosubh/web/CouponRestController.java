package com.connecttosubh.web;

import com.connecttosubh.model.Coupon;
import com.connecttosubh.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
@CrossOrigin  // support all HTTP URL for cross origin
public class CouponRestController {

    @Autowired
    CouponRepo repo;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/coupons")
    public Coupon saveCoupon(@RequestBody Coupon coupon)
    {
        return repo.save(coupon);
    }

    @GetMapping("/coupons/{code}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Coupon getCoupon(@PathVariable String code)
    {
        return repo.findByCode(code);
    }
}
