package com.dktechin.ticket.sales.model;

import com.dktechin.ticket.sales.enums.MovieType;
import java.time.Duration;
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

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Duration getRunningTime() { return runningTime; }
    public void setRunningTime(Duration runningTime) { this.runningTime = runningTime; }

    public Money getFee() { return fee; }
    public void setFee(Money fee) { this.fee = fee; }

    public List<DiscountCondition> getDiscountConditions() { return discountConditions; }
    public void setDiscountConditions(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public MovieType getMovieType() { return movieType; }
    public void setMovieType(MovieType movieType) { this.movieType = movieType; }

    public Money getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(Money discountAmount) { this.discountAmount = discountAmount; }

    public double getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(double discountPercent) { this.discountPercent = discountPercent; }
}
