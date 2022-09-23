package com.copilot.sample.repository;

import com.copilot.sample.exception.TransactionException;
import com.copilot.sample.model.Trades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//Add repository configuration to TradesDaoImpl class
@Repository
//implement the interface tradesdao
public class TradesDaoImpl implements TradesDao {
    //Autowire JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //Add custom query to find all trades
    @Override
    public List<Trades> findAll() {
        return jdbcTemplate.query("select * from trades", new TradesRowMapper());
    }
    //Add custom query to find trades by id
    @Override
    public Trades findById(String tradesId) {
        return jdbcTemplate.queryForObject("select * from trades where id = ?",
                new Object[]{tradesId}, new TradesRowMapper());
    }
    //Add custom query to create a new trade
    @Override
    public Trades create(Trades trades) throws TransactionException {
        System.out.println("create" + trades.getAmount());

        //check if the trade is already present with status as EXIT using resultsetextractor
        Trades trades1 = jdbcTemplate.query("select * from trades where tradeId = ? and transactionType = ?",
                new Object[]{trades.getTradeId(), "EXIT"}, rs -> {
                    if (rs.next()) {
                        Trades trades2 = new Trades();
                        trades2.setTransaction(rs.getString("transactionType"));
                        trades2.setTradeId(rs.getString("tradeId"));
                        trades2.setBuyer(rs.getString("buyer"));
                        trades2.setSeller(rs.getString("seller"));
                        trades2.setAmount(rs.getString("amount"));
                        trades2.setCurrency(rs.getString("currency"));
                        trades2.setStatus(rs.getString("status"));
                        trades2.setTradeDate(rs.getString("tradeDate"));
                        return trades2;
                    }
                    return null;
                }); //end of resultsetextractor

        //if the trade is present with status as EXIT, throw an exception with message as TRADE is EXITED.
        if(trades1 != null) {
            throw new TransactionException("TRADE is EXITED.");
        }
        // get the transactiontype from the table
        String transactionType = jdbcTemplate.query("select transactionType from trades where tradeId = ? and transactionType = ?",
                new Object[]{trades.getTradeId(), "NEW-TRADE"},
                //add resultsetextractor to get the transactiontype
                new ResultSetExtractor<String>() {
                    @Override
                    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            return rs.getString("transactionType");
                        }
                        return null;
                    }
                }); //end of resultsetextractor

        //if the transaction type is NEW-TRADE and equals to the trade object transaction type, throw an exception with message as TRADE is already present.
       if(transactionType != null && transactionType.equals( trades.getTransaction())) {
            throw new TransactionException("TRADE is already present.");
        }

        //Add custom query to create a new trade
        jdbcTemplate.update("insert into trades (transactionType, tradeId, buyer, seller, amount, currency, tradeDate, status) values(?,?,?,?,?,?,?,?)",
                trades.getTransaction(), trades.getTradeId(), trades.getBuyer(), trades.getSeller(), trades.getAmount(), trades.getCurrency(), trades.getTradeDate(), "ACTIVE");
        return trades;
    }
    //Add custom query to update a trade
    @Override
    public Trades update(Trades trades) {
        jdbcTemplate.update("update trades set transactionType = ?, amount = ?, currency = ? where tradeId = ?",
                trades.getTransaction(), trades.getAmount(), trades.getCurrency(), trades.getTradeId());
        return trades;
    }
    //Add custom query to delete a trade
    @Override
    public void delete(String tradesId) {
        jdbcTemplate.update("delete from trades where tradeId = ?", tradesId);
    }
}
