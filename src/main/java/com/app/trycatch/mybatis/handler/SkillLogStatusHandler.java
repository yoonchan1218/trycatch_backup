package com.app.trycatch.mybatis.handler;

import com.app.trycatch.common.enumeration.skillLog.SkillLogStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SkillLogStatus.class)
public class SkillLogStatusHandler implements TypeHandler<SkillLogStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SkillLogStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public SkillLogStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "published":
                return SkillLogStatus.PUBLISHED;
            case "deleted":
                return SkillLogStatus.DELETED;
        }
        return null;
    }

    @Override
    public SkillLogStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "published":
                return SkillLogStatus.PUBLISHED;
            case "deleted":
                return SkillLogStatus.DELETED;
        }
        return null;
    }

    @Override
    public SkillLogStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "published":
                return SkillLogStatus.PUBLISHED;
            case "deleted":
                return SkillLogStatus.DELETED;
        }
        return null;
    }
}
