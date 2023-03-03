package com.woxsen.leagueapi.payload.response;

import java.util.UUID;

import com.woxsen.leagueapi.utils.CourseTypes;
import com.woxsen.leagueapi.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {

    private UUID id;

    private String name;


    private CourseTypes type;

    private Status status;
    private boolean activeIndex;

}
