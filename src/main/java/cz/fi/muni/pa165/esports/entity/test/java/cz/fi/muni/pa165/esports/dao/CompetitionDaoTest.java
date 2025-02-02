package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.esports.entity.Competition;
import cz.fi.muni.pa165.esports.entity.Team;
import cz.fi.muni.pa165.esports.enums.Game;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
public class CompetitionDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public CompetitionDao compeDao;

    private Competition c1;
    private Competition c2;
    private Competition c3;
    private Competition c4;

    private Game game;

    @BeforeClass
    public void createCompetitions(){
        game = Game.FIFA;

        c1 = new Competition();
        c2 = new Competition();
        c3 = new Competition();

        c1.setName("Japan League 2021");
        c2.setName("Nordic Championship 2021");
        c3.setName("Masters Clash Championship");

        c1.setLocation("Japan");
        c2.setLocation("Oslo");
        c3.setLocation("London");

        compeDao.create(c1);
        compeDao.create(c2);
        compeDao.create(c3);

    }

    @Test
    public void findById(){
        Competition compe = compeDao.findById(c1.getId());
        Assert.assertEquals(compe.getName(), c1.getName());
        Assert.assertNotEquals(compe.getName(), c2.getName());
    }

    @Test
    public void findAll(){
        List<Competition> compe = compeDao.findAll();
        Assert.assertEquals(compe.size(), 3);
    }

    @Test
    public void findByName(){
        Assert.assertEquals(compeDao.findByName("Japan League 2021").getId(), c1.getId());
        Assert.assertEquals(compeDao.findByName("Nordic Championship 2021").getId(), c2.getId());
        Assert.assertEquals(compeDao.findByName("Masters Clash Championship").getId(), c3.getId());
    }

}
