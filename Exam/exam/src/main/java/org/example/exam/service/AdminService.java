package org.example.exam.service;


import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.exam.Entities.CD;
import org.example.exam.Entities.Loan;

import java.util.List;

@Stateful
public class AdminService {

    @PersistenceContext
    private EntityManager em;

    public void addCD(CD cd) {
        em.persist(cd);
    }

    public void updateCD(CD cd) {
        em.merge(cd);
    }

    public void deleteCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public List<Loan> listActiveLoans() {
        return em.createQuery("SELECT l FROM Loan l WHERE l.returnDate IS NULL", Loan.class).getResultList();
    }
}
