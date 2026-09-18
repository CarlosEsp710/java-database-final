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

import com.project.code.Model.Customer;
import com.project.code.Model.PlaceOrderRequestDTO;
import com.project.code.Model.PurchaseProductDTO;
import com.project.code.Model.Store;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.StoreRepository;
import com.project.code.Service.OrderService;
import com.project.code.Service.ServiceClass;

@RestController
@RequestMapping("/store")
public class StoreController {

   @Autowired
   private StoreRepository storeRepository;

   @Autowired
   private ServiceClass serviceClass;

   @Autowired
   private OrderService orderService;

   @Autowired
   private InventoryRepository inventoryRepository;

   @PostMapping
   public Map<String, String> addStore(@RequestBody Store store) {
       Map<String, String> response = new HashMap<>();
       try {
           if (!serviceClass.validateStore(store)) {
               response.put("message", "Store already exists");
               return response;
           }
           storeRepository.save(store);
           response.put("message", "Store added successfully");
           return response;
       } catch (DataIntegrityViolationException e) {
           response.put("message", "Invalid store data");
           return response;
       }
   }

   @GetMapping("/{id}")
   public Map<String, Object> getStoreById(@PathVariable Long id) {
       Map<String, Object> response = new HashMap<>();
       response.put("stores", storeRepository.findById(id).orElse(null));
       return response;
   }

   @PutMapping
   public Map<String, String> updateStore(@RequestBody Store store) {
       Map<String, String> response = new HashMap<>();
       try {
           storeRepository.save(store);
           response.put("message", "Store updated successfully");
           return response;
       } catch (DataIntegrityViolationException e) {
           response.put("message", "Unable to update store");
           return response;
       }
   }

   @GetMapping
   public Map<String, Object> listStores() {
       Map<String, Object> response = new HashMap<>();
       response.put("stores", storeRepository.findAll());
       return response;
   }

   @GetMapping("/validate/{storeId}")
   public Map<String, Object> validateStore(@PathVariable Long storeId) {
       Map<String, Object> response = new HashMap<>();
       response.put("status", serviceClass.validateStoreId(storeId));
       return response;
   }

   @PostMapping("/placeOrder")
   public Map<String, String> saveOrder(@RequestBody Map<String, Object> payload) {
       Map<String, String> response = new HashMap<>();
       try {
           PlaceOrderRequestDTO request = new PlaceOrderRequestDTO();
           request.setStoreId(payload.get("storeId") != null ? Long.valueOf(payload.get("storeId").toString()) : null);
           request.setCustomerName(payload.get("customerName") != null ? payload.get("customerName").toString() : null);
           request.setCustomerEmail(payload.get("customerEmail") != null ? payload.get("customerEmail").toString() : null);
           request.setCustomerPhone(payload.get("customerPhone") != null ? payload.get("customerPhone").toString() : null);
           request.setTotalPrice(payload.get("totalPrice") != null ? Double.valueOf(payload.get("totalPrice").toString()) : 0.0);

           Object customerObj = payload.get("customer");
           if (customerObj instanceof Map<?, ?> customerMap) {
               Object name = customerMap.get("name");
               Object email = customerMap.get("email");
               Object phone = customerMap.get("phone");
               if (name != null) {
                   request.setCustomerName(name.toString());
               }
               if (email != null) {
                   request.setCustomerEmail(email.toString());
               }
               if (phone != null) {
                   request.setCustomerPhone(phone.toString());
               }
           }

           Object products = payload.get("purchaseProduct");
           if (products instanceof List<?> productList) {
               request.setPurchaseProduct(productList.stream()
                       .filter(PurchaseProductDTO.class::isInstance)
                       .map(PurchaseProductDTO.class::cast)
                       .toList());
           }

           if (request.getPurchaseProduct() == null || request.getPurchaseProduct().isEmpty()) {
               Object productItems = payload.get("products");
               if (productItems instanceof List<?> items) {
                   request.setPurchaseProduct(items.stream()
                           .filter(Map.class::isInstance)
                           .map(item -> {
                               Map<?, ?> map = (Map<?, ?>) item;
                               PurchaseProductDTO dto = new PurchaseProductDTO();
                               dto.setId(map.get("id") != null ? Long.valueOf(map.get("id").toString()) : null);
                               dto.setQuantity(map.get("quantity") != null ? Integer.valueOf(map.get("quantity").toString()) : 0);
                               dto.setPrice(map.get("price") != null ? Double.valueOf(map.get("price").toString()) : 0.0);
                               return dto;
                           })
                           .toList());
               }
           }

           orderService.saveOrder(request);
           response.put("message", "Order placed successfully");
           return response;
       } catch (Exception e) {
           response.put("message", "Order placement failed: " + e.getMessage());
           return response;
       }
   }

   @DeleteMapping("/{id}")
   public Map<String, String> deleteStore(@PathVariable Long id) {
       Map<String, String> response = new HashMap<>();
       if (!serviceClass.ValidateStoreId(id)) {
           response.put("message", "Store not present in database");
           return response;
       }
       inventoryRepository.deleteByStoreId(id);
       storeRepository.deleteById(id);
       response.put("message", "Store deleted successfully");
       return response;
   }
}

