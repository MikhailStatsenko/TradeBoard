package com.app.tradeboard.model;

import com.app.tradeboard.utils.Enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Email(message = "Логин должен соответствовать формату электронной почты")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    @ManyToMany(mappedBy = "users",  fetch = FetchType.EAGER)
    private List<Product> favorites;

    public void addProduct(Product product) {
        if (products == null)
            products = new ArrayList<>();
        products.add(product);
        product.setUser(this);
    }

    public void addFavorite(Product product) {
        System.out.println(favorites);
        if (favorites == null)
            favorites = new ArrayList<>();
        favorites.add(product);
        product.getUsers().add(this);
        System.out.println(favorites);
    }

    public void removeFavorite(Product product) {
        if (favorites != null) {
            for (int i = 0; i < favorites.size(); i++) {
                if (Objects.equals(favorites.get(i).getId(), product.getId()))
                    favorites.remove(i);
            }
            product.removeFromUsers(id);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}