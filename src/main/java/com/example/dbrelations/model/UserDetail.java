package com.example.dbrelations.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "UserDetail")
@Table(name = "userdetails")
public class UserDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetail that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDetail(), that.getDetail()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDetail());
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", user=" + user +
                '}';
    }
}
