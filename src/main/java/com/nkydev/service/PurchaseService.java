package com.nkydev.service;

import com.nkydev.dto.purchase.PurchaseRequestDTO;
import com.nkydev.dto.purchase.PurchaseResponseDTO;
import com.nkydev.dto.purchaseItem.PurchaseItemRequestDTO;
import com.nkydev.dto.purchaseItem.PurchaseItemResponseDTO;
import com.nkydev.entity.*;
import com.nkydev.entity.enums.PurchaseStatus;
import com.nkydev.repository.PurchaseRepository;
import com.nkydev.repository.TicketTypeRepository;
import com.nkydev.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository, TicketTypeRepository ticketTypeRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Transactional
    public PurchaseResponseDTO createPurchase(PurchaseRequestDTO request){
        // buscar el usuario x id
        User user = userRepository.findById(request.userId().intValue())
                .orElseThrow(()-> new RuntimeException("user not found with ID: " + request.userId()));

        // se crea la base de la compra (purchase)
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setPurchaseStatus(PurchaseStatus.PENDING);

        // inicializa el total en cero
        BigDecimal totalAmount= BigDecimal.ZERO;

        for (PurchaseItemRequestDTO itemDTO: request.items()){
            TicketType ticketType = ticketTypeRepository.findById(itemDTO.ticketTypeId().intValue())
                    .orElseThrow(()-> new RuntimeException("ticket type not found with ID: " + itemDTO.ticketTypeId()));

            // se valida el stock
            if (ticketType.getAvailableQuantity() < itemDTO.quantity()){
                throw new RuntimeException("not enough stock for ticket: "+ ticketType.getName());
            }

            //settea el stock descontando los recien tomados
            ticketType.setAvailableQuantity(ticketType.getAvailableQuantity() - itemDTO.quantity());

            BigDecimal subtotal = ticketType.getPrice().multiply(BigDecimal.valueOf(itemDTO.quantity()));
            totalAmount = totalAmount.add(subtotal);

            // se crea el item
            PurchaseItem item = new PurchaseItem();
            item.setPurchase(purchase);
            item.setTicketType(ticketType);
            item.setQuantity(itemDTO.quantity());
            item.setUnitPrice(ticketType.getPrice());

            purchase.addItem(item);
        }

        // se guarda la compra
        purchase.setTotalAmount(totalAmount);
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // y se retorna el stock actualizado al DTO
        return mapToPurchase(savedPurchase);
    }

    public List<PurchaseResponseDTO> getAllPurchases(){
        return purchaseRepository.findAll()
                .stream()
                .map(this::mapToPurchase)
                .toList();
    }

    public PurchaseResponseDTO getPurchaseById(Integer id) {
        return purchaseRepository.findById(id)
                .map(this::mapToPurchase)
                .orElseThrow(() -> new RuntimeException("purchase not found with id: " + id)); // 👈 Agregado ; y map
    }

    public void deletePurchase(Integer id) {
        if (!purchaseRepository.existsById(id)) {
            throw new RuntimeException("purchase not found with id: " + id);
        }
        purchaseRepository.deleteById(id);
    }

    /////////////////

    public List<PurchaseItemResponseDTO> getItemsByPurchaseId(Integer purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found with ID: " + purchaseId));

        return purchase.getItems().stream()
                .map(this::mapToPurchaseItemDto)
                .toList();
    }

    public PurchaseItemResponseDTO getItemByPurchaseIdAndItemId(Integer purchaseId, Integer itemId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase not found with ID: " + purchaseId));

        return purchase.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .map(this::mapToPurchaseItemDto)
                .orElseThrow(() -> new RuntimeException("Item with ID " + itemId + " not found in purchase " + purchaseId));
    }

    ////////////////

    public PurchaseResponseDTO mapToPurchase(Purchase purchase) {
        List<PurchaseItemResponseDTO> itemDTOs = purchase.getItems().stream()
                .map(item -> new PurchaseItemResponseDTO(
                        item.getId(),
                        item.getTicketType().getId(),
                        item.getTicketType().getName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                ))
                .toList();

        return new PurchaseResponseDTO(
                purchase.getId().longValue(),
                purchase.getUser().getId().longValue(),
                purchase.getUser().getName(),
                purchase.getPurchaseDate(),
                purchase.getTotalAmount(),
                purchase.getPurchaseStatus(),
                itemDTOs
        );
    }

    private PurchaseItemResponseDTO mapToPurchaseItemDto(PurchaseItem item) {
        return new PurchaseItemResponseDTO(
                item.getId(),
                item.getTicketType().getId(),
                item.getTicketType().getName(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
        );
    }
}
