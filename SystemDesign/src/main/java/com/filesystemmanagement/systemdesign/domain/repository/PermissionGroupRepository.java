package com.filesystemmanagement.systemdesign.domain.repository;

import com.filesystemmanagement.systemdesign.domain.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
    Optional<PermissionGroup> findByGroupName(String groupName);
}