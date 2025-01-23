package com.filesystemmanagement.systemdesign.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FolderDTO {
    @NotNull
    private String folderName;
    @NotNull
    private String userEmail;
}
