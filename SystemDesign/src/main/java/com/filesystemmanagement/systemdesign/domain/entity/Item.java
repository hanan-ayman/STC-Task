package com.filesystemmanagement.systemdesign.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType type; // Enum for Space, Folder, File

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;// One Group can be assigned to many items

    public Item(){

    }
    public Item(ItemType type, String name, PermissionGroup permissionGroup) {
        this.type = type;
        this.name = name;
        this.permissionGroup = permissionGroup;
    }

}
