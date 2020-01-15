package com.dktechin.ticket.sales.model;

/**
 * 예약 정보 도메인 클래스
 */
public class Reservation {
    /** * 예약 고객정보 */
    private Customer customer;
    /** * 예약 상영 정보 */
    private Screening screening;
    /** * 예약 금액 */
    private Money fee;
    /** * 예약 인원 수 */
    private int audienceCount;

    public Reservation(Customer customer, Screening screening,
        Money fee, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Screening getScreening() { return screening; }
    public void setScreening(Screening screening) { this.screening = screening; }

    public Money getFee() { return fee; }
    public void setFee(Money fee) { this.fee = fee; }

    public int getAudienceCount() { return audienceCount; }
    public void setAudienceCount(int audienceCount) { this.audienceCount = audienceCount; }
}
