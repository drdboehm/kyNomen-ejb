/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappe;
//import com.daa.util.GConnection;

import com.kynomics.daten.Adresstyp;
import com.kynomics.daten.Halter;
import com.kynomics.daten.Halteradresse;
import com.kynomics.daten.Haltertyp;
import com.kynomics.daten.Patient;
import com.kynomics.daten.Rasse;
import com.kynomics.daten.Spezies;
import com.kynomics.daten.finder.HalteradresseTreffer;
import com.kynomics.daten.finder.Haltertreffer;
import com.kynomics.daten.finder.Patiententreffer;
import com.kynomics.daten.finder.SuchkriterienHalter;
import com.kynomics.daten.finder.SuchkriterienHalteradresse;
import com.kynomics.daten.finder.SuchkriterienPatient;
import com.kynomics.daten.wrapper.HalterAdresssenPatientWrapper;
import com.kynomics.lib.TransmitterSessionBeanRemote;
import java.util.ArrayList;
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

    @Override
    public List<Haltertyp> initializeHalterTypen() {
        EntityManager em = emf.createEntityManager();
        List<Haltertyp> list = em.createNamedQuery("Haltertyp.findAll").getResultList();
//        System.out.println("********* ListSize Haltertyp*****" + list.size());
        return list;
    }

    @Override
    public List<Spezies> initializeSpeziesTypen() {
        EntityManager em = emf.createEntityManager();
        List<Spezies> list = em.createNamedQuery("Spezies.findAll").getResultList();
//        System.out.println("********* ListSize *Spezies ****" + list.size());
        return list;
    }

    @Override
    public List<Rasse> initializeRasseTypen() {
        EntityManager em = emf.createEntityManager();
        List<Rasse> list = em.createNamedQuery("Rasse.findAll").getResultList();
//        System.out.println("********* ListSize Rassen ****" + list.size());
        return list;
    }

    @Override
    public List<Adresstyp> initializeAdressTypen() {
        EntityManager em = emf.createEntityManager();
        List<Adresstyp> list = em.createNamedQuery("Adresstyp.findAll").getResultList();
//        System.out.println("********* ListSize Adresstypen ****" + list.size());
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

    @Override
    public List<Halteradresse> halteradresseGet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean storeEjb(HalterAdresssenPatientWrapper hapw) {
        boolean success = true;
        EntityManager em = emf.createEntityManager();
        if (hapw.getHalter() != null) {
            em.persist(hapw.getHalter());
        }
        if (hapw.getPatient() != null) {
            em.persist(hapw.getPatient());
        }
        if (hapw.getHalteradresse() != null) {
            em.persist(hapw.getHalteradresse());
        }
        em.flush();
        return success;
    } // end method

    @Override
    public List<Haltertreffer> sucheHalter(SuchkriterienHalter kriterien
    ) {
        String abfrage = "SELECT NEW " + Haltertreffer.class.getName()
                + "(h.halterId, h.halterName, h.halterBemerkung) FROM Halter h"
                + kriterien;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).setMaxResults(6).getResultList();
    }

    @Override
    public List<Patiententreffer> suchePatient(SuchkriterienPatient kriterien
    ) {
        String abfrage = "SELECT NEW " + Patiententreffer.class.getName()
                + "(p.patientId) FROM Patient p"
                + kriterien;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).setMaxResults(6).getResultList();
    }

    @Override
    public List<HalteradresseTreffer> sucheHalterAdresse(SuchkriterienHalteradresse kriterien) {
        String abfrage = "SELECT NEW " + HalteradresseTreffer.class.getName()
                + "(ha.halteradresseId) FROM Halteradresse ha"
                + kriterien;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).setMaxResults(6).getResultList();
    }

    @Override
    public <T> T
            findById(Class< T> entityClass, Integer primaryKey
            ) {
        EntityManager em = emf.createEntityManager();
        return em.find(entityClass, primaryKey);
    }

} // end  class  

