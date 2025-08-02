package campus.membercampusstudy.mapper.adapter;

import campus.membercampusstudy.entity.Member;
import campus.membercampusstudy.mapper.IMemberMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * MyBatis 매퍼를 공통 인터페이스로 래핑하는 어댑터 클래스
 * <p>
 * IMemberMapper의 MyBatis 전용 메서드들을 IMember 인터페이스 메서드로 변환합니다.
 * 이를 통해 JPA Repository와 동일한 인터페이스를 제공합니다.
 */
@Component
public class MemberMapperAdapter {
    
    /**
     * MyBatis 전용 메서드를 IMember 인터페이스 메서드로 어댑팅
     */
    public static class AdapterMethods {
        
        /**
         * save() 메서드 구현
         * - 새 객체인 경우 insert 수행
         * - 기존 객체인 경우 update 수행
         */
        public static Member save(IMemberMapper mapper, Member member) {
            if (member.getId() == null) {
                mapper.insertMember(member);
                return member;
            } else {
                mapper.updateMember(member);
                return member;
            }
        }
        
        /**
         * findById() 메서드 구현
         * MyBatis 전용 메서드 결과를 Optional로 래핑
         */
        public static Optional<Member> findById(IMemberMapper mapper, Long id) {
            Member member = mapper.findMemberById(id);
            return Optional.ofNullable(member);
        }
        
        /**
         * findByEmail() 메서드 구현
         * MyBatis 전용 메서드 결과를 Optional로 래핑
         */
        public static Optional<Member> findByEmail(IMemberMapper mapper, String email) {
            Member member = mapper.findMemberByEmail(email);
            return Optional.ofNullable(member);
        }
        
        /**
         * existsByEmail() 메서드 구현
         * countByEmail 결과를 boolean으로 변환
         */
        public static boolean existsByEmail(IMemberMapper mapper, String email) {
            return mapper.countByEmail(email) > 0;
        }
        
        /**
         * findAll() 메서드 구현
         * MyBatis 전용 메서드를 그대로 사용
         */
        public static List<Member> findAll(IMemberMapper mapper) {
            return mapper.findAllMembers();
        }
        
        /**
         * deleteById() 메서드 구현
         * MyBatis 전용 삭제 메서드 사용
         */
        public static void deleteById(IMemberMapper mapper, Long id) {
            mapper.deleteMember(id);
        }
        
        /**
         * findByNameContaining() 메서드 구현
         * MyBatis 전용 메서드를 그대로 사용
         */
        public static List<Member> findByNameContaining(IMemberMapper mapper, String name) {
            return mapper.findByNameContaining(name);
        }
        
        /**
         * findByAgeBetween() 메서드 구현
         * MyBatis 전용 메서드를 그대로 사용
         */
        public static List<Member> findByAgeBetween(IMemberMapper mapper, Integer minAge, Integer maxAge) {
            return mapper.findByAgeBetween(minAge, maxAge);
        }
        
        /**
         * findByGender() 메서드 구현
         * Enum을 String으로 변환하여 MyBatis 메서드 호출
         */
        public static List<Member> findByGender(IMemberMapper mapper, Member.Gender gender) {
            return mapper.findByGender(gender.name());
        }
    }
}