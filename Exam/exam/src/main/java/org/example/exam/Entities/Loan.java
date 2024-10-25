package org.example.exam.Entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CD cd;

    private LocalDate loanDate;
    private LocalDate returnDate;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CD getCd() { return cd; }
    public void setCd(CD cd) { this.cd = cd; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
