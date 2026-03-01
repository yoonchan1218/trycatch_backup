package com.app.trycatch.mybatis.handler;


import com.app.trycatch.common.enumeration.report.ReportStatus;
import com.app.trycatch.common.enumeration.skillLog.SkillLogReportTargetType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ReportStatus.class)
public class SkillLogReportTargetTypeHandler implements TypeHandler<SkillLogReportTargetType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SkillLogReportTargetType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public SkillLogReportTargetType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "log":
                return SkillLogReportTargetType.LOG;
            case "comment":
                return SkillLogReportTargetType.COMMENT;
        }
        return null;
    }

    @Override
    public SkillLogReportTargetType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "log":
                return SkillLogReportTargetType.LOG;
            case "comment":
                return SkillLogReportTargetType.COMMENT;
        }
        return null;
    }

    @Override
    public SkillLogReportTargetType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "log":
                return SkillLogReportTargetType.LOG;
            case "comment":
                return SkillLogReportTargetType.COMMENT;
        }
        return null;
    }
}
