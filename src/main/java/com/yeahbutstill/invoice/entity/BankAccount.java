package com.yeahbutstill.invoice.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@SQLDelete(sql = "UPDATE bank_account SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class BankAccount extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    @Column(unique = true)
    private String accountNumber;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String accountName;

    @Size(min = 3, max = 100)
    private String branchName;

}
