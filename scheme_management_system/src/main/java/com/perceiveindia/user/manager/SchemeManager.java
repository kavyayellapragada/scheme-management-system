package com.perceiveindia.user.manager;

import com.perceiveindia.user.model.Scheme;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SchemeManager {
    void addScheme(String scheme_category) throws SQLException;
    void updateScheme(Scheme scheme, String scheme_category, int id) throws SQLException;
    ArrayList<String> listCategoryWiseSchemes(int n);
    void deleteScheme(String scheme_category) throws SQLException;
    ArrayList<String> searchSchemes(String scheme);
}
