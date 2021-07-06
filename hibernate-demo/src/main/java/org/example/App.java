package org.example;

import org.example.model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            throw e;
        }

        // 插入数据
        Person person = new Person();
        person.setName("jl");
        person.setAge(19);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        }

        // 查询
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List persons = session.createQuery("FROM Person").list();
            for (Person p : (List<Person>) persons) {
                System.out.println(p);
            }
            session.getTransaction().commit();
        }

        // 更新
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person pUpdate = (Person) session.get(Person.class, 2);
            if (pUpdate != null){
                pUpdate.setAge(33);
                session.update(pUpdate);
            }
            session.getTransaction().commit();
        }

        // 删除
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person pDel = (Person) session.get(Person.class, 2);
            if (pDel != null) {
                session.delete(pDel);
            }
            session.getTransaction().commit();
        }

        sessionFactory.close();
    }



}
