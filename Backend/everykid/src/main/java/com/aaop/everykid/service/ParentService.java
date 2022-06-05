package com.aaop.everykid.service;


import com.aaop.everykid.Jwt.TokenUtils;
import com.aaop.everykid.dto.LoginPFormDto;
import com.aaop.everykid.dto.RegisterPFormDto;
import com.aaop.everykid.entity.Auth;
import com.aaop.everykid.entity.Kindergarten;
import com.aaop.everykid.entity.Parent;
import com.aaop.everykid.dto.TokenResponseDto;
import com.aaop.everykid.entity.Teacher;
import com.aaop.everykid.repository.AuthRepository;
import com.aaop.everykid.repository.KindergartenRepository;
import com.aaop.everykid.repository.ParentRepository;
import com.aaop.everykid.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;
    private final KindergartenRepository kindergartenRepository;
    private final TokenUtils tokenUtils;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Parent> findBypID(String PID) {

        return parentRepository.findBypID(PID);
    }


    @Transactional
    public TokenResponseDto signUp(RegisterPFormDto registerPFormDto) {
        Parent parent =
                parentRepository.save(
                        Parent.builder()
                                .pPWD(passwordEncoder.encode(registerPFormDto.getPPWD()))
                                .pID(registerPFormDto.getPID())
                                .pNAME(registerPFormDto.getPNAME())
                                .pEMAIL(registerPFormDto.getPEMAIL())
                                .pPHONE(registerPFormDto.getPPHONE())
                                .kKID(registerPFormDto.getKKID())
                                .tNAME(registerPFormDto.getTNAME())
                                .build());

        String accessToken = tokenUtils.generateJwtToken(parent);
        String refreshToken = tokenUtils.saveRefreshToken(parent);

        authRepository.save(
                Auth.builder().parent(parent).refreshToken(refreshToken).build());

        return TokenResponseDto.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }

    @Transactional
    public TokenResponseDto signIn(LoginPFormDto loginFormDto) throws Exception {

        Parent parent =
                parentRepository
                        .findBypID(loginFormDto.getPID())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Kindergarten kindergarten =
                kindergartenRepository
                        .findByKKID(parent.getKKID())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유치원입니다."));
        Auth auth = authRepository
                        .findByParentPKID(parent.getPKID())
                        .orElseThrow(() -> new IllegalArgumentException("Token 이 존재하지 않습니다."));

        if (!passwordEncoder.matches(loginFormDto.getPPWD(), parent.getPPWD())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = "";
        String refreshToken = auth.getRefreshToken();


        if (tokenUtils.isValidRefreshToken(refreshToken)) {
            accessToken = tokenUtils.generateJwtToken(auth.getParent());
            return TokenResponseDto.builder()
                    .status(200)
                    .ACCESS_TOKEN(accessToken)
                    .REFRESH_TOKEN(auth.getRefreshToken())
                    .pNAME(parent.getPNAME())
                    .pPHONE(parent.getPPHONE())
                    .pID(parent.getPID())
                    .PKID(parent.getPKID())
                    .pEMAIL(parent.getPEMAIL())
                    .KKID(parent.getKKID())
                    .kNAME(kindergarten.getKNAME())
                    .kADDRESS(kindergarten.getKADDRESS())
                    .kPHONE(kindergarten.getKPHONE())
                    .tNAME(parent.getTNAME())
                    .build();

        } else {
            accessToken = tokenUtils.generateJwtToken(auth.getParent());
            refreshToken = tokenUtils.saveRefreshToken(parent);
            auth.refreshUpdate(refreshToken);
        }

        return TokenResponseDto.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
    }


    public List<Parent> findUsers() {
        return parentRepository.findAll();
    }
}
