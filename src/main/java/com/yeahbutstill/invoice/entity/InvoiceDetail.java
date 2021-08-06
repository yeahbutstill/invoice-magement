package com.yeahbutstill.invoice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@SQLDelete(sql = "UPDATE invoice_detail SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class InvoiceDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String productCode;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String productName;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 36)
    private String measurementUnit;

    @NotNull
    @Min(0)
    private BigDecimal unitPrice;

    @NotNull
    @Min(0)
    private BigDecimal quantity;

}
