package com.example.dbrelations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.collection.spi.PersistentCollection;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//?Builder
@Setter
@Getter


@Entity(
        name = "User"
)
@Table(name = "users")

public class User {
    @Id
    @Generated
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
//    @Builder.Default
    @JsonIgnoreProperties
    private List<UserDetail> userDetailSet= new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
//    @Builder.Default
    @JsonIgnoreProperties
    private List<Address> userAddresses=new ArrayList<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User user)) return false;
//        return Objects.equals(getId(), user.getId()) && Objects.equals(getUserDetailSet(), user.getUserDetailSet()) && Objects.equals(getUserAddresses(), user.getUserAddresses());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getUserDetailSet(), getUserAddresses());
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", userDetailSet=" + userDetailSet +
//                ", userAddresses=" + userAddresses +
//                '}';
//    }
//
//    public List<UserDetail> getUserDetailListLazy() {
//        if (userDetailSet instanceof PersistentCollection) {
//            ((PersistentCollection) userDetailSet).forceInitialization();
//        }
//        return new ArrayList<>();
//    }
//
//    public List<Address> getUserAddressesLazy() {
//        if (userAddresses instanceof PersistentCollection) {
//            ((PersistentCollection) userDetailSet).forceInitialization();
//        }
//        return new ArrayList<>();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public List<UserDetail> getUserDetailSet() {
//        return userDetailSet;
//    }
//
//    public List<Address> getUserAddresses() {
//        return userAddresses;
//    }
}

