package com.app.trycatch.mybatis.handler;


import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.common.enumeration.report.ReportStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ReportStatus.class)
public class ReportStatusHandler implements TypeHandler<ReportStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, ReportStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public ReportStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "pending":
                return ReportStatus.PENDING;
            case "reviewing":
                return ReportStatus.REVIEWING;
            case "processed":
                return ReportStatus.PROCESSED;
            case "rejected":
                return ReportStatus.REJECTED;
        }
        return null;
    }

    @Override
    public ReportStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "pending":
                return ReportStatus.PENDING;
            case "reviewing":
                return ReportStatus.REVIEWING;
            case "processed":
                return ReportStatus.PROCESSED;
            case "rejected":
                return ReportStatus.REJECTED;
        }
        return null;
    }

    @Override
    public ReportStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "pending":
                return ReportStatus.PENDING;
            case "reviewing":
                return ReportStatus.REVIEWING;
            case "processed":
                return ReportStatus.PROCESSED;
            case "rejected":
                return ReportStatus.REJECTED;
        }
        return null;
    }
}
