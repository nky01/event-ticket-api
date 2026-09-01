package com.nkydev.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY) //no trae la bd completa, solo lo que se pedira
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private LocalDateTime purchaseDate;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> items = new ArrayList<>();

    public Purchase(){}

    public Purchase(Integer id, User user, LocalDateTime purchaseDate, BigDecimal totalAmount, PurchaseStatus purchaseStatus) {
        this.id = id;
        this.user = user;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.purchaseStatus = purchaseStatus;
    }

    public void addItem(PurchaseItem item) {
        items.add(item);
        item.setPurchase(this);
    }

    public void removeItem(PurchaseItem item) {
        items.remove(item);
        item.setPurchase(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public List<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) && Objects.equals(user, purchase.user) && Objects.equals(purchaseDate, purchase.purchaseDate) && Objects.equals(totalAmount, purchase.totalAmount) && purchaseStatus == purchase.purchaseStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, purchaseDate, totalAmount, purchaseStatus);
    }
}
