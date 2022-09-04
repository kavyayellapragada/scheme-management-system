package com.perceiveindia.user.manager.impl;

import com.perceiveindia.user.connection.MySQLConnectionUtility;
import com.perceiveindia.user.manager.UserManager;
import com.perceiveindia.user.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagerImpl implements UserManager {
    Connection connection = MySQLConnectionUtility.getConnection();

    public UserManagerImpl() throws SQLException {
    }

    @Override
    public List<User> listUsers(int n) {
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String list_user = "SELECT * FROM user limit " + n;
            ResultSet resultSet = statement.executeQuery(list_user);
            while(resultSet.next()) {
                String role = resultSet.getString("role");
                String gender = resultSet.getString("gender");
                String age = resultSet.getString("age");
                String marital_status = resultSet.getString("maritalstatus");
                String category = resultSet.getString("category");
                String education = resultSet.getString("highesteducation");
                String employement = resultSet.getString("employment");
                String annual_income = resultSet.getString("annualincome");
                String scheme = resultSet.getString("need");
                userList.add(new User(role, gender, age, marital_status, category, education,
                        employement, annual_income, scheme));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void addUser(User user) throws SQLException {
        String insert_usr = "INSERT INTO user(gender,age,maritalstatus,category,highesteducation," +
                "employment,annualincome,need,role) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement prep = connection.prepareStatement(insert_usr);
        prep.setString(1,user.getGender());
        prep.setString(2,user.getAge());
        prep.setString(3,user.getMaritalStatus());
        prep.setString(4,user.getCategory());
        prep.setString(5,user.getEducation());
        prep.setString(6,user.getEmployement());
        prep.setString(7,user.getAnnualIncome());
        prep.setString(8,user.getScheme());
        prep.setString(9,user.getRole());
        if(prep.executeUpdate()==1){
            System.out.println("Data Inserted Successfully in User table");
        }
        else{
            System.out.println("Failed to insert into User table");
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String update_usr = "UPDATE user SET gender=?,age=?,maritalstatus=?,category=?,highesteducation=?," +
                "employment=?,annualincome=?,need=?,role=?";
        PreparedStatement prep = connection.prepareStatement(update_usr);
        prep.setString(1,user.getGender());
        prep.setString(2,user.getAge());
        prep.setString(3,user.getMaritalStatus());
        prep.setString(4,user.getCategory());
        prep.setString(5,user.getEducation());
        prep.setString(6,user.getEmployement());
        prep.setString(7,user.getAnnualIncome());
        prep.setString(8,user.getScheme());
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("Employee details updated successfully");
        }
        else{
            System.out.println("Failed to update employee details");
        }
    }

    @Override
    public void deleteUser(int ID) throws SQLException {
        //Statement stmt = connection.createStatement();
        //String S_ID = String.valueOf(ID);
        //int code = stmt.executeUpdate(del_usr);
        String del_usr = "DELETE FROM user WHERE user_id=?";
        PreparedStatement prep = connection.prepareStatement(del_usr);
        prep.setInt(1,ID);
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("User details with ID "+ID+" deleted successfully");
        }
        else{
            System.out.println("Failed to delete record "+ID+" from User table");
        }
    }

    @Override
    public List<User> searchUsers(String keyword) {
        //This method searches users who opted for a particular scheme
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String list_user = "SELECT * FROM user WHERE need=" + keyword;
            ResultSet resultSet = statement.executeQuery(list_user);
            while(resultSet.next()) {
                String role = "user";
                String gender = resultSet.getString("gender");
                String age = resultSet.getString("age");
                String marital_status = resultSet.getString("maritalstatus");
                String category = resultSet.getString("category");
                String education = resultSet.getString("highesteducation");
                String employement = resultSet.getString("employment");
                String annual_income = resultSet.getString("annualincome");
                String scheme = resultSet.getString("need");
                userList.add(new User(role, gender, age, marital_status, category, education,
                        employement, annual_income, scheme));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
