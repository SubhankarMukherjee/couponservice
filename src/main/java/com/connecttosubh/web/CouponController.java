package com.connecttosubh.web;

import com.connecttosubh.model.Coupon;
import com.connecttosubh.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@CrossOrigin
public class CouponController {

    @Autowired
    CouponRepo couponRepo;


    //@PreAuthorize("hasRole('ADMIN')")-->Method level Security
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
    //@PostAuthorize("returnObject.disCount<60")
    public ModelAndView getCoupon(String code)
    {
        ModelAndView coupon = new ModelAndView("couponDetails");
        coupon.addObject(couponRepo.findByCode(code));
        return coupon;

    }
}
