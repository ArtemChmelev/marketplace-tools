package ru.chmelev.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaxationType {
    NPD("НПД", 10),
    USN("УСН", 6),
    USN_MINUS_EXPENSES("УСН Доходы минус расходы", 15),
    OSNO("ОСНО", 20);

    private final String name;
    private final double taxPercent;
}
