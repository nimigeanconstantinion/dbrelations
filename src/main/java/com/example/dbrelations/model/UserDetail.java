package com.example.dbrelations.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "UserDetail")
@Table(name = "userdetails")
public class UserDetail
{

    @Id
    @Generated
    private Long id;

    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference
    private User user;
}
