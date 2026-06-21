package com.core.exchange.service;
import com.core.exchange.exception.ApiNetworkException;
import com.core.exchange.exception.CurrencyMappingException;
import com.core.exchange.model.ConversionQuery;
import com.core.exchange.model.ConversionResult;
import com.core.exchange.repository.ExchangeRateRepository;
public class CurrencyConverterServiceImpl implements CurrencyConverterService {
    private final ExchangeRateRepository repository;
    public CurrencyConverterServiceImpl(ExchangeRateRepository repository) { this.repository = repository; }
    @Override
    public ConversionResult processConversion(ConversionQuery query) throws ApiNetworkException, CurrencyMappingException {
        if (query.getAmount() < 0) { throw new IllegalArgumentException("Transaction volume magnitude constraints cannot fall below zero."); }
        if (query.getBaseCurrency().equals(query.getTargetCurrency())) {
            return new ConversionResult(query.getBaseCurrency(), query.getTargetCurrency(), query.getAmount(), query.getAmount(), 1.0);
        }
        double rate = repository.fetchRate(query.getBaseCurrency(), query.getTargetCurrency());
        return new ConversionResult(query.getBaseCurrency(), query.getTargetCurrency(), query.getAmount(), query.getAmount() * rate, rate);
    }
}