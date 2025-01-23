package com.filesystemmanagement.systemdesign.domain.dto;

import com.filesystemmanagement.systemdesign.domain.entity.PermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPermissionDTO {
    private String userEmail;
    private PermissionLevel permissionLevel;
}
