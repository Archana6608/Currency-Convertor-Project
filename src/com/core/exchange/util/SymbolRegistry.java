package com.core.exchange.util;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public final class SymbolRegistry {
    private static final Map<String, String> CACHE;
    private static final Map<String, String> NAME_CACHE;
    static {
        Map<String, String> s = new HashMap<>();
        s.put("USD", "$"); s.put("EUR", "€"); s.put("GBP", "£"); s.put("INR", "₹"); s.put("JPY", "¥"); s.put("CAD", "CA$"); s.put("AUD", "A$");
        CACHE = Collections.unmodifiableMap(s);

        Map<String, String> n = new HashMap<>();
        n.put("USD", "US Dollars"); n.put("EUR", "Euros"); n.put("GBP", "UK Pounds"); n.put("INR", "Indian Rupees"); n.put("JPY", "Japanese Yen");
        NAME_CACHE = Collections.unmodifiableMap(n);
    }
    private SymbolRegistry() {}
    public static String getSymbol(String assetIsoCode) { return CACHE.getOrDefault(assetIsoCode.toUpperCase(), assetIsoCode); }
    public static String getName(String assetIsoCode) { return NAME_CACHE.getOrDefault(assetIsoCode.toUpperCase(), assetIsoCode + " Asset"); }
}