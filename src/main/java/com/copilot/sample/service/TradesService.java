package com.copilot.sample.service;

import com.copilot.sample.exception.TransactionException;
import com.copilot.sample.model.Trades;
import com.copilot.sample.repository.TradesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Add service configuration to TradesService class
@Service

public class TradesService {
    //Autowire TradesDao
    @Autowired
    private TradesDao tradesDao;

    public List<Trades> findAll() {
        return tradesDao.findAll();
    }

    public Trades findById(String tradesId) {
        return tradesDao.findById(tradesId);
    }

    public Trades create(Trades trades) throws TransactionException {
        return tradesDao.create(trades);
    }

    public Trades update(Trades trades) {
        return tradesDao.update(trades);
    }

    public void delete(String tradeId) {
        tradesDao.delete(tradeId);
    }

}