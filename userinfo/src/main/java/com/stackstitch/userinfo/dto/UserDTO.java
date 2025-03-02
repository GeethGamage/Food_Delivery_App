package com.stackstitch.userinfo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userID;
    private String userName;
    private String password;
    private String address;
    private String city;
}
