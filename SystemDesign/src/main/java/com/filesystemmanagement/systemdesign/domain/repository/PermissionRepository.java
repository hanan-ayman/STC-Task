package com.filesystemmanagement.systemdesign.domain.repository;

import com.filesystemmanagement.systemdesign.domain.entity.Permission;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByUserEmailAndPermissionGroup(String userEmail, PermissionGroup permissionGroup);
}