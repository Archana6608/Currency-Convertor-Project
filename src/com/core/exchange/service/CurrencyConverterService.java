package com.core.exchange.service;
import com.core.exchange.exception.ApiNetworkException;
import com.core.exchange.exception.CurrencyMappingException;
import com.core.exchange.model.ConversionQuery;
import com.core.exchange.model.ConversionResult;
public interface CurrencyConverterService {
    ConversionResult processConversion(ConversionQuery query) throws ApiNetworkException, CurrencyMappingException;
}