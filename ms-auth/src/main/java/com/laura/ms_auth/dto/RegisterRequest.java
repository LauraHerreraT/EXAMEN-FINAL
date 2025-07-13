package com.laura.ms_auth.dto;

import com.laura.ms_auth.model.Rol;
import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre;
    private String username;
    private String password;
    private Rol rol;
}
