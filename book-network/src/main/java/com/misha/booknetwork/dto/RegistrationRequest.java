package com.misha.booknetwork.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {


    @NotNull(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotNull(message = "Lastname  is mandatory")
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @Email(message = "Email is not formatted")
    @NotNull(message = " Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = " Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    private String password;

}
