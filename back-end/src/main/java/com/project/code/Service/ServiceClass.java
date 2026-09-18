package com.project.code.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.code.Model.Inventory;
import com.project.code.Model.Product;
import com.project.code.Model.Store;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.ProductRepository;
import com.project.code.Repo.StoreRepository;

@Service
public class ServiceClass {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    public boolean validateInventory(Inventory inventory) {
        if (inventory == null || inventory.getProduct() == null || inventory.getStore() == null) {
            return true;
        }
        Inventory existingInventory = inventoryRepository.findByProductIdandStoreId(
                inventory.getProduct().getId(), inventory.getStore().getId());
        return existingInventory == null;
    }

    public boolean validateProduct(Product product) {
        if (product == null || product.getName() == null || product.getName().trim().isEmpty()) {
            return true;
        }
        Product existingProduct = productRepository.findByName(product.getName());
        return existingProduct == null;
    }

    public boolean ValidateProductId(long id) {
        return productRepository.findById(id).isPresent();
    }

    public boolean validateStore(Store store) {
        if (store == null || store.getName() == null || store.getName().trim().isEmpty()) {
            return true;
        }
        return storeRepository.findById(store.getId()).isEmpty();
    }

    public boolean validateStoreId(Long storeId) {
        return storeId != null && storeRepository.findById(storeId).isPresent();
    }

    public boolean ValidateStoreId(Long storeId) {
        return validateStoreId(storeId);
    }

    public Inventory getInventoryId(Inventory inventory) {
        if (inventory == null || inventory.getProduct() == null || inventory.getStore() == null) {
            return null;
        }
        return inventoryRepository.findByProductIdandStoreId(inventory.getProduct().getId(), inventory.getStore().getId());
    }
}
