/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappe;
//import com.daa.util.GConnection;

import com.kynomics.daten.Halter;
import com.kynomics.daten.Haltertyp;
import com.kynomics.daten.Patient;
import com.kynomics.daten.Rasse;
import com.kynomics.daten.Spezies;
import com.kynomics.lib.TransmitterSessionBeanRemote;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author teilnehmer
 */
@Stateless
public class TransmitterSessionBean implements TransmitterSessionBeanRemote {

    @PersistenceUnit(unitName = "kyNomen-ejbPU")
    private EntityManagerFactory emf;//=Persistence.createEntityManagerFactory("PizzaService-ejbPU");

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
//    @Override
//    public List<Gericht> initializeMenu() {
//        EntityManager em = emf.createEntityManager();
//        return em.createNamedQuery("Gericht.findAll").getResultList();
//    }
    @PostConstruct
    public void init() {
    }

    @PreDestroy
    public void delete() {
//        try {
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(TransmitterSessionBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    @Override
//    public boolean storeEjb(BestellWrapper bw) {
//        Bestellung bestellung = bw.getBestellung();
//        bestellung.setOrderDate(new Date());
//        Kunde kunde = bw.getKunde();
//        List<Gericht> orderedGerichte = bw.getGerichte();
//        /*
//         Set timespamp fields - later distinguish if Kunde is present, then 
//        leave firstEntryDate as is
//         */
//        kunde.setFirstEntryDate(new Date());
//        kunde.setLastEntryDate(new Date());
//        boolean success = true;
//        EntityManager em = emf.createEntityManager();
//
//        em.persist(kunde);
//        em.flush();
//        // KundeId is set ! - bestellung takes Kunde-Object, not the integer ID only !
//        bestellung.setKeyKunde(kunde);
//        em.persist(bestellung);
//        em.flush();
//        Orderposition orderpositions;
//        for (Gericht g : orderedGerichte) {
//            orderpositions = new Orderposition();
//            // set the Key 
//            orderpositions.setKeyOrder(bestellung);
//            orderpositions.setKeyGericht(g);
//            orderpositions.setAmountPosition(g.getAmount());
//            em.persist(orderpositions);
//        }
//        em.flush();
//        return success;
//
//    } // end method
    @Override
    public boolean storeEjb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Haltertyp> initializeHalterTypen() {
        EntityManager em = emf.createEntityManager();
        List<Haltertyp> list = em.createNamedQuery("Haltertyp.findAll").getResultList();
        System.out.println("********* ListSize Haltertyp*****" + list.size());
        return list;
    }

    @Override
    public List<Spezies> initializeSpeziesTypen() {
        EntityManager em = emf.createEntityManager();
        List<Spezies> list = em.createNamedQuery("Spezies.findAll").getResultList();
        System.out.println("********* ListSize *Spezies ****" + list.size());
        return list;
    }

    @Override
    public List<Rasse> initializeRasseTypen() {
        EntityManager em = emf.createEntityManager();
        List<Rasse> list = em.createNamedQuery("Rasse.findAll").getResultList();
        System.out.println("********* ListSize Rassen ****" + list.size());
        return list;
    }

    @Override
    public List<Halter> halterGet() {
        EntityManager em = emf.createEntityManager();
        List<Halter> list = em.createNamedQuery("Halter.findAll").getResultList();
        return list;
    }

    @Override
    public List<Patient> patientGet() {
        EntityManager em = emf.createEntityManager();
        List<Patient> list = em.createNamedQuery("Patient.findAll").getResultList();
        return list;
    }
} // end  class

