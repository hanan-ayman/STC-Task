package com.filesystemmanagement.systemdesign.domain.repository;

import com.filesystemmanagement.systemdesign.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
