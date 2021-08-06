package com.yeahbutstill.invoice.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@SQLDelete(sql = "UPDATE virtual_account SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class VirtualAccount extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_payment_provider")
    private PaymentProvider paymentProvider;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_virtual_account_configuration", unique = true)
    private VirtualAccountConfiguration virtualAccountConfiguration;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String companyId;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String accountNumber;

}
