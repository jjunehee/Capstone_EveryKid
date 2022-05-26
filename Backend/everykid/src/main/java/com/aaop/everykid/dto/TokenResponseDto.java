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
    private String kKID;
    private String kNAME;
    private String pEMAIL;
    private String pALIAS;
    private String C_NAME;
    private String C_AGE;
    private String C_IMG;
    private int status;
}
