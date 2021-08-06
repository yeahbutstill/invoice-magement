package com.yeahbutstill.invoice.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
@SQLDelete(sql = "UPDATE virtual_account_configuration SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class VirtualAccountConfiguration extends BaseEntity {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    @Column(unique = true)
    private String code;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_bank_account")
    private BankAccount bankAccount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_payment_provider", unique = true)
    private PaymentProvider paymentProvider;

    @NotNull
    @Min(0)
    private BigDecimal transactionFeeFlat;

    @NotNull
    @Min(0)
    private BigDecimal transactionFeePercentage;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String companyPrefix;

    @NotNull
    @NotEmpty
    private Integer accountNumberLength;

}
