package com.ou.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Bill", schema = "TourismManagement")
public class BillEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bill_id")
    private int billId;
    @Basic
    @Column(name = "bill_created_date")
    private Timestamp billCreatedDate;
    @Basic
    @Column(name = "bill_total_money")
    private BigDecimal billTotalMoney  = BigDecimal.ZERO;
    @Basic
    @Column(name = "bill_total_sale_money")
    private BigDecimal billTotalSaleMoney= BigDecimal.ZERO;;
    @Basic
    @Column(name = "bill_ship_date")
    private Timestamp billShipDate;
    @Basic
    @Column(name = "bill_ship_address")
    private String billShipAddress;
    @Basic
    @Column(name = "bill_ship_district")
    private String billShipDistrict;
    @Basic
    @Column(name = "bill_ship_city")
    private String billShipCity;

    @Basic
    @Column(name = "bill_is_paid")
    private Boolean billIsPaid = false;

    @Basic
    @Column(name = "bill_departure_date")
    private Timestamp billDepartureDate;

    @Basic
    @Column(name = "acc_id")
    private int accId;
    @Basic
    @Column(name = "payt_id")
    private int paytId;


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Timestamp getBillCreatedDate() {
        return billCreatedDate;
    }

    public void setBillCreatedDate(Timestamp billCreatedDate) {
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

    public Timestamp getBillShipDate() {
        return billShipDate;
    }

    public void setBillShipDate(Timestamp billShipDate) {
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

    public Boolean getBillIsPaid() {
        return billIsPaid;
    }

    public void setBillIsPaid(Boolean billIsPaid) {
        this.billIsPaid = billIsPaid;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }


    public int getPaytId() {
        return paytId;
    }

    public void setPaytId(int paytId) {
        this.paytId = paytId;
    }

    public Timestamp getBillDepartureDate() {
        return billDepartureDate;
    }

    public void setBillDepartureDate(Timestamp billDepartureDate) {
        this.billDepartureDate = billDepartureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillEntity that = (BillEntity) o;
        return billId == that.billId && accId == that.accId  && paytId == that.paytId &&
                Objects.equals(billCreatedDate, that.billCreatedDate) &&
                Objects.equals(billTotalMoney, that.billTotalMoney) &&
                Objects.equals(billTotalSaleMoney, that.billTotalSaleMoney) &&
                Objects.equals(billShipDate, that.billShipDate) &&
                Objects.equals(billShipAddress, that.billShipAddress) &&
                Objects.equals(billShipDistrict, that.billShipDistrict) &&
                Objects.equals(billShipCity, that.billShipCity) && billIsPaid==that.billIsPaid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, billCreatedDate, billTotalMoney, billTotalSaleMoney, billShipDate, billShipAddress,
                billShipDistrict, billShipCity, accId, paytId, billIsPaid);
    }
}
