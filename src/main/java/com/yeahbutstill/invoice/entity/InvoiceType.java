package com.yeahbutstill.invoice.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@SQLDelete(sql = "UPDATE invoice_type SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class InvoiceType extends BaseEntity {

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
    @NotEmpty
    @Size(min = 3, max = 50)
    private String paymentType;

//    @ManyToMany()
//    @JoinTable(
//            name = "invoice_type_provider",
//            joinColumns = @JoinColumn(name = "id_invoice_type"),
//            inverseJoinColumns = @JoinColumn(name = "id_payment_provider")
//    )
//    private Set<PaymentProvider> paymentProviders = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "invoice_type_configuration",
            joinColumns = @JoinColumn(name = "id_invoice_type"),
            inverseJoinColumns = @JoinColumn(name = "id_virtual_account_configuration")
    )
    private Set<VirtualAccountConfiguration> virtualAccountConfigurations = new HashSet<>();

}
