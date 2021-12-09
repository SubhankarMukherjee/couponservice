package com.connecttosubh.web;

import com.connecttosubh.model.Role;
import com.connecttosubh.model.User;
import com.connecttosubh.repository.RoleRepo;
import com.connecttosubh.repository.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AssignRoleController {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @GetMapping("/showAssignRole")
    public String showAssignRoleForm()
    {
        return "roleAssignment";
    }
    @PostMapping("/assignRole")
    public String assignRoleToUser(String username, String role)
    {
        // Database Role sould be ROLE_ADMIN, ROLE_USER, but web Secuirity it should be only ADMIN, USER[
        User user = userRepo.findByEmail(username);

        System.out.println("User:"+ user.toString());
            Role role1 = roleRepo.findByName(role);
        System.out.println("Role:"+ role.toString());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role1);
        user.setRoles(roleSet);
          userRepo.save(user);
            return "SuccessAssign";
    }

}
