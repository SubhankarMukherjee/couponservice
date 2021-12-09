package com.connecttosubh.web;

import com.connecttosubh.model.Coupon;
import com.connecttosubh.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CouponController {

    @Autowired
    CouponRepo couponRepo;


    @GetMapping("/showCreateCoupon")
    public String showCreateCoupon()
    {
        return "createCoupon";
    }
    @PostMapping("/saveCoupon")
    public String saveCoupon(Coupon coupon)
    {
        couponRepo.save(coupon);
        return "createResponse";
    }
    @GetMapping("/showGetCoupon")
    public String getShowCoupon()
    {
        return "getCoupon";
    }
    @PostMapping("/getCoupon")
    public ModelAndView getCoupon(String code)
    {
        ModelAndView coupon = new ModelAndView("couponDetails");
        coupon.addObject(couponRepo.findByCode(code));
        return coupon;

    }
}
