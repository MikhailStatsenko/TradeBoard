package com.app.tradeboard.model;

import com.app.tradeboard.utils.Enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "price")
    private double price;

    @Column(name = "city")
    private String city;

    @ElementCollection
    @CollectionTable(name = "product_characteristics", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "characteristic_key")
    @Column(name = "characteristic_value")
    private Map<String, String> characteristics = new HashMap<>();

    @Lob
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "placement_date")
    private LocalDate placementDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product", orphanRemoval = true)
    private List<Image> images;

    @Column(name = "preview_image_id")
    private long previewImageId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "favoriteProducts")
    private List<User> favoritedByUsers;

    @PrePersist
    private void onCreate() {
        placementDate = LocalDate.now();
    }

    public void addImage(Image image) {
        if (images == null)
            images = new ArrayList<>();
        images.add(image);
        image.setProduct(this);
    }

    public void addCharacteristic(String key, String value) {
        characteristics.put(key, value);
    }
}

