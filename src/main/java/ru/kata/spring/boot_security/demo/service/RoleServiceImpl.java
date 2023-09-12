package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRep;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRep roleRep;

    public RoleServiceImpl(RoleRep roleRep) {
        this.roleRep = roleRep;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRep.findAll();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRep.save(role);
    }

    @Override
    public Role getRoleById(int id) {
        Optional<Role> roleById = roleRep.findById(id);
        return roleById.orElse(null);
    }
}
