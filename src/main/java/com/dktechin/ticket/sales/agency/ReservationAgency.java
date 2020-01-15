package com.dktechin.ticket.sales.agency;

import com.dktechin.ticket.sales.model.Customer;
import com.dktechin.ticket.sales.model.Money;
import com.dktechin.ticket.sales.model.Reservation;
import com.dktechin.ticket.sales.model.Screening;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }
}
