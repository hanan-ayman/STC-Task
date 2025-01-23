package com.filesystemmanagement.systemdesign.domain.repository;


import com.filesystemmanagement.systemdesign.domain.entity.Item;
import com.filesystemmanagement.systemdesign.domain.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);

    @Query("SELECT i.permissionGroup FROM Item i WHERE i.name = :spaceName")
    Optional<PermissionGroup> findPermissionGroupByName(String spaceName);
}