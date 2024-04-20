package com.example.dbrelations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.collection.spi.PersistentCollection;

import java.util.*;

@Data
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Setter
@Getter


@Entity(
        name = "User"
)
@Table(name = "users")
//@NamedEntityGraph(name = "User.details", attributeNodes = @NamedAttributeNode("details"))

public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Alte atribute

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private Set<UserDetail> details = new HashSet<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private Set<Address> addresses = new HashSet<>();

        // Constructori, getteri, setteri

        public User(Long id){
                this.id=id;
                this.addresses=new HashSet<>();
                this.details=new HashSet<>();
        }

        public User(Long id,Set<UserDetail> details,Set<Address> addresses){
                this.id=id;
                this.details=details!=null?details:new HashSet<>();
                this.addresses=addresses!=null?addresses:new HashSet<>();
        }

        public User(Long id,Set<Address> param){
                this.id=id;
                this.addresses=param;

        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof User user)) return false;
            return Objects.equals(getId(), user.getId()) && Objects.equals(getDetails(), user.getDetails()) && Objects.equals(getAddresses(), user.getAddresses());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getId());
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", details=" + details +
                        ", addresses=" + addresses +
                        '}';
        }
}

