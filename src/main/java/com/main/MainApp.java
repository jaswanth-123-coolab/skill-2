package com.main;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.entity.HibernateUtil;
import com.entity.Product;
public class MainApp 
{

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n PRODUCT CRUD MENU");
            System.out.println("1. Insert Product");
            System.out.println("2. Get All Products");
            System.out.println("3. Get Product By ID");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) 
            {
               
                case 1: 
                {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    Product p = new Product();
                    System.out.print("Enter Product ID: ");
                    p.setPid(sc.nextInt());
                    System.out.print("Enter Product Name: ");
                    p.setPname(sc.next());
                    System.out.print("Enter Product Price: ");
                    p.setPrice(sc.nextInt());
                    session.persist(p);
                    tx.commit();
                    session.close();
                    System.out.println("Product Inserted Successfully");
                    break;
                }
                
                case 2: 
                {
                    Session session = HibernateUtil.getSessionFactory().openSession();

                    List<Product> list =session.createQuery("from Product", Product.class).list();

                    System.out.println("PID\tPNAME\tPRICE");
                    for (Product p : list) 
                    {
                        System.out.println(p.getPid() + "\t" +p.getPname() + "\t" +p.getPrice());
                    }
                    session.close();
                    break;
                }
                
                case 3: 
                {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    Product p = session.get(Product.class, id);
                    System.out.println("PID\tPNAME\tPRICE");
                    if (p != null) 
                    {
                    	System.out.println(p.getPid() + "\t" +p.getPname() + "\t" +p.getPrice());
                    } 
                    else 
                    {
                        System.out.println("Product Not Found");
                    }
                    session.close();
                    break;
                }

                case 4: 
                {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter Product ID to Update: ");
                    int id = sc.nextInt();
                    Product p = session.get(Product.class, id);
                    if (p != null) 
                    {
                        System.out.print("Enter New Name: ");
                        p.setPname(sc.next());
                        System.out.print("Enter New Price: ");
                        p.setPrice(sc.nextInt());
                        session.merge(p);
                        tx.commit();
                        System.out.println("Product Updated Successfully");
                    } 
                    else 
                    {
                        System.out.println("Product Not Found");
                    }
                    session.close();
                    break;
                }

                
                case 5: 
                {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    System.out.print("Enter Product ID to Delete: ");
                    int id = sc.nextInt();
                    Product p = session.get(Product.class, id);

                    if (p != null) {
                        session.remove(p);
                        tx.commit();
                        System.out.println("Product Deleted Successfully");
                    } 
                    else 
                    {
                        System.out.println("Product Not Found");
                    }
                    session.close();
                    break;
                }

                case 0:
                    System.out.println("Exiting Application");
                    HibernateUtil.getSessionFactory().close();
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 0);

        sc.close();
    }
}
