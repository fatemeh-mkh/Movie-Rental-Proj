import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import static java.awt.Color.RED;
import static java.awt.Color.white;

public class manager {

    public void manager_login() {
        Scanner inp = new Scanner(System.in);
        System.out.println("please enter your email : ");
        String email1 = inp.next();
        System.out.println("please enter your password : ");
        int password = inp.nextInt();
        String first_name;
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet = state.executeQuery("select * from manager where email='" + email1 + "'" + " and password='" + password + "'");


            if (resultSet.next()) {

                String email2 = resultSet.getString("email").trim();
                first_name = resultSet.getString("manager_first_name").trim();
                String manager_last_name = resultSet.getString("manager_last_name").trim();

                String output1 = " first_name : " + first_name + " || " + " manager_last_name : " + manager_last_name + " || " + " email : " + email2;
                graphic gr = new graphic();
                gr.graphicFunc(output1, RED);
                System.out.println(output1);

                System.out.println("your login is successfully :)");
            } else {
                System.out.println("this manager isn't available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("\n\n******please choose your desired option(1 or 2 or etc)******");
            System.out.println("1-customer_Information\n2-\n3-active_rental\n4-\n5-store_details and changes Information\n" +
                    "6-film genre\n7-film score\n8-search by actors\n9-search by genre\n10-search by films\n11-search by language" +
                    "\n12-search by year\n13-store_payment(with customers Information)\n14-store_payment(with film Information)\n15-)");
            int choose = inp.nextInt();

            switch (choose) {
                case 1:
                    System.out.println("Manager_id :");
                    int Manager_id = inp.nextInt();
                    customer_Information(Manager_id);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("---active_rentals---\n");
                    active_rental();
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("store_id :");
                    int store_id = inp.nextInt();
                    System.out.println("new_store_name :");
                    String store_name = inp.next();
                    store_details(store_name, store_id);
                    break;
                case 6:
                    System.out.println("genre :");
                    String genre = inp.next();
                    film_genre(genre);
                    break;
                case 7:
                    System.out.println("---10 best films related to their rating---\n");
                    film_score();
                    break;
                case 8:
                    System.out.println("actor_first_name :");
                    String actor_name = inp.next();
                    System.out.println("actor_last_name : ");
                    String actor_last_name = inp.next();
                    search_by_actors(actor_name, actor_last_name);
                    break;

                case 9:
                    System.out.println("genre :");
                    genre = inp.next();
                    search_by_genre(genre);
                    break;
                case 10:
                    System.out.println("title :");
                    String title = inp.nextLine();
                    while (inp.hasNext(title)) {
                        search_by_film(title);
                    }
                    break;

                case 11:
                    System.out.println("language :");
                    String language = inp.next();
                    search_language(language);
                    break;
                case 12:
                    System.out.println("year :");
                    int year = inp.nextInt();
                    search_by_year(year);
                    break;
                case 13:
                    System.out.println("store_id :");
                    int storeId = inp.nextInt();
                    payment_store_customer(storeId);
                    break;
                case 14:
                    System.out.println("store_id :");
                    storeId = inp.nextInt();
                    payment_store_film(storeId);
                    break;

            }
        }
    }

    public void customer_Information(int Manager_id) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet3 = state.executeQuery("select *" +
                    "from manager,store_table,customer where " +
                    "manager.manager_id=store_table.manager_staff_id and " +
                    "store_table.store_id=customer.store_id " +
                    " and manager_id= '" + Manager_id + "'");


            if (resultSet3.next()) {

                int customer_id = resultSet3.getInt("customer_id");
                int store_id = resultSet3.getInt("store_id");
                String first_name = resultSet3.getString("first_name").trim();
                String last_name = resultSet3.getString("last_name").trim();

                System.out.println(" first_name : " + first_name + " || " + " last_name : " + last_name + " || " + " store_id : " + store_id + " || " + " customer_id : " + customer_id);

            } else {
                System.out.println("No customer information available for this manager.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void active_rental() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet3 = state.executeQuery("select *" +
                    "from rental,customer where " +
                    "rental.customer_id=customer.customer_id and active= '" + 1 + "'"
            );


            if (resultSet3.next()) {

                while (resultSet3.next()) {
                    int rental_id = resultSet3.getInt("rental_id");
                    Date rental_date = resultSet3.getDate("rental_date");
                    Date return_date = resultSet3.getDate("return_date");
                    int customer_id = resultSet3.getInt("customer_id");
                    int active = resultSet3.getInt("active");
                    System.out.println("active :" + active + " || " + " rental_id : "
                            + rental_id + " || " + " rental_date : " +
                            rental_date + " || " + " return_date : " + return_date +
                            " || " + " customer_id : " + customer_id);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void store_details(String store_name, int store_id) {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            String q1 = "UPDATE store_table set store_name = '" + store_name +
                    "' WHERE store_id = '" + store_id + "'";
            int y = state.executeUpdate(q1);


            if (y > 0)
                System.out.println("store_name Successfully Updated");
            else
                System.out.println("ERROR OCCURRED :(");
            ResultSet resultSet3 = state.executeQuery("select *" +
                    "from store_table where " +
                    "store_id= '" + store_id + "'"
            );


            if (resultSet3.next()) {
                store_name = resultSet3.getString("store_name").trim();
                int manager_staff_id = resultSet3.getInt("manager_staff_id");
                int address_id = resultSet3.getInt("address_id");
                System.out.println("store_name :" + store_name + " || " + " manager_staff_id : "
                        + manager_staff_id + " || " + " address_id : " +
                        address_id);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void film_genre(String genre) {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet8 = state.executeQuery("select *" +
                    "from category join film_category on " +
                    " film_category.category_id=category.category_id " +
                    "join film on film.film_id=film_category.film_id " +
                    "where category.name= '" + genre + "'" +
                    " order by category.name");

            if (resultSet8.next()) {

                while (resultSet8.next()) {
                    String film_id = resultSet8.getString("film_id").trim();
                    int rental_rate = resultSet8.getInt("rental_rate");
                    genre = resultSet8.getString("name").trim();
                    String title = resultSet8.getString("title").trim();
                    System.out.println(" name : " + genre + " || " + " film_id : " + film_id + " || " + " rental_rate : " + rental_rate
                            + " || " + " title : " + title);
                }
            } else {
                System.out.println("this genre isn't available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void film_score() {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet9 = state.executeQuery("select * from film order by film.rental_rate desc limit 10");

            while (resultSet9.next()) {
                String film_id = resultSet9.getString("film_id").trim();
                String rental_rate = resultSet9.getString("rental_rate").trim();


                System.out.println(" film_id : " + film_id + " || " + " rental_rate : " + rental_rate);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search_by_actors(String actor_name, String actor_last_name) {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet2 = state.executeQuery("select * from actor where first_name='" + actor_name + "'" + "and last_name ='" + actor_last_name + "'");

            while (resultSet2.next()) {
                actor_name = resultSet2.getString("last_name").trim();
                actor_last_name = resultSet2.getString("first_name").trim();
                String actor_id = resultSet2.getString("actor_id").trim();

                System.out.println(" first_name : " + actor_name + " || " + " actor_last_name : " + actor_last_name + " || " + " actor_id : " + actor_id);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search_by_genre(String genre) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet3 = state.executeQuery("select *" +
                    "from film,category,film_category where " +
                    "film.film_id=film_category.film_id and " +
                    "film_category.category_id=category.category_id" +
                    " and name= '" + genre + "'");


            while (resultSet3.next()) {
                genre = resultSet3.getString("name").trim();
                String category_id = resultSet3.getString("category_id").trim();
                String title = resultSet3.getString("title").trim();
                String film_id = resultSet3.getString("film_id").trim();

                System.out.println(" name : " + genre + " || " + " category_id : " + category_id + " || " + " title : " + title + " || " + " film_id : " + film_id);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search_by_film(String title) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet4 = state.executeQuery("select *" +
                    "from film,category,film_category where " +
                    "film.film_id=film_category.film_id and " +
                    "film_category.category_id=category.category_id" +
                    " and name= '" + title + "'");


            while (resultSet4.next()) {

                title = resultSet4.getString("title").trim();
                String category_id = resultSet4.getString("category_id").trim();
                String film_id = resultSet4.getString("film_id").trim();
                System.out.println(" category_id : " + category_id + " || " + " title : " + title + " || " + " film_id : " + film_id);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search_language(String language) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet4 = state.executeQuery("select * from film,language where film.language_id = language.language_id and name = '" + language + "'");

            if (resultSet4.next()) {
                while (resultSet4.next()) {
                    language = resultSet4.getString("name").trim();
                    String title = resultSet4.getString("title").trim();
                    String film_id = resultSet4.getString("film_id").trim();
                    String description = resultSet4.getString("description").trim();
                    System.out.println(" language : " + language + " || " + " title : " + title + " || " + " film_id : " + film_id + " || " + " description : " + description);
                }
            } else {
                System.out.println("this film isn't available in this search ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search_by_year(int year) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet5 = state.executeQuery("select * from film where release_year = '" + year + "'");

            if (resultSet5.next()) {

                while (resultSet5.next()) {
                    year = resultSet5.getInt("release_year");
                    String film_id = resultSet5.getString("film_id").trim();
                    String description = resultSet5.getString("description").trim();
                    System.out.println(" release_year : " + year + " || " + " film_id : " + film_id + " || " + " description : " + description);
                }
            } else {
                System.out.println("this film isn't available in this search ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void payment_store_customer(int store_id) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet3 = state.executeQuery("select * " +
                    "from payment,store_table,customer " +
                    "where payment.customer_id =customer.customer_id and " +
                    "customer.store_id = store_table.store_id " +
                    "and store_table.store_id= '"+store_id+"'");

            if (resultSet3.next()) {

                while(resultSet3.next()){
                    int customer_id = resultSet3.getInt("customer_id");
                    String store_name = resultSet3.getString("store_name");
                    store_id = resultSet3.getInt("store_id");
                    int manager_staff_id = resultSet3.getInt("manager_staff_id");
                    String amount = resultSet3.getString("amount");
                    System.out.println(" customer_id : " + customer_id + " || " + " store_name : " + store_name + " || " + " amount : " + amount+ " || " + " manager_staff_id : " + manager_staff_id
                            + " || " + " store_id : " + store_id);

                }

            } else {
                System.out.println("No information found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void payment_store_film(int store_id) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet3 = state.executeQuery("select * " +
                    "from payment,customer,store_table,inventory,film " +
                    "where payment.customer_id=customer.customer_id and " +
                    "customer.store_id=store_table.store_id and " +
                    "inventory.film_id=film.film_id and " +
                    "store_table.store_id='"+store_id+"'");

            if (resultSet3.next()) {

                while(resultSet3.next()){
                    int customer_id = resultSet3.getInt("customer_id");
                    String store_name = resultSet3.getString("store_name");
                    store_id = resultSet3.getInt("store_id");
                    int manager_staff_id = resultSet3.getInt("manager_staff_id");
                    String amount = resultSet3.getString("amount");
                    System.out.println(" customer_id : " + customer_id + " || " + " store_name : " + store_name + " || " + " amount : " + amount+ " || " + " manager_staff_id : " + manager_staff_id
                            + " || " + " store_id : " + store_id);

                }

            } else {
                System.out.println("No information found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
