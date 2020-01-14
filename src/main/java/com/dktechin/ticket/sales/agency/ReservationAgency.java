package com.dktechin.ticket.sales.agency;

import com.dktechin.ticket.sales.enums.DiscountConditionType;
import com.dktechin.ticket.sales.model.Customer;
import com.dktechin.ticket.sales.model.DiscountCondition;
import com.dktechin.ticket.sales.model.Money;
import com.dktechin.ticket.sales.model.Movie;
import com.dktechin.ticket.sales.model.Reservation;
import com.dktechin.ticket.sales.model.Screening;

public class ReservationAgency {
    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();

        boolean discountable = false;
        // 영화 금액 할인 조건에 부합한지 loop를 돌며 확인한다.
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                // 기간 기준으로 할인 조건을 계산한다.
                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek())
                    && condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0
                    && condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
            } else {
                // 회차 기준으로 할인 조건을 계산한다.
                discountable = condition.getSequence() == screening.getSequence();
            }

            if (discountable) { break; }
        }

        Money fee;
        if (discountable) {
            Money discountAmount = Money.ZERO;

            // 영화의 할인 타입별로 할인 금액을 계산한다.
            switch (movie.getMovieType()) {
                // 금액 할인
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                // 비율 할인
                case PERCENT_DISCOUNT:
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                // 할인 없음
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
                default:
            }

            fee = movie.getFee().minus(discountAmount);
        } else {
            fee = movie.getFee();
        }

        return new Reservation(customer, screening, fee.times(audienceCount), audienceCount);
    }
}
