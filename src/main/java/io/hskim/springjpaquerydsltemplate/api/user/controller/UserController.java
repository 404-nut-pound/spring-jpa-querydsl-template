package io.hskim.springjpaquerydsltemplate.api.user.controller;

import io.hskim.springjpaquerydsltemplate.api.user.dto.UserDto.UserRequestDto;
import io.hskim.springjpaquerydsltemplate.api.user.dto.UserDto.UserResponseDto;
import io.hskim.springjpaquerydsltemplate.api.user.dto.UserDto.UserSearchDto;
import io.hskim.springjpaquerydsltemplate.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "사용자 컨트롤러")
public class UserController {

  private final UserService userService;

  @Operation(
    summary = "사용자 등록",
    description = """
      userId - 필수, 중복 허용하지 않음<br/>
      userName - 필수<br/>
      email - 필수, 중복 허용하지 않음<br/>
      """
  )
  @PostMapping
  public void postUser(@RequestBody UserRequestDto userRequestDto) {
    userService.postUser(userRequestDto);
  }

  @Operation(summary = "사용자 목록 조회")
  @GetMapping
  public Page<UserResponseDto> getUserList(
    @ParameterObject UserSearchDto userSearchDto,
    @ParameterObject Pageable pageable
  ) {
    return userService.getUserList(userSearchDto, pageable);
  }

  @Operation(summary = "사용자 단건 조회")
  @GetMapping(value = "/{userId}")
  public UserResponseDto getUser(@PathVariable String userId) {
    return userService.getUser(userId);
  }

  @Operation(summary = "사용자 수정")
  @PatchMapping(value = "/{userId}")
  public void patchUser(
    @PathVariable String userId,
    @RequestBody UserRequestDto userRequestDto
  ) {
    userRequestDto.setUserId(userId);

    userService.patchUser(userRequestDto);
  }

  @Operation(summary = "사용자 삭제")
  @DeleteMapping(value = "/{userId}")
  public void deleteUser(
    @PathVariable String userId,
    @RequestBody UserRequestDto userRequestDto
  ) {
    userRequestDto.setUserId(userId);

    userService.deleteUser(userRequestDto);
  }
}
