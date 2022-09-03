package com.perceiveindia.user.manager;

import com.perceiveindia.user.model.Scheme;

import java.sql.SQLException;
import java.util.List;

public interface SchemeManager {
    void addScheme(Scheme scheme1, String scheme_category) throws SQLException;
    void updateScheme(Scheme scheme, String scheme_category, int id) throws SQLException;
    List<Scheme> listCategoryWiseSchemes(String scheme_category);
    void deleteScheme(int id, String scheme_category) throws SQLException;
    List<Scheme> searchSchemes(String keyword, String scheme_category);
}
