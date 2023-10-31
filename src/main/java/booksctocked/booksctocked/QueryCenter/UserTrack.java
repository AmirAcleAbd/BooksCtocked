package booksctocked.booksctocked.QueryCenter;
import java.io.*;
import java.util.ArrayList;

public class UserTrack implements Serializable {

    private static UserTrack instance = null;

    private ArrayList<User> userList = new ArrayList<>();


    private UserTrack() {
    }

    public static UserTrack getInstance() {
        if (instance == null) {
            try {
                FileInputStream fis = new FileInputStream("target/CrystalPalace/users.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                instance = (UserTrack) ois.readObject();
                fis.close();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                instance = new UserTrack();
            }
        }
        return instance;
    }

    public void saveInstance() {
        try {
            FileOutputStream fos = new FileOutputStream("target/CrystalPalace/users.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public boolean userExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                System.out.println("User exists");
                return true;
            }
        }
        System.out.println("User does not exist");
        return false;
    }

    public User getUser(User user) {
        for (User u : userList) {
            if (u.equals(user)) {
                return u;
            }
        }
        return null;
    }

    public boolean userLoginValidation(String username, String password) {
        if(!userExists(username)){
            return false;
        }
        User user = new User(username, password);
        if (getUser(user) == null) {
            return false;
        }
        return true;

    }

    public boolean userLoginValidation(User user) {
        return userList.contains(user);
    }

    public void addUser(User user) {
        if (userLoginValidation(user)) {
            return;
        }
        userList.add(user);
    }

}