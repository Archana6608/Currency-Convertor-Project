               PROJECT 4: CURRENCY CONVERTOR
# 💼 Enterprise Asset Exchange Engine

A decoupled, production-grade Java console application that tracks real-time asset exchange rates via remote HTTP API handshakes. The system calculates currency conversions dynamically and outputs them in structured framing layouts featuring full descriptive currency names and localized symbols.

---

## 📝 Project Task Overview & Objectives

This task implements a modular, enterprise-ready architecture to solve typical runtime integration and character decoding challenges in terminal applications. 

### Key System Achievements:
* **Decoupled Package Architecture:** Separated core concerns cleanly into dedicated models, interfaces, services, exceptions, and utility registries.
* **Character Encoding Resolution:** Built using a custom BOM-free UTF-8 layout to eliminate pipeline decoding crashes (`Get-Item` errors).
* **Live Remote API Handshake:** Plugs into an external exchange provider using Java's native `HttpClient` framework with rigorous timeout protection.
* **Refined Terminal UX:** Formats transaction confirmations and termination alerts inside beautifully aligned, isolated bounding boxes.

---

## 📂 Core Component Architecture

The system logic is isolated into 6 cohesive layers:

| Package / Component | Target File Name | Strategic Functionality |
| :--- | :--- | :--- |
| **Orchestration Core** | `Main.java` | Coordinates the user input loop, invokes the service layer, and prints the framed UI cards. |
| **Exception Framework** | `ApiNetworkException.java`<br>`CurrencyMappingException.java` | Intercepts integration errors, infrastructure faults, and invalid currency entries safely without breaking the runtime pipeline. |
| **Data Entities** | `ConversionQuery.java`<br>`ConversionResult.java` | Implements immutable data containers (DTOs) to safely carry parameters across execution layers. |
| **Data Repository Layer** | `ExchangeRateRepository.java`<br>`RemoteExchangeRateRepository.java` | Separates data fetching logic. Uses regex pattern matching on raw JSON streams to pull exact real-time reference rates. |
| **Business Logic Layer** | `CurrencyConverterService.java`<br>`CurrencyConverterServiceImpl.java` | Coordinates processing constraints (e.g., rejecting negative input values and optimizing identical source/target requests). |
| **Utility Registries** | `SymbolRegistry.java` | Maps standard asset codes (USD, INR, GBP) to localized full names and system rendering symbols (`$`, `₹`, `£`). |
