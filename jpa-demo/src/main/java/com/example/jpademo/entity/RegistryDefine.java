package com.example.jpademo.entity;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.example.jpademo.entity.base.AbstractAuditModel;

@Entity
@Table(name = "registry_define")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RegistryDefine extends AbstractAuditModel{
    private static final long serialVersionUID = 1103324803467241615L;

    private String name;

    private String host;

    private Integer port;

    private String user;

    private String password;

    @Column(name = "harbor_product_name")
    private String harborProductName;

    @Column(name = "harbor_product_id")
    private Integer harborProductId;

    @Enumerated(EnumType.STRING)
    private RegistryType type;

    private String scheme;

    private Integer health;
}
