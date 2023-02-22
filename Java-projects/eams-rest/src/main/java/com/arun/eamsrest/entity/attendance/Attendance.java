package com.arun.eamsrest.entity.attendance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    private long id;

    private Date date;
    private Status

//    @OneToMany(mappedBy = "department")
//    private List<Salary> salaries;
//


}
