package com.example.cinema.services;

import com.example.cinema.dtos.request.RoleRequest;
import com.example.cinema.dtos.response.PermissonResponse;
import com.example.cinema.dtos.response.RoleResponse;
import com.example.cinema.mapper.RoleMapper;
import com.example.cinema.repositories.PermissionRepository;
import com.example.cinema.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    @Autowired
    RoleRepository repository;
    @Autowired
    PermissionRepository permissionRepository;
    RoleMapper mapper;
    private final RoleRepository roleRepository;

    public RoleResponse create(RoleRequest request){
        var role = mapper.toRole(request);

        var permissons = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissons));

        role=roleRepository.save(role);
        return mapper.toRoleResponse(role);
    }
    public List<RoleResponse> getAll(){
        var role = roleRepository.findAll();
        return role.stream().map(mapper::toRoleResponse).toList();
    }
    public void delete(Integer role){
        roleRepository.deleteById(role);
    }
}
