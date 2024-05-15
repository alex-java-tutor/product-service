package com.example.productservice.storage.model;

import com.example.productservice.util.DateUtil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private LocalDateTime updatedAt;

    /**
     * Переопределяем equals и hashCode
     * https://jpa-buddy.com/blog/hopefully-the-final-article-about-equals-and-hashcode-for-jpa-entities-with-db-generated-ids/
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getEffectiveClass(this) != getEffectiveClass(o)) return false;
        Product p = (Product) o;
        return getId() != null && Objects.equals(getId(), p.getId());
    }

    @Override
    public final int hashCode() {
        return getEffectiveClass(this).hashCode();
    }

    private static Class<?> getEffectiveClass(Object o) {
        return o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    }
}
