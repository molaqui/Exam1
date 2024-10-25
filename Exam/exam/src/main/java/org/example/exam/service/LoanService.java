package org.example.exam.service;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.exam.Entities.CD;
import org.example.exam.Entities.Loan;

import java.time.LocalDate;
import java.util.List;

@Dependent
@Stateless
public class LoanService {

    @PersistenceContext
    private EntityManager em;

    // Méthode pour lister les CDs disponibles
    public List<CD> listAvailableCDs() {
        return em.createQuery("SELECT c FROM CD c WHERE c.available = true", CD.class).getResultList();
    }

    // Méthode pour emprunter un CD
    public void borrowCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isAvailable()) {
            cd.setAvailable(false);
            Loan loan = new Loan();
            loan.setCd(cd);
            loan.setLoanDate(LocalDate.now());
            em.persist(loan);
        }
    }

    // Nouvelle méthode pour trouver un CD par ID
    public CD findCDById(Long cdId) {
        return em.find(CD.class, cdId);
    }

    // Méthode pour lister les emprunts utilisateurs
    public List<Loan> listUserLoans() {
        return em.createQuery("SELECT l FROM Loan l WHERE l.returnDate IS NULL", Loan.class).getResultList();
    }
}
