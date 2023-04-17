package loginPractice.login2.domain.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static Long sequence = 0L;

    // save
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(sequence,member);
        return member;
    }

    // findById
    public Member findById(long id) {
        Member findMember = store.get(id);
        return findMember;
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    // findByLoginId
    public Optional<Member> findByLoginId(String loginId) {
        // loginId가 없을수도 있음!
        return findAll().stream()
                .filter(m-> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void clear(){
        store.clear();
    }

}
