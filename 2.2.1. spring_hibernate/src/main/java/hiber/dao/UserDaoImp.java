package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByModelAndSerias(String model, int serias) {

        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.model = :model AND u.car.serias = :serias");
        query.setParameter("model", model);
        query.setParameter("serias", serias);
        List<User> userList = query.getResultList();

        if (!userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }

    }


}
