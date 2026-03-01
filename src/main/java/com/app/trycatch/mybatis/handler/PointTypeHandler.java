package com.app.trycatch.mybatis.handler;


import com.app.trycatch.common.enumeration.point.PointType;
import com.app.trycatch.common.enumeration.report.ReportStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(PointType.class)
public class PointTypeHandler implements TypeHandler<PointType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, PointType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public PointType getResult(ResultSet rs, String columnName) throws SQLException {
        switch (rs.getString(columnName)) {
            case "earn":
                return PointType.EARN;
            case "use":
                return PointType.USE;
            case "expire":
                return PointType.EXPIRE;
            case "purchase_cancel":
                return PointType.PURCHASE_CANCEL;
        }
        return null;
    }

    @Override
    public PointType getResult(ResultSet rs, int columnIndex) throws SQLException {
        switch (rs.getString(columnIndex)) {
            case "earn":
                return PointType.EARN;
            case "use":
                return PointType.USE;
            case "expire":
                return PointType.EXPIRE;
            case "purchase_cancel":
                return PointType.PURCHASE_CANCEL;
        }
        return null;
    }

    @Override
    public PointType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        switch (cs.getString(columnIndex)) {
            case "earn":
                return PointType.EARN;
            case "use":
                return PointType.USE;
            case "expire":
                return PointType.EXPIRE;
            case "purchase_cancel":
                return PointType.PURCHASE_CANCEL;
        }
        return null;
       }
}
