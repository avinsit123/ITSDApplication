package com.io.itsd;

import org.springframework.stereotype.Component;

@Component
public class HibernateQueryBuilder {


    private String hqlQuery;

    public HibernateQueryBuilder() {
        hqlQuery = "";
    }

    public HibernateQueryBuilder setTableName(String tableName) {
        hqlQuery += "from " + tableName + " S";
        return this;
    }

    public HibernateQueryBuilder addEqualityFilter(String columnName, int cutoffValue) {
        hqlQuery += ( hqlQuery.contains("WHERE") ? " AND S." : " WHERE S." )
                + columnName + " = " + cutoffValue;
        return this;
    }

    public HibernateQueryBuilder addEqualityFilter(String columnName, String cutoffValue) {
        hqlQuery += ( hqlQuery.contains("WHERE") ? " AND S." : " WHERE S." )
                + columnName + " = '" + cutoffValue + "'";
        return this;
    }

    public HibernateQueryBuilder addOrderBy(String columnName, boolean asc ) {
        hqlQuery += ( hqlQuery.contains("ORDER BY") ? ", S." : " ORDER BY S." )
                + columnName + ( asc ? " ASC" : "DESC");
        return this;
    }

    public String returnHqlQuery() {
        return hqlQuery;
    }

    public void flush() {
        hqlQuery = "";
    }

}
