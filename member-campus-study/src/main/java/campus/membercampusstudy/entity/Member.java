package campus.membercampusstudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 회원 엔티티
 * <p>
 * JPA 테이블: {@code jpamember}
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@Entity
@Table(name = "jpamember")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpa_member_seq")
    @SequenceGenerator(name = "jpa_member_seq", sequenceName = "jpa_member_seq", allocationSize = 1)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, length = 20)
    private String phone;
    
    @Column(length = 10)
    private Integer age;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;
    
    public enum Gender {
        MALE, FEMALE, OTHER
    }
}