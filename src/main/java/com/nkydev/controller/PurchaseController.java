package com.nkydev.controller;

import com.nkydev.dto.purchase.PurchaseRequestDTO;
import com.nkydev.dto.purchase.PurchaseResponseDTO;
import com.nkydev.dto.purchaseItem.PurchaseItemResponseDTO;
import com.nkydev.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public PurchaseResponseDTO createPurchase(@Valid @RequestBody PurchaseRequestDTO request) {
        return purchaseService.createPurchase(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<PurchaseResponseDTO> getPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @purchaseSecurity.isOwner(#id, authentication.name)")
    public PurchaseResponseDTO getPurchaseById(@PathVariable Integer id) {
        return purchaseService.getPurchaseById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchase(@PathVariable Integer id) {
        purchaseService.deletePurchase(id);
    }

    // se obtienen los items de una compra especifica x id
    @GetMapping("/{purchaseId}/items")
    public List<PurchaseItemResponseDTO> getItemsByPurchaseId(@PathVariable Integer purchaseId) {
        return purchaseService.getItemsByPurchaseId(purchaseId);
    }

    // se obtiene solo un item(id) dentro de una compra(id)
    @GetMapping("/{purchaseId}/items/{itemId}")
    public PurchaseItemResponseDTO getItemByPurchaseIdAndItemId(@PathVariable Integer purchaseId, @PathVariable Integer itemId) {
        return purchaseService.getItemByPurchaseIdAndItemId(purchaseId, itemId);
    }
}