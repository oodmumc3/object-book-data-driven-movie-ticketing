package com.dktechin.ticket.sales.model;

import com.dktechin.ticket.sales.enums.DiscountConditionType;
import com.dktechin.ticket.sales.enums.MovieType;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 영화 정보 관련 클래스
 * 좀더 명확히 보여주기 위해 lombok 사용안함.
 */
public class Movie {

    /** * 영화 제목 */
    private String title;
    /** * 영화 재생 시간 */
    private Duration runningTime;
    /** * 영화 관람 가격 */
    private Money fee;

    // 기존과 다른 부분
    /** * 영화 할인 정책 계산 */
    private List<DiscountCondition> discountConditions;
    /** * 영화 할인 정책 타입 */
    private MovieType movieType;
    /** * 할인 가격 정보 */
    private Money discountAmount;
    /** * 할인 비율 정보 */
    private double discountPercent;

    public void setTitle(String title) { this.title = title; }
    public void setFee(Money fee) { this.fee = fee; }
    public void setDiscountConditions(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }
    public MovieType getMovieType() { return movieType; }
    public void setMovieType(MovieType movieType) { this.movieType = movieType; }
    public void setDiscountAmount(Money discountAmount) { this.discountAmount = discountAmount; }
    public void setDiscountPercent(double discountPercent) { this.discountPercent = discountPercent; }

    /**
     * 비용 할인 금액을 제외하고 예매금액을 계산하여 반환한다.
     * @return 할인 예매 금액
     */
    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return this.fee.minus(this.discountAmount);
    }

    /**
     * 비율 기준 할인 금액을 제외하고 예매금액을 계산하여 반환한다.
     * @return 할인 예매 금액
     */
    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return this.fee.minus(this.fee.times(this.discountPercent));
    }

    /**
     * 할인 정책이 없으므로 예매금액을 그대로 반환한다.
     * @return 할인 예매 금액
     */
    public Money calculateNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return this.fee;
    }

    /**
     * 할인이 가능한 예매인지 판단하여 반환한다.
     * @param whenScreened 예매 상영 시간
     * @param sequence 예매 상영 회차
     * @return 할인 가능 여부
     */
    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for (DiscountCondition discountCondition : this.discountConditions) {
            if (discountCondition.getType() == DiscountConditionType.PERIOD) {
                // 기간 할인 판단
                if (discountCondition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
                    return true;
                }
            } else {
                // 회차 할인 판단
                if (discountCondition.isDiscountable(sequence)) {
                    return true;
                }
            }
        }

        return false;
    }
}
