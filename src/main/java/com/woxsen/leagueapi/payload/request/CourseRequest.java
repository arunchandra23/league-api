package com.woxsen.leagueapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "In-valid course name")
    private String name;
    @NotBlank(message = "In-valid course type")
    private String courseType;

}
