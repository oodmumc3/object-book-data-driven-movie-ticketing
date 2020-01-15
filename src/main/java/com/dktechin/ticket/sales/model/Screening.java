package com.dktechin.ticket.sales.model;

import java.time.LocalDateTime;

/**
 * 영화 상영 정보 도메인 클래스
 */
public class Screening {
    /** * 상영될 영화 정보 */
    private Movie movie;
    /** * 상영 회차 정보 */
    private int sequence;
    /** * 상영 시작 시간 */
    private LocalDateTime whenScreened;

    public void setMovie(Movie movie) { this.movie = movie; }

    public void setSequence(int sequence) { this.sequence = sequence; }
    public void setWhenScreened(LocalDateTime whenScreened) { this.whenScreened = whenScreened; }

    /**
     * 예매 금액을 계산하여 반환한다.
     * @param audienceCount 예매 인원수
     * @return 총 예매 금액
     */
    public Money calculateFee(int audienceCount) {
        switch (this.movie.getMovieType()) {
            case AMOUNT_DISCOUNT:
                if (this.movie.isDiscountable(this.whenScreened, this.sequence)) {
                    return this.movie.calculateAmountDiscountedFee().times(audienceCount);
                }

            case PERCENT_DISCOUNT:
                if (this.movie.isDiscountable(this.whenScreened, this.sequence)) {
                    return this.movie.calculatePercentDiscountedFee().times(audienceCount);
                }

            case NONE_DISCOUNT:
                return this.movie.calculateNoneDiscountedFee().times(audienceCount);
        }

        return this.movie.calculateNoneDiscountedFee().times(audienceCount);
    }
}

