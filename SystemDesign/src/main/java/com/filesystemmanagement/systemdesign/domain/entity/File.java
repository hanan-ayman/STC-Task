package com.filesystemmanagement.systemdesign.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] metaDataBinary;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public File() {
    }

    public File( byte[] metaDataBinary, Item item) {
        this.metaDataBinary = metaDataBinary;
        this.item = item;
    }
}
