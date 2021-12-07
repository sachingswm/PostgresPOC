package com.example.vgproject2.service;

import com.example.vgproject2.entity.Customers;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

@Service
public class CustomersService {

    private static final String BASENAME="cust_arr";
    private static  final String LEFT="_small";
    private static  final String RIGHT="_big";
    private Connection connection=null;
    private Statement statement=null;
    private static final String [] permutation={"_small_small","_small_big","_big_small","_big_big"};


    public Customers findBy(String key,String value) {

        switch (key)
        {
            case "username":
                return findByUsernameV2(value);

            case "email":
                return findByEmail(value);

            case "phone":
                return findByPhone(value);

        }
        return null;
    }

    public Customers mapper(ResultSet resultSet)
    {
        try {
            Customers customers = new Customers();
            while (resultSet.next()) {
                customers.setId(resultSet.getInt(1));
                customers.setUsername(resultSet.getString(2));
                customers.setEmail(resultSet.getString(3));
                customers.setPhone(resultSet.getString(4));
                customers.setAddress(resultSet.getString(5));
            }
            return customers;
        }catch (Exception e)
        {
            return null;
        }
    }


    public Customers findByUsernameV2(String username)
    {
        char first_char=username.charAt(0);
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/cognito",
                            "postgres", "password");
            statement = connection.createStatement();
        }
        catch (Exception e)
        {

            return null;
        }
        String table=BASENAME;
        if(first_char>='A' && first_char<'M')
        {
            table+=LEFT;
        }
        else
        {
            table+=RIGHT;
        }
        String t1=table+permutation[0];
        String t2=table+permutation[1];
        String t3=table+permutation[2];
        String t4=table+permutation[3];
        new Thread()
        {
            public void run()
            {
                try
                {
                    String query="select * from "+t1+" where "+t1+".username='"+username+"'";
                    ResultSet resultSet=statement.executeQuery(query);
                    Customers customers=mapper(resultSet);
                    if(customers!=null && customers.getUsername().equals(username))
                    {
                        System.out.println(customers);
                    }
                }
                catch (Exception e)
                {

                }
            }
        }.start();
        new Thread()
        {
            public void run()
            {
                try
                {
                    String query="select * from "+t2+" where "+t2+".username='"+username+"'";
                    ResultSet resultSet=statement.executeQuery(query);
                    Customers customers=mapper(resultSet);
                    if(customers!=null && customers.getUsername().equals(username))
                    {
                        System.out.println(customers);

                    }
                }
                catch (Exception e)
                {

                }
            }
        }.start();
        new Thread()
        {
            public void run()
            {
                try
                {
                    String query="select * from "+t3+" where "+t3+".username='"+username+"'";
                    ResultSet resultSet=statement.executeQuery(query);
                    Customers customers=mapper(resultSet);
                    if(customers!=null && customers.getUsername().equals(username))
                    {
                        System.out.println(customers);

                    }
                }
                catch (Exception e)
                {

                }
            }
        }.start();
        new Thread()
        {
            public void run()
            {
                try
                {
                    String query="select * from "+t4+" where "+t4+".username='"+username+"'";
                    ResultSet resultSet=statement.executeQuery(query);
                    Customers customers=mapper(resultSet);
                    if(customers!=null && customers.getUsername().equals(username))
                    {
                        System.out.println(customers);

                    }
                }
                catch (Exception e)
                {

                }
            }
        }.start();

        return null;
    }


    public Customers findByUsername(String username)
    {
        char first_char=username.charAt(0);
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/cognito",
                            "postgres", "password");
            statement = connection.createStatement();
        }
        catch (Exception e)
        {

            return null;
        }
        String table=BASENAME;
        if(first_char>='A' && first_char<'M')
        {
            table+=LEFT;
        }
        else
        {
            table+=RIGHT;
        }
        for(String p:permutation)
        {

            String t=table+p;
            String query="select * from "+t+" where "+t+".username='"+username+"'";
            System.out.println(query);
            try
            {
                ResultSet resultSet=statement.executeQuery(query);
                Customers customers=mapper(resultSet);
                if(customers!=null && customers.getUsername().equals(username))
                {
                    return customers;
                }
            }
            catch (Exception e)
            {

            }
        }
        return null;
    }
    public Customers findByEmail(String email)
    {
        return null;
    }
    public Customers findByPhone(String phone)
    {
        return null;
    }

}
