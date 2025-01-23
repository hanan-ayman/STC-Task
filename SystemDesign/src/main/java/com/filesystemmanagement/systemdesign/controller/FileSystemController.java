package com.filesystemmanagement.systemdesign.controller;


import com.filesystemmanagement.systemdesign.domain.dto.FileDTO;
import com.filesystemmanagement.systemdesign.domain.dto.FolderDTO;
import com.filesystemmanagement.systemdesign.domain.dto.SpaceDTO;
import com.filesystemmanagement.systemdesign.service.FileService;
import com.filesystemmanagement.systemdesign.service.FolderService;
import com.filesystemmanagement.systemdesign.service.SpaceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/public/api/v1")
public class FileSystemController {

    private final SpaceService spaceService;
    private final FolderService folderService;
    private final FileService fileService;
    public FileSystemController(SpaceService spaceService, FolderService folderService, FileService fileService) {
        this.spaceService = spaceService;
        this.folderService = folderService;
        this.fileService = fileService;
    }

    @PostMapping("/spaces")
    public ResponseEntity<?> createSpace(@Valid @RequestBody SpaceDTO spaceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceService.createSpaceWithPermissions(spaceDTO));
    }

    @PostMapping("/spaces/{spaceName}/folders")
    public ResponseEntity<?> createFolder(@PathVariable String spaceName, @Valid @RequestBody FolderDTO folderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(folderService.createFolderUnderSpace(spaceName, folderDTO));
    }

    @PostMapping("/folder/{folderName}/files")
    public ResponseEntity<?> createFile(@PathVariable String folderName,  @Valid @RequestBody FileDTO fileDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.createFileUnderFolder(folderName , fileDTO));
    }
}
