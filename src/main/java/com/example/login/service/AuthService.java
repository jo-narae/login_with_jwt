package com.example.login.service;

import com.example.login.domain.Member;
import com.example.login.util.EncryptHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthService {

    @Autowired
    private EncryptHelper encryptHelper;

    @Autowired
    MemberService memberService;

    public Member validUserId(String email) {
        return memberService.findByEmail(email).orElseThrow(NoSuchElementException::new);
    }

    public void validPassword(String password, String userPassword) {
        if (!encryptHelper.isMatch(password, userPassword)) {
            throw new BadCredentialsException("비밀번호가 틀렸습니다.");
        }
    }
}
