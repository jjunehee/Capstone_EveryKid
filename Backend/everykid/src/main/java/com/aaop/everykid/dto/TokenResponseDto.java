package com.aaop.everykid.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponseDto {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
    private Long PKID;
    private String pID;
    private String pNAME;
    private String pPHONE;
    private String pEMAIL;
    private int status;
    private String kNAME;
    private String kPHONE;
    private String kADDRESS;
    private String tNAME;
}
