package com.aaop.everykid.service;


import com.aaop.everykid.Jwt.TokenUtils2;
import com.aaop.everykid.dto.LoginPFormDto;
import com.aaop.everykid.dto.LoginTFormDto;
import com.aaop.everykid.dto.RegisterTFormDto;
import com.aaop.everykid.dto.TokenResponseDto2;
import com.aaop.everykid.entity.Auth2;
import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.repository.AuthRepository2;
import com.aaop.everykid.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TokenUtils2 tokenUtils;
    private final AuthRepository2 authRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Teacher> findBytID(String TID) {

        return teacherRepository.findBytID(TID);
    }

    @Transactional
    public TokenResponseDto2 signUp(RegisterTFormDto registerTFormDto) {
        Teacher teacher =
                teacherRepository.save(
                        Teacher.builder()
                                .tPWD(passwordEncoder.encode(registerTFormDto.getTPWD()))
                                .tID(registerTFormDto.getTID())
                                .tNAME(registerTFormDto.getTNAME())
                                .tEMAIL(registerTFormDto.getTEMAIL())
                                .tPHONE(registerTFormDto.getTPHONE())
                                .build());

        String accessToken = tokenUtils.generateJwtToken(teacher);
        String refreshToken = tokenUtils.saveRefreshToken(teacher);

        authRepository.save(
                Auth2.builder().teacher(teacher).refreshToken(refreshToken).build());

        return TokenResponseDto2.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }

    @Transactional
    public TokenResponseDto2 signIn2(LoginTFormDto loginFormDto) throws Exception {
        Teacher teacher =
                teacherRepository
                        .findBytID(loginFormDto.getTID())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Auth2 auth2 =
                authRepository
                        .findByTeacherTKID(teacher.getTKID())
                        .orElseThrow(()-> new IllegalArgumentException("Token 이 존재하지 않습니다."));
        if (!passwordEncoder.matches(loginFormDto.getTPWD(), teacher.getTPWD())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = "";
        String refreshToken = auth2.getRefreshToken();


        if (tokenUtils.isValidRefreshToken(refreshToken)) {
            accessToken = tokenUtils.generateJwtToken(auth2.getTeacher());
            return TokenResponseDto2.builder()
                    .ACCESS_TOKEN(accessToken)
                    .REFRESH_TOKEN(auth2.getRefreshToken())
                    .tNAME(teacher.getTNAME())
                //    .tALIAS(teacher.getTALIAS())
                    .tPHONE(teacher.getTPHONE())
                    .tID(teacher.getTID())
                    .TKID(teacher.getTKID())
                    .build();
        } else {
            accessToken = tokenUtils.generateJwtToken(auth2.getTeacher());
            refreshToken = tokenUtils.saveRefreshToken(teacher);
            auth2.refreshUpdate(refreshToken);
        }

        return TokenResponseDto2.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }
}
