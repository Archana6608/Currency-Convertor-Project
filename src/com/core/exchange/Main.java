package com.core.exchange;
import com.core.exchange.model.ConversionQuery;
import com.core.exchange.model.ConversionResult;
import com.core.exchange.repository.RemoteExchangeRateRepository;
import com.core.exchange.service.CurrencyConverterService;
import com.core.exchange.service.CurrencyConverterServiceImpl;
import com.core.exchange.util.SymbolRegistry;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        CurrencyConverterService service = new CurrencyConverterServiceImpl(new RemoteExchangeRateRepository());
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================================");
        System.out.println("💼 ENTERPRISE ASSET EXCHANGE ENGINE INTERFACE                    ");
        System.out.println("=================================================================");
        while (true) {
            try {
                System.out.print("\nEnter Origin Base Asset Code (or type 'EXIT'): ");
                String base = scanner.next().trim();
                if (base.equalsIgnoreCase("EXIT")) break;
                System.out.print("Enter Target Asset Code: ");
                String target = scanner.next().trim();
                System.out.print("Enter Transaction Quantity: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("❌ Parsing Exception: Input parameter is not a standard numerical digit.");
                    scanner.next(); continue;
                }
                double amount = scanner.nextDouble();
                ConversionResult report = service.processConversion(new ConversionQuery(base, target, amount));
                
                String baseName = SymbolRegistry.getName(report.getBaseCurrency());
                String targetName = SymbolRegistry.getName(report.getTargetCurrency());
                String targetSymbol = SymbolRegistry.getSymbol(report.getTargetCurrency());
                
                System.out.println("\n+-------------------------------------------------------------------------------+");
                System.out.println("| ✅ DATA RETRIEVAL TRANSACTION PROCESSED SUCCESSFULLY                          |");
                System.out.printf("|    ➡️ Transacted Unit: %,.2f %s (%s)\n", report.getOriginalAmount(), report.getBaseCurrency(), baseName);
                System.out.printf("|    ➡️ Converted Yield: %,.2f %s (%s - %s)\n", report.getConvertedAmount(), report.getTargetCurrency(), targetSymbol, targetName);
                System.out.printf("|    ➡️ Reference Index: 1 %s = %.5f %s\n", report.getBaseCurrency(), report.getExchangeRate(), report.getTargetCurrency());
                System.out.println("+-------------------------------------------------------------------------------+");
            } catch (Exception ex) {
                System.out.println("\n⚠️ Exception Intercepted inside Runtime Pipeline: " + ex.getMessage());
            }
        }
        System.out.println("\n+-------------------------------------------------------------------------------+");
        System.out.println("| =================================================================             |");
        System.out.println("| 👋 THANK YOU FOR USING THE ASSET EXCHANGE ENGINE!                             |");
        System.out.println("| 🔒 System context execution loops cleanly unregistered.                       |");
        System.out.println("| =================================================================             |");
        System.out.println("+-------------------------------------------------------------------------------+");
        scanner.close();
    }
}