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
    public ArrayList<String> listCategoryWiseSchemes(int n) {
        ArrayList<String> schemeList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String list_schm = "SELECT * FROM need limit " + n;
            ResultSet resultSet = statement.executeQuery(list_schm);
            while(resultSet.next()) {
                String needname = resultSet.getString("needname");
                String desc = resultSet.getString("description");
                schemeList.add(needname + " - " + desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schemeList;
    }

    @Override
    public void addScheme(String category) throws SQLException {
        String insert_usr = "INSERT INTO need " + "(needname,description)" + " VALUES (?,?)";
        PreparedStatement prep = connection.prepareStatement(insert_usr);
        prep.setString(1,category);
        prep.setString(2,"this table displays the schemes relevant to " + category);
        if(prep.executeUpdate()==1){
            System.out.println("Data Inserted Successfully in Schemes table");
        }
        else{
            System.out.println("Failed to insert into Schemes table");
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
    public void deleteScheme(String scheme) throws SQLException {
        //Statement stmt = connection.createStatement();
        //int code = stmt.executeUpdate(del_schm);
        String del_schm = "DELETE FROM need" + " WHERE needname=?";
        PreparedStatement prep = connection.prepareStatement(del_schm);
        prep.setString(1,scheme);
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("Scheme details with name "+scheme+" deleted successfully");
        }
        else{
            System.out.println("Failed to delete record "+scheme+" from Scheme table");
        }
    }

    @Override
    public ArrayList<String> searchSchemes(String scheme) {
        //This method searches schemes opted by user on particular filters
        ArrayList<String> schmList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String list_schm = "SELECT DISTINCT SchemeName,description,beneficiary FROM " + scheme +
                                " s INNER JOIN user u WHERE " +
                                "s.role=u.role" + " AND " +
                                "s.gender=u.gender" + " AND " +
                                "s.age=u.age" + " AND " +
                                "s.maritalstatus=u.maritalstatus" + " AND " +
                                "s.category=u.category" + " AND " +
                                "s.highesteducation=u.highesteducation" + " AND " +
                                "s.employment=u.employment" + " AND " +
                                "s.annualincome=u.annualincome" + ";";
            ResultSet resultSet = statement.executeQuery(list_schm);
            while(resultSet.next()) {
                String scheme_name = resultSet.getString("SchemeName");
                String scheme_desc = resultSet.getString("description");
                String scheme_beneficiary = resultSet.getString("beneficiary");
                schmList.add(scheme_name + " - " + scheme_desc + " - "+ scheme_beneficiary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schmList;
    }
}
