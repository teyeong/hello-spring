package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;
    private final DataSource dataSource;
    private final MemberRepository memberRepository;


    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // 메모리 사용
//        return  new JdbcMemberRepository(dataSource); // 순수 jdbc 사용
//        return new JdbcTemplateMemberRepository(dataSource); // 스프링 jdbc template 사용
//        return new JpaMemberRepository(em); // JPA 사용
//    }
}
