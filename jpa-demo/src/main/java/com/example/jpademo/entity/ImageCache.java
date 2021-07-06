package com.example.jpademo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.jpademo.entity.base.AbstractAuditModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "image_cache")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ImageCache extends AbstractAuditModel{
    private static final long serialVersionUID = 5159980051948289074L;

    @Column(name = "image_id")
    private String imageId;

    private String name;

    private String tag;

    private Integer size;

    @Column(name = "registry_address")
    private String registryAddress;

    @Column(name = "registry_name")
    private String registryName;

    private String config;
}
