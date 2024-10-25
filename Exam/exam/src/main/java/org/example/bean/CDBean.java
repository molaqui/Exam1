package org.example.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import org.example.exam.Entities.CD;
import org.example.exam.service.LoanService;

@Named
@RequestScoped
public class CDBean implements Serializable {

    @Inject
    private LoanService loanService;

    private List<CD> availableCDs;
    private CD selectedCD;

    @PostConstruct
    public void init() {
        availableCDs = loanService.listAvailableCDs();
    }

    public void borrowCD(Long cdId) {
        selectedCD = loanService.findCDById(cdId);
    }

    public void confirmBorrow() {
        if (selectedCD != null) {
            loanService.borrowCD(selectedCD.getId());
            availableCDs = loanService.listAvailableCDs();  // Rafraîchir la liste
        }
    }

    // Getters et setters
    public List<CD> getAvailableCDs() {
        return availableCDs;
    }

    public CD getSelectedCD() {
        return selectedCD;
    }

    public void setSelectedCD(CD selectedCD) {
        this.selectedCD = selectedCD;
    }
}
