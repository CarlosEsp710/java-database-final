package com.project.code.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.code.Model.Customer;
import com.project.code.Model.Inventory;
import com.project.code.Model.OrderDetails;
import com.project.code.Model.OrderItem;
import com.project.code.Model.PlaceOrderRequestDTO;
import com.project.code.Model.Product;
import com.project.code.Model.PurchaseProductDTO;
import com.project.code.Model.Store;
import com.project.code.Repo.CustomerRepository;
import com.project.code.Repo.InventoryRepository;
import com.project.code.Repo.OrderDetailsRepository;
import com.project.code.Repo.OrderItemRepository;
import com.project.code.Repo.ProductRepository;
import com.project.code.Repo.StoreRepository;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void saveOrder(PlaceOrderRequestDTO placeOrderRequest) {
        if (placeOrderRequest == null || placeOrderRequest.getPurchaseProduct() == null) {
            throw new IllegalArgumentException("Order data is invalid");
        }

        Customer customer = customerRepository.findByEmail(placeOrderRequest.getCustomerEmail());
        if (customer == null) {
            customer = new Customer();
            customer.setName(placeOrderRequest.getCustomerName());
            customer.setEmail(placeOrderRequest.getCustomerEmail());
            customer.setPhone(placeOrderRequest.getCustomerPhone());
            customer = customerRepository.save(customer);
        }

        Store store = storeRepository.findById(placeOrderRequest.getStoreId()).orElseThrow(
                () -> new IllegalArgumentException("Store not found")
        );

        OrderDetails orderDetails = new OrderDetails(
                customer,
                store,
                placeOrderRequest.getTotalPrice(),
                LocalDateTime.now()
        );
        OrderDetails savedOrder = orderDetailsRepository.save(orderDetails);

        List<PurchaseProductDTO> products = placeOrderRequest.getPurchaseProduct();
        for (PurchaseProductDTO productDto : products) {
            Product product = productRepository.findById(productDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productDto.getId()));

            Inventory inventory = inventoryRepository.findByProductIdandStoreId(product.getId(), store.getId());
            if (inventory == null) {
                throw new IllegalArgumentException("Inventory not found for product: " + product.getId());
            }
            if (inventory.getStockLevel() < productDto.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getId());
            }

            inventory.setStockLevel(inventory.getStockLevel() - productDto.getQuantity());
            inventoryRepository.save(inventory);

            OrderItem orderItem = new OrderItem(savedOrder, product, productDto.getQuantity(), productDto.getPrice());
            orderItemRepository.save(orderItem);
        }
    }
}
