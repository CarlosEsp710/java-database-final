package com.project.code.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.code.Model.CombinedRequest;
import com.project.code.Model.Inventory;
import com.project.code.Model.Product;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.ProductRepository;
import com.project.code.Service.ServiceClass;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ServiceClass serviceClass;

    @PutMapping
    public Map<String, String> updateInventory(@RequestBody CombinedRequest combinedRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            if (combinedRequest == null || combinedRequest.getProduct() == null || combinedRequest.getInventory() == null) {
                response.put("message", "No data available");
                return response;
            }
            Product product = combinedRequest.getProduct();
            Inventory inventory = combinedRequest.getInventory();

            if (!serviceClass.ValidateProductId(product.getId())) {
                response.put("message", "Invalid product ID");
                return response;
            }

            Inventory existingInventory = inventoryRepository.findByProductIdandStoreId(
                    product.getId(), inventory.getStore().getId());
            if (existingInventory == null) {
                response.put("message", "No data available");
                return response;
            }

            existingInventory.setStockLevel(inventory.getStockLevel());
            inventoryRepository.save(existingInventory);
            response.put("message", "Successfully updated product");
            return response;
        } catch (DataIntegrityViolationException e) {
            response.put("message", "Invalid data provided");
            return response;
        }
    }

    @PostMapping
    public Map<String, String> saveInventory(@RequestBody Inventory inventory) {
        Map<String, String> response = new HashMap<>();
        try {
            if (inventory == null || inventory.getProduct() == null || inventory.getStore() == null) {
                response.put("message", "No data available");
                return response;
            }
            if (!serviceClass.validateInventory(inventory)) {
                response.put("message", "Data already present");
                return response;
            }
            inventoryRepository.save(inventory);
            response.put("message", "Data saved successfully");
            return response;
        } catch (DataIntegrityViolationException e) {
            response.put("message", "Invalid data provided");
            return response;
        }
    }

    @GetMapping("/{storeid}")
    public Map<String, Object> getAllProducts(@PathVariable Long storeid) {
        Map<String, Object> response = new HashMap<>();
        List<Product> products = productRepository.findProductsByStoreId(storeid);
        response.put("products", products);
        return response;
    }

    @GetMapping("filter/{category}/{name}/{storeid}")
    public Map<String, Object> getProductName(@PathVariable String category,
                                             @PathVariable String name,
                                             @PathVariable Long storeid) {
        Map<String, Object> response = new HashMap<>();
        List<Product> products;
        if ("null".equalsIgnoreCase(category) && "null".equalsIgnoreCase(name)) {
            products = productRepository.findProductsByStoreId(storeid);
        } else if ("null".equalsIgnoreCase(category)) {
            products = productRepository.findByNameLike(storeid, name);
        } else if ("null".equalsIgnoreCase(name)) {
            products = productRepository.findByCategoryAndStoreId(storeid, category);
        } else {
            products = productRepository.findByNameAndCategory(storeid, name, category);
        }
        response.put("product", products);
        return response;
    }

    @GetMapping("search/{name}/{storeId}")
    public Map<String, Object> searchProduct(@PathVariable String name, @PathVariable Long storeId) {
        Map<String, Object> response = new HashMap<>();
        response.put("product", productRepository.findByNameLike(storeId, name));
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> removeProduct(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        if (!serviceClass.ValidateProductId(id)) {
            response.put("message", "Product not present in database");
            return response;
        }
        inventoryRepository.deleteByProductId(id);
        productRepository.deleteById(id);
        response.put("message", "Product deleted successfully");
        return response;
    }

    @GetMapping("validate/{quantity}/{storeId}/{productId}")
    public boolean validateQuantity(@PathVariable Integer quantity,
                                   @PathVariable Long storeId,
                                   @PathVariable Long productId) {
        Inventory inventory = inventoryRepository.findByProductIdandStoreId(productId, storeId);
        if (inventory == null) {
            return false;
        }
        return inventory.getStockLevel() >= quantity;
    }
}
