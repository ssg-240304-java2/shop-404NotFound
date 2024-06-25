package com.nf404.devshop.user.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
