package com.app.trycatch.mybatis.handler;

import com.app.trycatch.common.enumeration.experience.ExperienceProgramStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ExperienceProgramStatus.class)
public class ExperienceProgramStatusHandler implements TypeHandler<ExperienceProgramStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, ExperienceProgramStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public ExperienceProgramStatus getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) return null;
        switch (value) {
            case "draft":     return ExperienceProgramStatus.DRAFT;
            case "recruiting": return ExperienceProgramStatus.RECRUITING;
            case "closed":    return ExperienceProgramStatus.CLOSED;
            case "cancelled": return ExperienceProgramStatus.CANCELLED;
        }
        return null;
    }

    @Override
    public ExperienceProgramStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) return null;
        switch (value) {
            case "draft":     return ExperienceProgramStatus.DRAFT;
            case "recruiting": return ExperienceProgramStatus.RECRUITING;
            case "closed":    return ExperienceProgramStatus.CLOSED;
            case "cancelled": return ExperienceProgramStatus.CANCELLED;
        }
        return null;
    }

    @Override
    public ExperienceProgramStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) return null;
        switch (value) {
            case "draft":     return ExperienceProgramStatus.DRAFT;
            case "recruiting": return ExperienceProgramStatus.RECRUITING;
            case "closed":    return ExperienceProgramStatus.CLOSED;
            case "cancelled": return ExperienceProgramStatus.CANCELLED;
        }
        return null;
    }
}
