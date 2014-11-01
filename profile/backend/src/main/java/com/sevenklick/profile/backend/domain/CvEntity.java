package com.sevenklick.profile.backend.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by pierre.petersson on 25/10/2014.
 */
@Entity
@Table(name = "CV", schema = "public", catalog = "postgres")
public class CvEntity {
    private Long id;
    private String name;
    private byte[] content;
    private Timestamp lastUpdated;
    private Long viewsCounter;
    private Boolean visibility;
    private String contentType;
    private UserEntity userEntity;

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @SequenceGenerator(name = "profile_sequence", sequenceName = "profile_sequence", schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "content", nullable = true, insertable = true, updatable = true)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Basic
    @Column(name = "last_updated", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Basic
    @Column(name = "views_counter", nullable = true, insertable = true, updatable = true)
    public Long getViewsCounter() {
        return viewsCounter;
    }

    public void setViewsCounter(Long viewsCounter) {
        this.viewsCounter = viewsCounter;
    }

    @Basic
    @Column(name = "visibility", nullable = true, insertable = true, updatable = true)
    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    @Basic
    @Column(name = "content_type", nullable = true, insertable = true, updatable = true, length = 40)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CvEntity cvEntity = (CvEntity) o;

        if (!Arrays.equals(content, cvEntity.content)) return false;
        if (id != null ? !id.equals(cvEntity.id) : cvEntity.id != null) return false;
        if (lastUpdated != null ? !lastUpdated.equals(cvEntity.lastUpdated) : cvEntity.lastUpdated != null)
            return false;
        if (name != null ? !name.equals(cvEntity.name) : cvEntity.name != null) return false;
        if (viewsCounter != null ? !viewsCounter.equals(cvEntity.viewsCounter) : cvEntity.viewsCounter != null)
            return false;
        if (visibility != null ? !visibility.equals(cvEntity.visibility) : cvEntity.visibility != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? Arrays.hashCode(content) : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        result = 31 * result + (viewsCounter != null ? viewsCounter.hashCode() : 0);
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
        return result;
    }
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_REF", nullable = false)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
