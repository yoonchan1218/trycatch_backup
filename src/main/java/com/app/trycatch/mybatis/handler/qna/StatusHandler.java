package com.app.trycatch.mybatis.handler.qna;

import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.common.enumeration.qna.QnaStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(QnaStatus.class)
public class StatusHandler implements TypeHandler<QnaStatus> {

    @Override
    public void setParameter(PreparedStatement ps, int i, QnaStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public QnaStatus getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "active":
                return QnaStatus.PUBLISHED;
            case "inactive":
                return QnaStatus.DELETED;
        }
        return null;
    }

    @Override
    public QnaStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "active":
                return QnaStatus.PUBLISHED;
            case "inactive":
                return QnaStatus.DELETED;
        }
        return null;
    }

    @Override
    public QnaStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "active":
                return QnaStatus.PUBLISHED;
            case "inactive":
                return QnaStatus.DELETED;
        }
        return null;
    }
}
