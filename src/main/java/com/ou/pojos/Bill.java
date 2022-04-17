package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="Bill")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

    @Column(name ="bill_created_date")
    private Date billCreatedDate;

    @Column(name = "bill_total_money")
    private BigDecimal billTotalMoney;

    @Column(name = "bill_total_sale_money")
    private BigDecimal billTotalSaleMoney;

    @Column(name = "bill_ship_date")
    private Date billShipDate;

    @Column(name = "bill_ship_address")
    private String billShipAddress;

    @Column(name = "bill_ship_district")
    private String billShipDistrict;

    @Column(name = "bill_ship_city")
    private String billShipCity;

    @ManyToOne
    @JoinColumn(name = "acc_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "payt_id")
    private PaymentType paymentType;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Date getBillCreatedDate() {
        return billCreatedDate;
    }

    public void setBillCreatedDate(Date billCreatedDate) {
        this.billCreatedDate = billCreatedDate;
    }

    public BigDecimal getBillTotalMoney() {
        return billTotalMoney;
    }

    public void setBillTotalMoney(BigDecimal billTotalMoney) {
        this.billTotalMoney = billTotalMoney;
    }

    public BigDecimal getBillTotalSaleMoney() {
        return billTotalSaleMoney;
    }

    public void setBillTotalSaleMoney(BigDecimal billTotalSaleMoney) {
        this.billTotalSaleMoney = billTotalSaleMoney;
    }

    public Date getBillShipDate() {
        return billShipDate;
    }

    public void setBillShipDate(Date billShipDate) {
        this.billShipDate = billShipDate;
    }

    public String getBillShipAddress() {
        return billShipAddress;
    }

    public void setBillShipAddress(String billShipAddress) {
        this.billShipAddress = billShipAddress;
    }

    public String getBillShipDistrict() {
        return billShipDistrict;
    }

    public void setBillShipDistrict(String billShipDistrict) {
        this.billShipDistrict = billShipDistrict;
    }

    public String getBillShipCity() {
        return billShipCity;
    }

    public void setBillShipCity(String billShipCity) {
        this.billShipCity = billShipCity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
