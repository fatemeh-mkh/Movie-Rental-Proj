import java.awt.*;
import java.sql.*;
import java.util.Scanner;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class customer {

    public void login() {
        Scanner inp = new Scanner(System.in);
        System.out.println("please enter your email : ");
        String email1 = inp.next();
        System.out.println("please enter your password : ");
        String password = inp.next();
        String first_name;
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet = state.executeQuery("select * from customer where email='" + email1 + "'");


            if (resultSet.next()) {

                String email2 = resultSet.getString("email").trim();
                first_name = resultSet.getString("first_name").trim();

                String output1 = " first_name : " + first_name + " || " + " email : " + email2;
                //graphic gr = new graphic();
              //  gr.graphicFunc(output1, RED);
                System.out.println(" first_name : " + first_name + " || " + " email : " + email2);

                System.out.println("your login is successfully :)");

                //***********************************************************
                while (true) {
                    System.out.println("\n\n******please choose your desired option(1 or 2 or etc)******");
                    System.out.println("1-store details\n2-profile\n3-film genre\n4-film score" +
                            "\n5-search by actors\n6-search by genre\n7-search by films\n8-search by language\n9-search by year" +
                            "\n10-reserve list\n11-film_rental_details\n12-request_reserve\n13-rental_active\n14-pay_rental");

                    int choose = inp.nextInt();
                    switch (choose) {
                        case 1:
                            System.out.println("store_name:");
                            String store_name = inp.next();
                            store_details(store_name);
                            break;
                        case 2:

                            resultSet = state.executeQuery("select * from customer where email='" + email1 + "'");

                            if (resultSet.next()) {
                                email2 = resultSet.getString("email").trim();
                                first_name = resultSet.getString("first_name").trim();
                                String last_name = resultSet.getString("last_name").trim();
                                int customer_id = resultSet.getInt("customer_id");

                                System.out.println("\nCustomer_Informations\n");
                                System.out.println("first_name : " + first_name + " || " + " last_name : " + last_name + " || " + " email : " + email2 + " || " + " customer_id : " + customer_id);
                                System.out.println("which one do you want to change?(1 or 2 or etc)");
                                System.out.println("1-first_name\n2-last_name\n3-email");
                                int num2 = inp.nextInt();
                                switch (num2) {
                                    case 1:
                                        System.out.println("new_first_name :");
                                        String new_name = inp.next();
                                        edit_first_name(new_name, customer_id);
                                        String output2="first_name : " + new_name + " || " + " last_name : " + last_name + " || " + " email : " + email2 + " || " + " customer_id : " + customer_id;
//                                        System.out.println(output2);
//                                        gr.graphicFunc(output2,BLUE);

                                        break;
                                    case 2:
                                        System.out.println("new_last_name :");
                                        String new_last_name = inp.next();
                                        edit_last_name(new_last_name, customer_id);
                                        System.out.println("first_name : " + first_name + " || " + " last_name : " + new_last_name + " || " + " email : " + email2 + " || " + " customer_id : " + customer_id);

                                        break;
                                    case 3:
                                        System.out.println("new_email :");
                                        String new_email = inp.next();
                                        edit_email(new_email, customer_id);
                                        System.out.println("first_name : " + first_name + " || " + " last_name : " + last_name + " || " + " email : " + new_email + " || " + " customer_id : " + customer_id);

                                        break;

                                }

                            }

                            break;
                        case 3:
                            System.out.println("group by genre :");
                            String genre=inp.next();
                            film_genre(genre);

                            break;
                        case 4:
                            System.out.println("---10 best films related to their rating---\n");
                            film_score();

                            break;
                        case 5:
                            System.out.println("actor_first_name :");
                            String actor_name = inp.next();
                            System.out.println("actor_last_name : ");
                            String actor_last_name = inp.next();
                            search_by_actors(actor_name, actor_last_name);
                            break;
                        case 6:
                            System.out.println("genre :");
                            genre = inp.next();
                            search_by_genre(genre);
                            break;
                        case 7:
                            System.out.println("title :");
                            String title = inp.nextLine();
                            while (inp.hasNext(title)) {
                                search_by_film(title);
                            }
                            break;
                        case 8:
                            System.out.println("language :");
                            String language = inp.next();
                            search_language(language);
                            break;
                        case 9:
                            System.out.println("year :");
                            int year = inp.nextInt();
                            search_by_year(year);
                            break;
                        case 10:
                            System.out.println("customer_id :");
                            int customer_id = inp.nextInt();
                            reserve_list(customer_id);
                            break;
                        case 11:
                            System.out.println("enter your customer_id to request for rent film :");
                            customer_id=inp.nextInt();

                            break;
                        case 12:
                            break;
                        case 13:
                            break;
                        case 14:
                            System.out.println("customer_id :");
                            customer_id=inp.nextInt();
                            payment_details(customer_id);
                            break;

                    }

                }
            } else {
                System.out.println("Sorry !" +
                        "The user is not found !");
            }


            resultSet.close();
            state.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void edit_first_name(String first_name, int customer_id) {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            String q1 = "UPDATE customer set first_name = '" + first_name +
                    "' WHERE customer_id = '" + customer_id + "'";
            int x = state.executeUpdate(q1);

            if (x > 0)
                System.out.println("first_name Successfully Updated");
            else
                System.out.println("ERROR OCCURRED :(");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit_last_name(String last_name, int customer_id) {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            String q1 = "UPDATE customer set first_name = '" + last_name +
                    "' WHERE customer_id = '" + customer_id + "'";
            int x = state.executeUpdate(q1);

            if (x > 0)
                System.out.println("last_name Successfully Updated");
            else
                System.out.println("ERROR OCCURRED :(");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit_email(String email, int customer_id) {

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            String q1 = "UPDATE customer set first_name = '" + email +
                    "' WHERE customer_id = '" + customer_id + "'";
            int x = state.executeUpdate(q1);

            if (x > 0)
                System.out.println("email Successfully Updated");
            else
                System.out.println("ERROR OCCURRED :(");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void store_details(String store_name) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet3 = state.executeQuery("select * from store_table");

            if (resultSet3.next()) {
                store_name = resultSet3.getString("store_name").trim();
                int store_id = resultSet3.getInt("store_id");
                int address_id = resultSet3.getInt("address_id");

                System.out.println(" store_name : " + store_name + " || " + " store_id : " + store_id + " || " + " address_id : " + address_id);

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
    public void reserve_list(int customer_id) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet6 = state.executeQuery("select * from film,inventory,rental where inventory.inventory_id=rental.inventory_id" +
                    " and inventory.film_id=film.film_id " +
                    "and return_date<= Now() and customer_id= '" + customer_id + "'");

            if (resultSet6.next()) {

                while (resultSet6.next()) {
                    String film_id = resultSet6.getString("film_id").trim();
                    customer_id = resultSet6.getInt("customer_id");
                    int rental_id = resultSet6.getInt("rental_id");
                    Date rental_date = resultSet6.getDate("rental_date");
                    Date return_date = resultSet6.getDate("return_date");
                    System.out.println(" customer_id : " + customer_id + " || " + " film_id : " + film_id + " || " + " rental_id : " + rental_id
                            + " || " + " rental_date : " + rental_date
                            + " || " + " return_date : " + return_date);
                }
            } else {
                System.out.println("this customer hasn't reserved film.");
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
//    public void reserve_request(String customer_id){
//        try
//        {
//            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
//            Statement state = connect.createStatement(); //make obj
//            state.executeUpdate("INSERT INTO rental (requested_Info,) "
//                    +"VALUES ('requested')");
//        }
//        catch (Exception e)
//        {
//            System.err.println("Got an exception!");
//            System.err.println(e.getMessage());
//        }
//
//       }

    public void payment_details(int customer_id) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "popoyaya@2751");
            Statement state = connect.createStatement(); //make obj
            ResultSet resultSet3 = state.executeQuery("select * from payment where customer_id = '" + customer_id+"'");

            if (resultSet3.next()) {
                while(resultSet3.next()){
                    String payment_id = resultSet3.getString("payment_id").trim();
                    customer_id = resultSet3.getInt("customer_id");
                    String amount = resultSet3.getString("amount");
                    Date payment_date=resultSet3.getDate("payment_date");

                    System.out.println(" customer_id : " + customer_id + " || " + " payment_id : " + payment_id + " || " + " amount : " + amount+ " || " + " payment_date : " + payment_date);

                }

            }
            else {
                System.out.println("customer_id dose not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


