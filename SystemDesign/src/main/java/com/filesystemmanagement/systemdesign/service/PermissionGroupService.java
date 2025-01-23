package com.filesystemmanagement.systemdesign.service;

import com.filesystemmanagement.systemdesign.domain.dto.SpaceDTO;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionGroup;
import com.filesystemmanagement.systemdesign.domain.repository.PermissionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionGroupService {

    private final PermissionGroupRepository permissionGroupRepository;

    public PermissionGroupService(PermissionGroupRepository permissionGroupRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
    }

    public PermissionGroup getOrCreateThePermissionGroup (String permissionGroupName) {
        return permissionGroupRepository
                .findByGroupName(permissionGroupName)
                .orElseGet(() -> {
                    PermissionGroup permissionGroup = new PermissionGroup();
                    permissionGroup.setGroupName(permissionGroupName);
                    permissionGroupRepository.save(permissionGroup);
                    return permissionGroup;
                });
    }
}
