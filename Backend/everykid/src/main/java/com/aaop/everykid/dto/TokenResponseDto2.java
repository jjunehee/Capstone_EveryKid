package com.aaop.everykid.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponseDto2 {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
    private Long TKID;
    private String tID;
    private String tNAME;
    private String tPHONE;
    private String tKID;
    private String tEMAIL;
    private String tALIAS;
}
