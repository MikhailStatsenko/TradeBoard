package com.app.tradeboard.service;

import com.app.tradeboard.model.Image;
import com.app.tradeboard.model.Product;
import com.app.tradeboard.model.User;
import com.app.tradeboard.repository.ProductRepository;
import com.app.tradeboard.repository.UserRepository;
import com.app.tradeboard.utils.Exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product, long userId,
                           HttpServletRequest request, MultipartFile[] images) throws IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] keys = parameterMap.get("characteristics.keys");
        String[] values = parameterMap.get("characteristics.values");

        if (keys != null && !keys[0].equals("")) {
            addCharacteristics(product, keys, values);
        }

        boolean productHasImages = images != null && images[0].getSize() > 0;

        if (productHasImages) {
            addImages(product, images);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.addProduct(product);
        Product savedProduct = productRepository.save(product);

        if (productHasImages) {
            setPreviewImage(savedProduct);
            productRepository.save(savedProduct);
        }
    }

    private void addCharacteristics(Product product, String[] keys, String[] values) {
        for (int i = 0; i < keys.length; i++) {
            product.addCharacteristic(keys[i], values[i]);
        }
    }

    private void addImages(Product product, MultipartFile[] images) throws IOException {
        for (MultipartFile image : images) {
            if (image.getSize() > 0) {
                Image convertedImage = toImageEntity(image);
                product.addImage(convertedImage);
                convertedImage.setProduct(product);
            }
        }
    }

    private void setPreviewImage(Product product) {
        Image previewImage = product.getImages().get(0);
        previewImage.setPreviewImage(true);
        product.setPreviewImageId(previewImage.getId());
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


