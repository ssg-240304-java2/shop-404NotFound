package com.nf404.devshop.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class UserDTO {
    private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userAddr;
    private String userPhone;
    private String userDate;
    private int userRank;

}
