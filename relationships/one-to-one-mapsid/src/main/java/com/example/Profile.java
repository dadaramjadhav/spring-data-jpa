package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;

    @OneToOne

    @MapsId // ← This is key: tells JPA to use the same ID as User
    @JoinColumn(name = "id") // foreign key to User.id
    private User user;

    public Profile(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Profile [id=" + id + ", bio=" + bio + "]";
    }
}
// What’s happening here?
// Profile.id is not generated — it uses the ID from User.
// @MapsId tells Hibernate to reuse the User's ID as the Profile ID.
// Profile.user is a managed reference to the owning User.