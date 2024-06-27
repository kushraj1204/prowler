package com.kush.prowler.config;

import com.kush.prowler.model.entity.SystemAction;
import com.kush.prowler.model.entity.SystemRole;
import com.kush.prowler.model.entity.SystemUser;
import com.kush.prowler.repository.SystemActionRepository;
import com.kush.prowler.repository.SystemRoleRepository;
import com.kush.prowler.repository.SystemUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kush
 */
//@Component
public class Laufe implements CommandLineRunner {
    SystemActionRepository systemActionRepository;
    SystemRoleRepository systemRoleRepository;
    SystemUserRepository systemUserRepository;

    public Laufe(SystemActionRepository systemActionRepository, SystemRoleRepository systemRoleRepository, SystemUserRepository systemUserRepository) {
        this.systemActionRepository = systemActionRepository;
        this.systemRoleRepository = systemRoleRepository;
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SystemAction action1=new SystemAction("Create System User","create_user","Create System User");
        SystemAction action2=new SystemAction("Read System User","view_user","Read System User");
        SystemAction action3=new SystemAction("Update System User","modify_user","Update System User");
        SystemAction action4=new SystemAction("Delete System User","delete_user","Delete System User");
        SystemAction action5=new SystemAction("Read System User List","view_user_list","Read System User List");

        SystemAction action11 = new SystemAction("Create System Role", "create_role", "Create new system roles");
        SystemAction action12 = new SystemAction("Read System Role", "view_role", "View existing system roles");
        SystemAction action13 = new SystemAction("Update System Role", "modify_role", "Modify system role details");
        SystemAction action14 = new SystemAction("Delete System Role", "delete_role", "Delete system roles");
        SystemAction action15=new SystemAction("Read System Action List","view_role_list","Read System role List");

        SystemAction action6 = new SystemAction("Create System Action", "create_action", "Create new system actions");
        SystemAction action7 = new SystemAction("Read System Action", "view_action", "View existing system actions");
        SystemAction action8 = new SystemAction("Update System Action", "modify_action", "Modify system action details");
        SystemAction action9 = new SystemAction("Delete System Action", "delete_action", "Delete system actions");
        SystemAction action10=new SystemAction("Read System Action List","view_action_list","Read System action List");


        systemActionRepository.saveAll(List.of(action1,action2,action3,action4,action5 ,action6,action7,action8,action9,action10 ,action11,action12,action13,action14,action15));
        Set<SystemAction> systemActionSet=new HashSet<SystemAction>(systemActionRepository.findAll());
        SystemRole role=new SystemRole("SuperUser","superuser","SuperUser",systemActionSet.stream().map(SystemAction::getCode).collect(Collectors.toSet()));
        systemRoleRepository.save(role);
      Optional<SystemRole> roleOpt=systemRoleRepository.findByCodeIgnoreCase("superuser");
      if(roleOpt.isPresent()){
          Set<SystemRole> roles=new HashSet<>();
          roles.add(roleOpt.get());
          // Create an encoder with strength 16
          BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
          String password = encoder.encode("kush");
          SystemUser user=new SystemUser("kush",password,"kushraj1204@gmail.com");
          user.setRoles(roles);
          systemUserRepository.save(user);
      }
    }
}
