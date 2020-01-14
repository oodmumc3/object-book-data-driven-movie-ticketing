package com.dktechin.ticket.sales.model;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

/**
 * 금액을 나타내는 도메인 오브젝트
 */
@RequiredArgsConstructor
public class Money {
    public static final Money ZERO = Money.wons(0L);

    private final BigDecimal amount;

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money plus(Money anotherAmount) {
        return new Money(this.amount.add(anotherAmount.amount));
    }

    public Money minus(Money anotherAmount) {
        return new Money(this.amount.subtract(anotherAmount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return this.amount.compareTo(other.amount) >= 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Money{");
        sb.append("amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
