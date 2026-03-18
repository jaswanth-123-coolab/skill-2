package com.entity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil 
{
    private static SessionFactory factory;
    static 
    {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() 
    {
        return factory;
    }
}
