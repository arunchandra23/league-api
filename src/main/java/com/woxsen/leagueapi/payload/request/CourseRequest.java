package com.woxsen.leagueapi.payload.request;

import com.woxsen.leagueapi.utils.CourseTypes;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "In-valid course name")
    private String name;
    @NotBlank(message = "In-valid course type")
    private String courseType;

}
