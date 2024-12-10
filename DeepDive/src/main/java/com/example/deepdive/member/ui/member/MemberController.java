package com.example.deepdive.member.ui.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberController {

    @GetMapping("/members")
    public ResponseEntity<Void> showMember() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/members", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        return ResponseEntity.ok().build();
    }
}
//회원 관련 추가적인 기능을 처리하는 컨트롤러.
//현재는 엔드포인트가 최소한으로 구현되어 있음.
//구성:
//
//GET /api/members:
//
//특정 로직 없이 200 OK 응답만 반환.
//추후 회원 목록 조회나 세부 정보를 추가할 수 있음.
//OPTIONS /api/members:
//
//클라이언트가 특정 URL에서 지원되는 HTTP 메서드를 확인할 수 있도록 응답.
//CORS 관련 처리를 위해 사용될 가능성이 있음.
