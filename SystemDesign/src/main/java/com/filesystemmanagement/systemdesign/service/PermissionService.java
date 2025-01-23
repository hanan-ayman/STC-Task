package com.filesystemmanagement.systemdesign.service;

import com.filesystemmanagement.systemdesign.domain.dto.UserPermissionDTO;
import com.filesystemmanagement.systemdesign.domain.entity.Permission;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionGroup;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionLevel;
import com.filesystemmanagement.systemdesign.domain.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void addTheNewUsersWithNewPermissionsOrUpdateTheirPermissions(List<UserPermissionDTO> userPermissions, PermissionGroup permissionGroup) {
        for (UserPermissionDTO userPermissionDTO : userPermissions) {
            Optional<Permission> existingPermission = permissionRepository.findByUserEmailAndPermissionGroup(userPermissionDTO.getUserEmail(), permissionGroup);

            Permission permission;
            if (existingPermission.isPresent()) {
                permission = existingPermission.get();
            } else {
                // If the user does not exist, create a new permission entry
                permission = new Permission();
                permission.setUserEmail(userPermissionDTO.getUserEmail());
                permission.setPermissionGroup(permissionGroup);
            }
            permission.setPermissionLevel(userPermissionDTO.getPermissionLevel());
            permissionRepository.save(permission);
        }
    }

    public PermissionLevel getUserPermissionLevel(String userEmail , PermissionGroup permissionGroup) {
        Optional<Permission> byUserEmailAndPermissionGroup = permissionRepository.findByUserEmailAndPermissionGroup(userEmail, permissionGroup);
        return byUserEmailAndPermissionGroup.map(Permission::getPermissionLevel).orElse(null);
    }

}
