package booksctocked.booksctocked.QueryCenter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean admin = false;
    private ArrayList<Book> borrowed = new ArrayList<>();
    private HashMap<Book, Integer> transactions = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean borrowedBook(Book book){
        for(Book b: borrowed){
            if(b.equals(book)){
                return true;
            }
        }
        return false;
    }

    public void borrowBook(Book book){
        if(!borrowedBook(book)){
            borrowed.add(book);
            transactions.put(book, transactions.getOrDefault(book, 0) + 1);
            book.setCopyNum(book.getCopyNum() - 1);
        }else{
            System.out.println("book already borrowed, check yo code");
        }
    }

    public void returnBook(Book book){
        if(borrowedBook(book)){
            borrowed.remove(book);
            transactions.put(book, transactions.getOrDefault(book, 0) + 1);
            book.setCopyNum(book.getCopyNum() + 1);
        }else{
            System.out.println("book already returned, check yo code");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return username.equals(user.username) && password.equals(user.password);
    }

    public ArrayList<Book> getBorrowed() {
        return borrowed;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}