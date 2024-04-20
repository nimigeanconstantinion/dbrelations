package com.example.dbrelations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithDetailsDTO {
    private Long id;
    private Set<UserDetail> details=new HashSet<>();
    private Set<Address> addresses=new HashSet<>();
}
