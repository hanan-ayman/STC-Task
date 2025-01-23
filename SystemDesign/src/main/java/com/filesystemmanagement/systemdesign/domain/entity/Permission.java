package com.filesystemmanagement.systemdesign.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "user_email")
    private String userEmail;

    @Enumerated(EnumType.STRING)
    @Column( name = "permission_level")
    private PermissionLevel permissionLevel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup permissionGroup;

    public Permission() {
    }

    public Permission(Long id, String userEmail, PermissionLevel permissionLevel, PermissionGroup permissionGroup) {
        this.id = id;
        this.userEmail = userEmail;
        this.permissionLevel = permissionLevel;
        this.permissionGroup = permissionGroup;
    }


}
