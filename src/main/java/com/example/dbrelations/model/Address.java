package com.example.dbrelations.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)


@Entity(name = "Address")
@Table(name = "addresses")
public class Address {
    @Id
    @Generated
    private Long id;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

}
