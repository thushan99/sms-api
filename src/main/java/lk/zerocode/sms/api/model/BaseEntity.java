package lk.zerocode.sms.api.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
//import jakarta.persistence.Transient;
//import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    protected LocalDateTime createdAt;
    @CreatedBy
    protected String createdBy;
//    protected String deletedAt;
//    protected String deletedBy;
    @LastModifiedDate
    protected LocalDateTime modifiedAt;
    @LastModifiedBy
    protected String modifiedBy;
//    @Version
//    protected Long version;

//    @Transient
//    public boolean isNew() {
//        return version == null;
//    }
}