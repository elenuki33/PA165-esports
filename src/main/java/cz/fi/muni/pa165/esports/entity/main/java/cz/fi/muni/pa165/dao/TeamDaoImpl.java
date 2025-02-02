package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Gabriela Kandova
 */

@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Team team) {
        em.persist(team);
    }

    @Override
    public void delete(Team team) {
        em.remove(team);
    }

    @Override
    public Team findById(Long id) {
        return em.find(Team.class, id);
    }

    @Override
    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }

    @Override
    public Team findByName(String name) {
        try {
            return em.createQuery("select t from Team t where t.name = :name", Team.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Team findByAbbreviation(String abbreviation) {
        try {
            return em.createQuery("select t from Team t where t.abbreviation = :abbr", Team.class)
                    .setParameter("abbr", abbreviation).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}