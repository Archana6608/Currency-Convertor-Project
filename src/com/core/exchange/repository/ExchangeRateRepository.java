package com.core.exchange.repository;
import com.core.exchange.exception.ApiNetworkException;
import com.core.exchange.exception.CurrencyMappingException;
public interface ExchangeRateRepository {
    double fetchRate(String base, String target) throws ApiNetworkException, CurrencyMappingException;
}