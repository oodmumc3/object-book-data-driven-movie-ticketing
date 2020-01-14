package com.dktechin.ticket.sales;

import com.dktechin.ticket.sales.agency.ReservationAgency;
import com.dktechin.ticket.sales.enums.DiscountConditionType;
import com.dktechin.ticket.sales.enums.MovieType;
import com.dktechin.ticket.sales.model.Customer;
import com.dktechin.ticket.sales.model.DiscountCondition;
import com.dktechin.ticket.sales.model.Money;
import com.dktechin.ticket.sales.model.Movie;
import com.dktechin.ticket.sales.model.Reservation;
import com.dktechin.ticket.sales.model.Screening;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

public class TicketSalesMain {

    public static void main(String[] args) {
        Customer customer = new Customer("park jin", "jinn");

        // xman 영화는 금액 할인을 해주며 2000원을 할인해준다.
        Movie xMan = new Movie();
        xMan.setTitle("X맨");
        xMan.setFee(Money.wons(12000));
        xMan.setMovieType(MovieType.AMOUNT_DISCOUNT);
        xMan.setDiscountAmount(Money.wons(2000));

        DiscountCondition sequenceCondition = new DiscountCondition();
        sequenceCondition.setType(DiscountConditionType.SEQUENCE);
        sequenceCondition.setSequence(1);
        xMan.setDiscountConditions(Collections.singletonList(sequenceCondition));

        // 1회차 상영이므로 2000원 할인된다.
        Screening xManScreening1 = new Screening();
        xManScreening1.setMovie(xMan);
        xManScreening1.setSequence(1);
        xManScreening1.setWhenScreened(LocalDateTime.now());

        ReservationAgency reservationAgency = new ReservationAgency();
        Reservation xManReserve = reservationAgency.reserve(xManScreening1, customer, 2);

        System.out.println("xManReserve reserve fee is " + xManReserve.getFee()); // xManReserve reserve fee is Money{amount=20000.0}

        // 슈퍼맨은 비율할인을 해주며 10% 할인한다.
        Movie superMan = new Movie();
        superMan.setTitle("슈퍼맨");
        superMan.setFee(Money.wons(12000));
        superMan.setMovieType(MovieType.PERCENT_DISCOUNT);
        superMan.setDiscountPercent(0.1);

        //월요일 7시~10시 기간할인을 해준다.
        DiscountCondition periodCondition = new DiscountCondition();
        periodCondition.setType(DiscountConditionType.PERIOD);
        periodCondition.setStartTime(LocalTime.of(7, 0));
        periodCondition.setEndTime(LocalTime.of(10, 0));
        periodCondition.setDayOfWeek(DayOfWeek.MONDAY);
        superMan.setDiscountConditions(Collections.singletonList(periodCondition));

        Screening superManScreening1 = new Screening();
        superManScreening1.setMovie(superMan);
        superManScreening1.setSequence(1);
        superManScreening1.setWhenScreened(LocalDateTime.of(2020, 1, 13, 8, 0));

        Reservation superManReserve = reservationAgency.reserve(superManScreening1, customer, 2);
        System.out.println("superman reserve fee is " + superManReserve.getFee()); // superman reserve fee is Money{amount=21600.00}
    }
}
