package com.example.sample.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {


    @NotBlank(message = "Name cannot be blank or null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private int age;

    @NotBlank(message = "Address cannot be blank or null")
    private String address;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank or null")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @NotBlank(message = "Phone number cannot be blank or null")
    private String phone;
}
