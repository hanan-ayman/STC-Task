package com.filesystemmanagement.systemdesign.service;

import com.filesystemmanagement.systemdesign.domain.dto.FolderDTO;
import com.filesystemmanagement.systemdesign.domain.entity.Item;
import com.filesystemmanagement.systemdesign.domain.entity.ItemType;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionGroup;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionLevel;
import com.filesystemmanagement.systemdesign.domain.repository.ItemRepository;
import com.filesystemmanagement.systemdesign.exception.PermissionDeniedException;
import com.filesystemmanagement.systemdesign.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FolderService {

    private final ItemRepository itemRepository;
    private final PermissionService permissionService;

    public FolderService(ItemRepository itemRepository, PermissionService permissionService) {
        this.itemRepository = itemRepository;
        this.permissionService = permissionService;
    }

    public Item createFolderUnderSpace(String spaceName, FolderDTO folderDTO) {
        Optional<PermissionGroup> permissionGroupOptional = itemRepository.findPermissionGroupByName(spaceName);

        if (permissionGroupOptional.isEmpty()) {
            throw new ResourceNotFoundException("Space with this name is not found");
        }

        PermissionGroup permissionGroup = permissionGroupOptional.get();

        PermissionLevel userPermissionLevel = permissionService.getUserPermissionLevel(folderDTO.getUserEmail(), permissionGroup);

        if (userPermissionLevel == null || userPermissionLevel.equals(PermissionLevel.VIEW)) {
            throw new PermissionDeniedException("User has VIEW access, cannot create folder");
        }

        Item newFolder = new Item(ItemType.FOLDER, folderDTO.getFolderName(), permissionGroup);
        return itemRepository.save(newFolder);
    }

}
