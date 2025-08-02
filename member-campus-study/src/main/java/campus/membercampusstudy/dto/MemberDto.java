package campus.membercampusstudy.dto;

import campus.membercampusstudy.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberDto {
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private String email;
        private String name;
        private String phone;
        private Integer age;
        private Member.Gender gender;
        
        public Member toEntity() {
            return Member.builder()
                    .email(email)
                    .name(name)
                    .phone(phone)
                    .age(age)
                    .gender(gender)
                    .build();
        }
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private String phone;
        private Integer age;
        private Member.Gender gender;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        public static Response from(Member member) {
            return Response.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .age(member.getAge())
                    .gender(member.getGender())
                    .createdAt(member.getCreatedAt())
                    .updatedAt(member.getUpdatedAt())
                    .build();
        }
    }
}