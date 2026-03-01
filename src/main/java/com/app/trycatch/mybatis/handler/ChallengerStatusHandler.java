package com.app.trycatch.mybatis.handler;


import com.app.trycatch.common.enumeration.experience.ApplyStatus;
import com.app.trycatch.common.enumeration.experience.ChallengerStatus;
import com.app.trycatch.common.enumeration.member.Status;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ChallengerStatus.class)
public class ChallengerStatusHandler implements TypeHandler<ChallengerStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, ChallengerStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public ChallengerStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "in_progress":
                return ChallengerStatus.IN_PROGRESS;
            case "completed":
                return ChallengerStatus.COMPLETED;
            case "out_of_process":
                return ChallengerStatus.OUT_OF_PROCESS;
            case "step_failed":
                return ChallengerStatus.STEP_FAILED;
        }
        return null;
    }

    @Override
    public ChallengerStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "in_progress":
                return ChallengerStatus.IN_PROGRESS;
            case "completed":
                return ChallengerStatus.COMPLETED;
            case "out_of_process":
                return ChallengerStatus.OUT_OF_PROCESS;
            case "step_failed":
                return ChallengerStatus.STEP_FAILED;
        }
        return null;
    }

    @Override
    public ChallengerStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "in_progress":
                return ChallengerStatus.IN_PROGRESS;
            case "completed":
                return ChallengerStatus.COMPLETED;
            case "out_of_process":
                return ChallengerStatus.OUT_OF_PROCESS;
            case "step_failed":
                return ChallengerStatus.STEP_FAILED;
        }
        return null;
    }
}
