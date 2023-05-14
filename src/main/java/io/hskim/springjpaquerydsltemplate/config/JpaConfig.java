package io.hskim.springjpaquerydsltemplate.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig implements AttributeConverter<Boolean, String> {

  @PersistenceContext
  private EntityManager em;

  /**
   * Spring Bean으로 등록해서 다른 repo에서 호출해서 사용
   * @param em
   * @return
   */
  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(em);
  }

  //자바 boolean을 DB 'Y'|'N'으로 변환
  @Override
  public String convertToDatabaseColumn(Boolean attribute) {
    return attribute != null && attribute ? "Y" : "N";
  }

  //DB 'Y'|'N'을 자바 boolean으로 변환
  @Override
  public Boolean convertToEntityAttribute(String dbData) {
    return "Y".equalsIgnoreCase(dbData);
  }

  @Bean
  public AuditorAware<String> auditorAware() {
    return new AuditorAwareImpl();
  }

  public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
      return Optional.of("system");
      // 아래 코드는 Spring Security 필요
      // Authentication authentication = SecurityContextHolder
      //   .getContext()
      //   .getAuthentication();

      // //사용자 인증 정보가 없을 때 기본 반환 값
      // if (
      //   authentication == null ||
      //   !authentication.isAuthenticated() ||
      //   authentication.getPrincipal().equals("anonymousUser")
      // ) {
      //   return Optional.of("system");
      // }

      // //사용자 인증 정보가 있으면 사용자 ID 반환
      // return Optional.of(((User) authentication.getPrincipal()).getUsername());
    }
  }
}
