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
import com.kynomics.daten.Milestone;
import com.kynomics.daten.Milestonetyp;
import com.kynomics.daten.Patient;
import com.kynomics.daten.Rasse;
import com.kynomics.daten.Spezies;
import com.kynomics.daten.Untersuchungstyp;
import com.kynomics.daten.finder.HalteradresseTreffer;
import com.kynomics.daten.finder.Haltertreffer;
import com.kynomics.daten.finder.MilestoneTreffer;
import com.kynomics.daten.finder.Patiententreffer;
import com.kynomics.daten.finder.SuchkriterienHalter;
import com.kynomics.daten.finder.SuchkriterienHalteradresse;
import com.kynomics.daten.finder.SuchkriterienMilestone;
import com.kynomics.daten.finder.SuchkriterienPatient;
import com.kynomics.daten.finder.SuchkriterienUTyp;
import com.kynomics.daten.finder.UTypTreffer;
import com.kynomics.daten.wrapper.HalterAdresssenPatientWrapper;
import com.kynomics.daten.wrapper.UTypMileStoneWrapper;
import com.kynomics.lib.TransmitterSessionBeanRemote;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * The TransmitterSessionBean class implements the {@link TransmitterSessionBeanRemote}
 * remote interface for giving access to the database through JPA.
 * <p>
 * </p>
 * 
 * @see TransmitterSessionBeanRemote
 * 
 * @since 0.2
 * 
 * @author drdboehm
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
    public List<Untersuchungstyp> initializeUntersuchungstypen() {
        EntityManager em = emf.createEntityManager();
        List<Untersuchungstyp> list = em.createNamedQuery("Untersuchungstyp.findAll").getResultList();
        return list;
    }

    @Override
    public List<Milestonetyp> initializeMilestoneTypen() {
        EntityManager em = emf.createEntityManager();
        List<Milestonetyp> list = em.createNamedQuery("Milestonetyp.findAll").getResultList();
        return list;
    }

    @Override
    public List<Milestone> initializeAllMilestones() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Milestone.findAll").getResultList();
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
        boolean success = false;
        EntityManager em = emf.createEntityManager();
        if (hapw.getHalter() != null) {
            em.merge(hapw.getHalter());
//            em.flush();
            success = true;
        }
        if (hapw.getPatient() != null) {
            em.merge(hapw.getPatient());
//            em.flush();
            success = true;
        }
        if (hapw.getHalteradresse() != null) {
            em.merge(hapw.getHalteradresse());
//            em.flush();
            success = true;
        }
//        em.flush();
        return success;
    } // end method

    /**
     *
     * @param wrapper
     * @return
     */
    @Override
    public boolean storeEjb(UTypMileStoneWrapper wrapper) {
        boolean success = false;
        EntityManager em = emf.createEntityManager();
        if (wrapper.getuTyp() != null) {
            System.out.println("this UType is changed : " + wrapper.getuTyp());
            em.merge(wrapper.getuTyp());
//            em.flush();
            success = true;
        }
        if (wrapper.getMilestone() != null) {
//            em.persist(wrapper.getuTypMilestone());
            em.merge(wrapper.getMilestone());
//            em.flush();
            success = true;
        }
//         em.flush();
        return success;
    }

    @Override
    public List<Haltertreffer> sucheHalter(SuchkriterienHalter kriterien
    ) {
        String abfrage = "SELECT NEW " + Haltertreffer.class.getName()
                + "(h.halterId, h.halterName, h.halterBemerkung) FROM Halter h"
                + kriterien;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).getResultList();
    }

    @Override
    public List<Patiententreffer> suchePatient(SuchkriterienPatient kriterien
    ) {
        String abfrage = "SELECT NEW " + Patiententreffer.class.getName()
                + "(p.patientId) FROM Patient p"
                + kriterien;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).getResultList();
    }

    @Override
    public List<HalteradresseTreffer> sucheHalterAdresse(SuchkriterienHalteradresse kriterien) {
        String abfrage = "SELECT NEW " + HalteradresseTreffer.class.getName()
                + "(ha.halteradresseId) FROM Halteradresse ha"
                + kriterien;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).getResultList();
    }

    @Override
    public List<UTypTreffer> sucheUntersuchungstyp(SuchkriterienUTyp suchKr) {
        String abfrage = "SELECT NEW " + UTypTreffer.class.getName()
                + "(ut.untersuchungtypId) FROM Untersuchungstyp ut"
                + suchKr;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).getResultList();
    }

    @Override
    public List<MilestoneTreffer> sucheMilestone(SuchkriterienMilestone suchKr) {
        String abfrage = "SELECT NEW " + MilestoneTreffer.class.getName()
                + "(ms.milestoneId) FROM Milestone ms"
                + suchKr;
        System.out.println("Abfrage = " + abfrage);
        EntityManager em = emf.createEntityManager();
        return em.createQuery(abfrage).getResultList();
    }

    @Override
    public <T> T
            findById(Class< T> entityClass, Integer primaryKey
            ) {
        EntityManager em = emf.createEntityManager();
        return em.find(entityClass, primaryKey);
    }

    @Override
    public <T> T deleteById(Class<T> entityClass, Integer primaryKey) {
        EntityManager em = emf.createEntityManager();
        T t = em.find(entityClass, primaryKey);
        em.remove(t);
        em.flush();
        return t;
    }

} // end  class  

