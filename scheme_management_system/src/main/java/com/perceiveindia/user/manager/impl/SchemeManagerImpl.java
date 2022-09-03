package com.perceiveindia.user.manager.impl;

import com.perceiveindia.user.connection.MySQLConnectionUtility;
import com.perceiveindia.user.manager.SchemeManager;
import com.perceiveindia.user.model.Scheme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchemeManagerImpl implements SchemeManager {
    Connection connection = MySQLConnectionUtility.getConnection();

    public SchemeManagerImpl() throws SQLException {
    }

    @Override
    public List<Scheme> listCategoryWiseSchemes(String category_name) {
        List<Scheme> schemeList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String list_schm = "SELECT * FROM "+category_name;
            ResultSet resultSet = statement.executeQuery(list_schm);
            while(resultSet.next()) {
                String role = "user";
                String gender = resultSet.getString("gender");
                String age = resultSet.getString("age");
                String marital_status = resultSet.getString("maritalstatus");
                String category = resultSet.getString("category");
                String education = resultSet.getString("highesteducation");
                String employement = resultSet.getString("employment");
                String annual_income = resultSet.getString("annualincome");
                String scheme = resultSet.getString("SchemeName");
                String desc = resultSet.getString("description");
                String beneficiary = resultSet.getString("beneficiary");
                schemeList.add(new Scheme(role, gender, age, marital_status, category, education,
                        employement, annual_income, scheme, desc, beneficiary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schemeList;
    }

    @Override
    public void addScheme(Scheme scheme, String category) throws SQLException {
        String insert_usr = "INSERT INTO " + category +
                "(gender,age,maritalstatus,category,highesteducation,employment,annualincome,SchemeName," +
                "description,beneficiary,role)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prep = connection.prepareStatement(insert_usr);
        prep.setString(1,scheme.getGender());
        prep.setString(2,scheme.getAge());
        prep.setString(3,scheme.getMaritalStatus());
        prep.setString(4,scheme.getCategory());
        prep.setString(5,scheme.getEducation());
        prep.setString(6,scheme.getEmployement());
        prep.setString(7,scheme.getAnnualIncome());
        prep.setString(8,scheme.getSchemeName());
        prep.setString(9,scheme.getSchemeDescription());
        prep.setString(10,scheme.getBeneficiary());
        prep.setString(11,scheme.getRole());
        if(prep.executeUpdate()==1){
            System.out.println("Data Inserted Successfully in Scheme table");
        }
        else{
            System.out.println("Failed to insert into Scheme table");
        }
    }

    @Override
    public void updateScheme(Scheme scheme, String category, int id) throws SQLException {
        String update_usr = "UPDATE " + category + " SET gender=?,age=?,maritalstatus=?,category=?," +
                "highesteducation=?,employment=?,annualincome=?,SchemeName=?,description=?," +
                "beneficiary=?,role=? WHERE id="+id;
        PreparedStatement prep = connection.prepareStatement(update_usr);
        prep.setString(1,scheme.getGender());
        prep.setString(2,scheme.getAge());
        prep.setString(3,scheme.getMaritalStatus());
        prep.setString(4,scheme.getCategory());
        prep.setString(5,scheme.getEducation());
        prep.setString(6,scheme.getEmployement());
        prep.setString(7,scheme.getAnnualIncome());
        prep.setString(8,scheme.getSchemeName());
        prep.setString(9,scheme.getSchemeDescription());
        prep.setString(10,scheme.getBeneficiary());
        prep.setString(11,scheme.getRole());
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("Scheme details updated successfully");
        }
        else{
            System.out.println("Failed to update scheme details");
        }
    }

    @Override
    public void deleteScheme(int ID, String category) throws SQLException {
        Statement stmt = connection.createStatement();
        String S_ID = String.valueOf(ID);
        String del_schm = "DELETE FROM " + category + " WHERE id="+S_ID;
        int code = stmt.executeUpdate(del_schm);
        if(code == 1){
            System.out.println("Scheme details with ID "+S_ID+" deleted successfully");
        }
        else{
            System.out.println("Failed to delete record "+S_ID+" from Scheme table");
        }
    }

    @Override
    public List<Scheme> searchSchemes(String keyword, String category_name) {
        //This method searches users who opted for a particular scheme
        List<Scheme> schmList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String list_schm = "SELECT * FROM " + category_name + " s INNER JOIN user u" +
                    " on u." + keyword + " =s." + keyword;
            ResultSet resultSet = statement.executeQuery(list_schm);
            while(resultSet.next()) {
                String role = "user";
                String gender = resultSet.getString("gender");
                String age = resultSet.getString("age");
                String marital_status = resultSet.getString("maritalstatus");
                String category = resultSet.getString("category");
                String education = resultSet.getString("highesteducation");
                String employement = resultSet.getString("employment");
                String annual_income = resultSet.getString("annualincome");
                String scheme = resultSet.getString("SchemeName");
                String desc = resultSet.getString("description");
                String beneficiary = resultSet.getString("beneficiary");
                schmList.add(new Scheme(role, gender, age, marital_status, category, education,
                        employement, annual_income, scheme, desc, beneficiary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schmList;
    }
}
