package campus.membercampusstudy.mapper.adapter;

import campus.membercampusstudy.entity.MemberProfile;
import campus.membercampusstudy.mapper.IMemberProfileMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * MyBatis 프로필 매퍼를 공통 인터페이스로 래핑하는 어댑터 클래스
 * <p>
 * IMemberProfileMapper의 MyBatis 전용 메서드들을 IMemberProfile 인터페이스 메서드로 변환합니다.
 * 이를 통해 JPA Repository와 동일한 인터페이스를 제공합니다.
 */
@Component
public class MemberProfileMapperAdapter {
    
    /**
     * MyBatis 전용 메서드를 IMemberProfile 인터페이스 메서드로 어댑팅
     */
    public static class AdapterMethods {
        
        /**
         * save() 메서드 구현
         * - 새 객체인 경우 insert 수행
         * - 기존 객체인 경우 update 수행
         */
        public static MemberProfile save(IMemberProfileMapper mapper, MemberProfile profile) {
            if (profile.getId() == null) {
                mapper.insertProfile(profile);
                return profile;
            } else {
                mapper.updateProfile(profile);
                return profile;
            }
        }
        
        /**
         * findById() 메서드 구현
         * MyBatis 전용 메서드 결과를 Optional로 래핑
         */
        public static Optional<MemberProfile> findById(IMemberProfileMapper mapper, Long id) {
            MemberProfile profile = mapper.findProfileById(id);
            return Optional.ofNullable(profile);
        }
        
        /**
         * findByMemberId() 메서드 구현
         * MyBatis 전용 메서드 결과를 Optional로 래핑
         */
        public static Optional<MemberProfile> findByMemberId(IMemberProfileMapper mapper, Long memberId) {
            MemberProfile profile = mapper.findProfileByMemberId(memberId);
            return Optional.ofNullable(profile);
        }
        
        /**
         * existsByMemberId() 메서드 구현
         * countByMemberId 결과를 boolean으로 변환
         */
        public static boolean existsByMemberId(IMemberProfileMapper mapper, Long memberId) {
            return mapper.countByMemberId(memberId) > 0;
        }
        
        /**
         * findAll() 메서드 구현
         * MyBatis 전용 메서드를 그대로 사용
         */
        public static List<MemberProfile> findAll(IMemberProfileMapper mapper) {
            return mapper.findAllProfiles();
        }
        
        /**
         * deleteById() 메서드 구현
         * MyBatis 전용 삭제 메서드 사용
         */
        public static void deleteById(IMemberProfileMapper mapper, Long id) {
            mapper.deleteProfile(id);
        }
        
        /**
         * deleteByMemberId() 메서드 구현
         * MyBatis 전용 삭제 메서드 사용
         */
        public static void deleteByMemberId(IMemberProfileMapper mapper, Long memberId) {
            mapper.deleteProfileByMemberId(memberId);
        }
        
        /**
         * findByNicknameContaining() 메서드 구현
         * MyBatis 전용 메서드를 그대로 사용
         */
        public static List<MemberProfile> findByNicknameContaining(IMemberProfileMapper mapper, String nickname) {
            return mapper.findProfilesByNicknameContaining(nickname);
        }
        
        /**
         * findByAddressContaining() 메서드 구현
         * MyBatis 전용 메서드를 그대로 사용
         */
        public static List<MemberProfile> findByAddressContaining(IMemberProfileMapper mapper, String address) {
            return mapper.findProfilesByAddressContaining(address);
        }
        
        /**
         * findByPostalCode() 메서드 구현
         * MyBatis 전용 메서드를 그대로 사용
         */
        public static List<MemberProfile> findByPostalCode(IMemberProfileMapper mapper, String postalCode) {
            return mapper.findProfilesByPostalCode(postalCode);
        }
    }
}