package com.filesystemmanagement.systemdesign.service;

import com.filesystemmanagement.systemdesign.domain.dto.SpaceDTO;
import com.filesystemmanagement.systemdesign.domain.entity.Item;
import com.filesystemmanagement.systemdesign.domain.entity.ItemType;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionGroup;
import com.filesystemmanagement.systemdesign.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;


@Service
public class SpaceService {

    private final ItemRepository itemRepository;

    private final PermissionGroupService permissionGroupService;

    private final PermissionService permissionService;

    public SpaceService(ItemRepository itemRepository, PermissionGroupService permissionGroupService, PermissionService permissionService) {
        this.itemRepository = itemRepository;
        this.permissionGroupService = permissionGroupService;
        this.permissionService = permissionService;
    }

    public Item createSpaceWithPermissions (SpaceDTO spaceDTO) {
        PermissionGroup permissionGroup = permissionGroupService.getOrCreateThePermissionGroup(spaceDTO.getPermissionGroupName());
        if (!spaceDTO.getUserPermissions().isEmpty()) {
            permissionService.addTheNewUsersWithNewPermissionsOrUpdateTheirPermissions(spaceDTO.getUserPermissions(), permissionGroup);
        }
        return itemRepository.save(new Item(ItemType.SPACE , spaceDTO.getName(), permissionGroup));
    }
}
