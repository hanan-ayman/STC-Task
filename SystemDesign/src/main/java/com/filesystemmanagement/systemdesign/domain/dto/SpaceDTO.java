package com.filesystemmanagement.systemdesign.domain.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpaceDTO {
    @NotNull
    private String name;

    @NotNull
    private String permissionGroupName;

    @Nullable
    private List<UserPermissionDTO> userPermissions;

    public List<UserPermissionDTO> getUserPermissions() {
        return userPermissions != null ? userPermissions : Collections.emptyList();
    }
}
