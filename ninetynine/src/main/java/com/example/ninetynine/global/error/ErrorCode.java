package com.example.ninetynine.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
//todo 상세코드 작성
    /* 400 BAD_REQUEST : 잘못된 요청  */
    INVALID_REFRESH_TOKEN(BAD_REQUEST,"A-001","리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST,"A-002","리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    DUPLICATE_USERS(BAD_REQUEST,"A-003","중복된 유저가 존재합니다"),
    INVALID_REQUEST(BAD_REQUEST, "A-004","잘못된 요청입니다."),



    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "B-001","권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "B-002", "현재 내 계정 정보가 존재하지 않습니다"),

    /* 403 FORBIDDEN : 권한 없음*/
    UNAUTHORIZED_USER(FORBIDDEN,"C-001","접근 권한이 없습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "D-001","유저 정보를 찾을 수 없습니다"),
    EMPTY_CLIENT(NOT_FOUND,"D-002","등록된 유저가 없습니다."),
    NOT_FOUND_CLIENT(NOT_FOUND,"D-003","해당 유저를 찾을 수 없습니다."),
    NOT_FOUND_PHOTO(NOT_FOUND,"D-004","해당 사진을 찾을 수 없습니다."),
    NOT_FOUND_COMMENT(NOT_FOUND,"D-005","해당 댓글을 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT,"E-001","데이터가 이미 존재합니다."),

    /* 500 INTERNAL_SERVER_ERROR : 서버에서 문제 일어남 */
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "F-001", "입력값을 다시 확인하세요.")
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String detail;
}