package com.app.trycatch.mybatis.handler;


import com.app.trycatch.common.enumeration.experience.ApplyStatus;
import com.app.trycatch.common.enumeration.member.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ApplyStatus.class)
public class ApplyStatusHandler implements TypeHandler<ApplyStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, ApplyStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public ApplyStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "applied":
                return ApplyStatus.APPLIED;
            case "document_pass":
                return ApplyStatus.DOCUMENT_PASS;
            case "document_fail":
                return ApplyStatus.DOCUMENT_FAIL;
            case "cancelled":
                return ApplyStatus.CANCELLED;
        }
        return null;
    }

    @Override
    public ApplyStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "applied":
                return ApplyStatus.APPLIED;
            case "document_pass":
                return ApplyStatus.DOCUMENT_PASS;
            case "document_fail":
                return ApplyStatus.DOCUMENT_FAIL;
            case "cancelled":
                return ApplyStatus.CANCELLED;
        }
        return null;
    }

    @Override
    public ApplyStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "applied":
                return ApplyStatus.APPLIED;
            case "document_pass":
                return ApplyStatus.DOCUMENT_PASS;
            case "document_fail":
                return ApplyStatus.DOCUMENT_FAIL;
            case "cancelled":
                return ApplyStatus.CANCELLED;
        }
        return null;
    }
}
