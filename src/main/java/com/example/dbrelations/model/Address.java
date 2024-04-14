package com.example.dbrelations.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "Address")
@Table(name = "addresses")
public class Address {
    @Id
    @Generated
    private Long id;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address1)) return false;
        return Objects.equals(getId(), address1.getId()) && Objects.equals(getAddress(), address1.getAddress()) && Objects.equals(getUser(), address1.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getUser());
    }
}
