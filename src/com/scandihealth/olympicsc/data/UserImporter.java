package com.scandihealth.olympicsc.data;

import com.scandihealth.olympicsc.user.model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 30-04-2010
 * Time: 15:33:26
 * To change this template use File | Settings | File Templates.
 */
public class UserImporter {
    private List<DataUser> users = new ArrayList<DataUser>();
    private static final String ae = "æ";
    private static final String oe = "ø";
    private static final String aa = "å";
    private static final String AE = "Æ";
    private static final String OE = "Ø|";
    private static final String AA = "å";
    private static final String eping = "é";
    private static final String oumlaut = "ö";


    public static void main(String[] args) {
        UserImporter userImporter = new UserImporter();
        userImporter.loadData();
//        userImporter.saveData();
        userImporter.deleteData();
    }

    private void deleteData() {
        DataManager dataManager = new DataManager();
        for (DataUser datauser : users) {
            User user = new User();
            user.setFirstname(datauser.firstname);
            user.setLastname(datauser.lastname);
            user.setUserName(datauser.username);
            user.setEmployeeId(datauser.personelnumber);
            user.setMail(datauser.mail);
            user.setPhone(datauser.phone);
            user.setShirtsize("");

            dataManager.deleteUser(user);
        }

    }

    private void saveData() {
        DataManager dataManager = new DataManager();
        for (DataUser datauser : users) {
            User user = new User();
            user.setFirstname(datauser.firstname);
            user.setLastname(datauser.lastname);
            user.setUserName(datauser.username);
            user.setEmployeeId(datauser.personelnumber);
            user.setMail(datauser.mail);
            user.setPhone(datauser.phone);
            user.setPassword(datauser.password);
            user.setShirtsize("");
            user.setFirstlogin(true);
            if (datauser.username.equalsIgnoreCase("ljd") ||
                    datauser.username.equalsIgnoreCase("bpj") ||
                    datauser.username.equalsIgnoreCase("rni") ||
                    datauser.username.equalsIgnoreCase("aha") ||
                    datauser.username.equalsIgnoreCase("mah")) {
                user.setAdmin(true);
            }

            dataManager.saveUser(user);
        }
    }

    private void loadData() {
        File dataFile = new File("f:/projects/programming/schema/trunk/users.txt");

        try {
            BufferedReader fh = new BufferedReader(new FileReader(dataFile));
            String s;
            while ((s = fh.readLine()) != null) {
                String f[] = s.split("\t");
                DataUser dataUser = new DataUser();
                String name = f[0];
                String[] names = name.split(" ");
                dataUser.firstname = names[0];


                names[0] = null;
                for (String name1 : names) {
                    if (name1 != null) {
                        if (dataUser.lastname == null || "".equals(dataUser.lastname)) {
                            dataUser.lastname = name1;
                        } else {
                            dataUser.lastname += " " + name1;
                        }
                    }
                }


                dataUser.username = f[1];
                dataUser.personelnumber = f[2];
                dataUser.mail = f[3];
                dataUser.phone = f[4];
                dataUser.password = f[5];
                dataUser = convertDanishChars(dataUser);
                users.add(dataUser);
            }
            fh.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataUser convertDanishChars(DataUser dataUser) {


        if (dataUser.firstname.contains(ae)) {
            dataUser.firstname = dataUser.firstname.replace(ae, "�");
        }
        if (dataUser.firstname.contains(oe)) {
            dataUser.firstname = dataUser.firstname.replace(oe, "�");
        }
        if (dataUser.firstname.contains(aa)) {
            dataUser.firstname = dataUser.firstname.replace(aa, "�");
        }
        if (dataUser.firstname.contains(eping)) {
            dataUser.firstname = dataUser.firstname.replace(eping, "�");
        }
        if (dataUser.firstname.contains(eping)) {
            dataUser.firstname = dataUser.firstname.replace(oumlaut, "�");
        }
        if (dataUser.firstname.contains(AE)) {
            dataUser.firstname = dataUser.firstname.replace(AE, "�");
        }
        if (dataUser.firstname.contains(OE)) {
            dataUser.firstname = dataUser.firstname.replace(OE, "�");
        }
//        if (dataUser.firstname.contains(OE)) {
//            dataUser.firstname = dataUser.firstname.replace(AA, "�");
//        }

        if (dataUser.lastname.contains(ae)) {
            dataUser.lastname = dataUser.lastname.replace(ae, "�");
        }
        if (dataUser.lastname.contains(oe)) {
            dataUser.lastname = dataUser.lastname.replace(oe, "�");
        }
        if (dataUser.lastname.contains(aa)) {
            dataUser.lastname = dataUser.lastname.replace(aa, "�");
        }
        if (dataUser.lastname.contains(eping)) {
            dataUser.lastname = dataUser.lastname.replace(eping, "�");
        }
        if (dataUser.lastname.contains(oumlaut)) {
            dataUser.lastname = dataUser.lastname.replace(oumlaut, "�");
        }
        if (dataUser.lastname.contains(AE)) {
            dataUser.lastname = dataUser.lastname.replace(AE, "�");
        }
        if (dataUser.lastname.contains(OE)) {
            dataUser.lastname = dataUser.lastname.replace(oe, "�");
        }
//        if (dataUser.lastname.contains(OE)) {
//            dataUser.lastname = dataUser.lastname.replace(aa, "�");
//        }
        return dataUser;
    }

    class DataUser {
        private String firstname;
        private String lastname;

        private String username;
        private String personelnumber;
        private String mail;
        private String phone;
        private String password;

        @Override
        public String toString() {
            return "DataUser{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", username='" + username + '\'' +
                    ", personelnumber='" + personelnumber + '\'' +
                    ", mail='" + mail + '\'' +
                    ", phone='" + phone + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

}

