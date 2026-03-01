package com.app.trycatch.mybatis.handler;


import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Gender.class)
public class GenderHandler implements TypeHandler<Gender> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Gender parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public Gender getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) return null;
        switch (value) {
            case "man":
                return Gender.MAN;
            case "women":
                return Gender.WOMEN;
        }
        return null;
    }

    @Override
    public Gender getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) return null;
        switch (value) {
            case "man":
                return Gender.MAN;
            case "women":
                return Gender.WOMEN;
        }
        return null;
    }

    @Override
    public Gender getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) return null;
        switch (value) {
            case "man":
                return Gender.MAN;
            case "women":
                return Gender.WOMEN;
        }
        return null;
    }
}
