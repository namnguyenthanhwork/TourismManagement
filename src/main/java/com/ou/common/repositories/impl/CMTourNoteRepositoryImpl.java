package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMTourNoteRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.NoteEntity;
import com.ou.pojos.PostEntity;
import com.ou.pojos.TourEntity;
import com.ou.pojos.TourNoteEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMTourNoteRepositoryImpl implements CMTourNoteRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;
    @Override
    public List<TourNoteEntity> getTourNoteByTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourNoteEntity> tourNoteEntityRoot = criteriaQuery.from(TourNoteEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(tourNoteEntityRoot.get("tourId").as(Integer.class),
                        tourEntityRoot.get("tourId").as(Integer.class)),
                criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                        postEntityRoot.get("postId").as(Integer.class)),
                criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), tourSlug))
                .multiselect(tourNoteEntityRoot.get("tourId"), tourNoteEntityRoot.get("noteId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourNoteEntity> tourNotes = pojoBeanFactory.getApplicationContext()
                .getBean("tourNoteEntityList", List.class);
        results.forEach(result -> {
            TourNoteEntity tourNote = pojoBeanFactory.getApplicationContext()
                    .getBean(TourNoteEntity.class);
            tourNote.setTourId((Integer) result[0]);
            tourNote.setNoteId((Integer) result[1]);

            tourNotes.add(tourNote);
        });
        return tourNotes;
    }

    @Override
    public List<TourNoteEntity> getTourNoteByNote(String noteSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourNoteEntity> tourNoteEntityRoot = criteriaQuery.from(TourNoteEntity.class);
        Root<NoteEntity> noteEntityRoot = criteriaQuery.from(NoteEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourNoteEntityRoot.get("noteId").as(Integer.class),
                                noteEntityRoot.get("noteId").as(Integer.class)),
                        criteriaBuilder.equal(noteEntityRoot.get("noteSlug").as(String.class), noteSlug))
                .multiselect(tourNoteEntityRoot.get("tourId"), tourNoteEntityRoot.get("noteId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourNoteEntity> tourNotes = pojoBeanFactory.getApplicationContext()
                .getBean("tourNoteEntityList", List.class);
        results.forEach(result -> {
            TourNoteEntity tourNote = pojoBeanFactory.getApplicationContext()
                    .getBean(TourNoteEntity.class);
            tourNote.setTourId((Integer) result[0]);
            tourNote.setNoteId((Integer) result[1]);

            tourNotes.add(tourNote);
        });
        return tourNotes;
    }

    @Override
    public boolean createTourNote(TourNoteEntity tourNoteEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(tourNoteEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateTourNote(TourNoteEntity tourNoteEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(tourNoteEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteTourNote(TourNoteEntity tourNoteEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(tourNoteEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
