package com.example.cinema.services;

import com.example.cinema.dtos.request.PermissionRequest;
import com.example.cinema.dtos.response.PermissonResponse;
import com.example.cinema.entities.Permission;
import com.example.cinema.mapper.PermissonMapper;
import com.example.cinema.repositories.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    @Autowired
    PermissionRepository repository;
    PermissonMapper mapper;
    @Autowired
    PermissionRepository permissionRepository;

    public PermissonResponse create (PermissionRequest request){
        Permission permission = mapper.toPermisson(request);
        permission = repository.save(permission);
        return mapper.toPermissonResponse(permission);
    }
    public List<PermissonResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(mapper::toPermissonResponse).toList();
    }
    public void delete(Integer permissson){
        permissionRepository.deleteById(permissson);
    }
}
