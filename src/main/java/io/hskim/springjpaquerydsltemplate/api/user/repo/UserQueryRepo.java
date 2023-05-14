package io.hskim.springjpaquerydsltemplate.api.user.repo;

import static io.hskim.springjpaquerydsltemplate.entity.QUserEntity.userEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.hskim.springjpaquerydsltemplate.api.user.dto.UserDto.UserSearchDto;
import io.hskim.springjpaquerydsltemplate.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class UserQueryRepo {

  private final JPAQueryFactory queryFactory;

  public Page<UserEntity> getUserList(
    UserSearchDto userSearchDto,
    Pageable pageable
  ) {
    JPAQuery<UserEntity> selectQuery = queryFactory
      .selectFrom(userEntity)
      .where(
        userIdContains(userSearchDto.getUserId()),
        userNameContains(userSearchDto.getUserName()),
        emailContains(userSearchDto.getEmail())
      )
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .orderBy(userEntity.createdAt.desc());

    JPAQuery<Long> countQuery = queryFactory
      .select(userEntity.count())
      .from(userEntity)
      .where(
        userIdContains(userSearchDto.getUserId()),
        userNameContains(userSearchDto.getUserName()),
        emailContains(userSearchDto.getEmail())
      )
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize());

    return PageableExecutionUtils.getPage(
      selectQuery.fetch(),
      pageable,
      countQuery::fetchOne
    );
  }

  private BooleanExpression userIdContains(String userId) {
    return StringUtils.hasText(userId)
      ? userEntity.userId.contains(userId)
      : null;
  }

  private BooleanExpression userNameContains(String userName) {
    return StringUtils.hasText(userName)
      ? userEntity.userName.contains(userName)
      : null;
  }

  private BooleanExpression emailContains(String email) {
    return StringUtils.hasText(email) ? userEntity.email.contains(email) : null;
  }
}
