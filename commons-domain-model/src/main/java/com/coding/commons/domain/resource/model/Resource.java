package com.coding.commons.domain.resource.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coding.commons.base.data.jpa.JpaBaseEntity;
import com.coding.commons.util.CollectionUtils;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
public class Resource extends JpaBaseEntity<Long> {
    public static final String GRANTED_URL_PREFIX = "Granted:";

    private Integer type;

    private String icon;

    private String displayName;

    private String url;

    private Long parentId;

    /**
     * 权限依赖的其他资源.
     */
    @Type(type = "jsonb")
    private List<Long> depends;

    @Transient
    private Boolean selected;

    @Transient
    private transient List<Resource> subs;

    public boolean isLeaf() {
        return CollectionUtils.isEmpty(subs);
    }
}
