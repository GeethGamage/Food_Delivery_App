package com.stackstitch.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
