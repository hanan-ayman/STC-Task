package com.filesystemmanagement.systemdesign.service;

import com.filesystemmanagement.systemdesign.domain.dto.FileDTO;
import com.filesystemmanagement.systemdesign.domain.entity.*;
import com.filesystemmanagement.systemdesign.domain.repository.FileRepository;
import com.filesystemmanagement.systemdesign.domain.repository.ItemRepository;
import com.filesystemmanagement.systemdesign.exception.FileNotSavedException;
import com.filesystemmanagement.systemdesign.exception.PermissionDeniedException;
import com.filesystemmanagement.systemdesign.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class FileService {

    private final ItemRepository itemRepository;
    private final PermissionService permissionService;
    private final FileRepository fileRepository;

    public FileService(ItemRepository itemRepository, PermissionService permissionService, FileRepository fileRepository) {
        this.itemRepository = itemRepository;
        this.permissionService = permissionService;
        this.fileRepository = fileRepository;
    }

    public File createFileUnderFolder(String spaceName, FileDTO fileDTO){
        Optional<PermissionGroup> permissionGroupOptional = itemRepository.findPermissionGroupByName(spaceName); // It Could be Name and Type

        if (permissionGroupOptional.isEmpty()) {
            throw new ResourceNotFoundException("Folder with this name is not found");
        }

        PermissionGroup permissionGroup = permissionGroupOptional.get();

        PermissionLevel userPermissionLevel = permissionService.getUserPermissionLevel(fileDTO.getUserEmail(), permissionGroup);

        if (userPermissionLevel == null || userPermissionLevel.equals(PermissionLevel.VIEW)) {
            throw new PermissionDeniedException("User has VIEW access, cannot create folder");
        }
        // Save File name in Item Table
        Item item = itemRepository.save(new Item(ItemType.FILE, fileDTO.getFileName(), permissionGroup));

        // Save File Data
        try {
            File newFile = new File();
            newFile.setItem(item);
            newFile.setMetaDataBinary(new byte[]{}); //TODO : Need to be handled from Controller Multipart type

            return fileRepository.save(newFile);
        } catch (Exception e) {
            throw new FileNotSavedException("Error saving file", e);
        }
    }
}
