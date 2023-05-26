package com.app.tradeboard.service;

import com.app.tradeboard.model.Image;
import com.app.tradeboard.model.Product;
import com.app.tradeboard.repository.ProductRepository;
import com.app.tradeboard.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.app.tradeboard.model.User;
import org.springframework.web.multipart.MultipartFile;

import org.postgresql.util.PGobject;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductService(UserRepository userRepository,
                          ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product, long userId,
                           HttpServletRequest request, MultipartFile[] images) throws IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();

        String[] keys = parameterMap.get("characteristics.keys");
        String[] values = parameterMap.get("characteristics.values");
        for (int i = 0; i < keys.length; i++) {
            product.getCharacteristics().put(keys[i], values[i]);
        }

        for (MultipartFile image : images) {
            Image convertedImage = toImageEntity(image);
            product.addImage(convertedImage);
            convertedImage.setProduct(product);
        }

        User user = userRepository.findById(userId).get();
        user.addProduct(product);
        productRepository.save(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

}
