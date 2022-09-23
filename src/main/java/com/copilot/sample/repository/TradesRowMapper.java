package com.copilot.sample.repository;

//rowmapper
import com.copilot.sample.model.Trades;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradesRowMapper implements RowMapper<Trades> {
    @Override
    public Trades mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trades trades = new Trades();
        trades.setTransaction(rs.getString("transactionType"));
        trades.setTradeId(rs.getString("tradeId"));
        trades.setBuyer(rs.getString("buyer"));
        trades.setSeller(rs.getString("seller"));
        trades.setAmount(rs.getString("amount"));
        trades.setCurrency(rs.getString("currency"));
        trades.setStatus(rs.getString("status"));
        trades.setTradeDate(rs.getString("tradeDate"));
        return trades;
    }
}