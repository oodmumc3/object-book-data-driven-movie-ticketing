package com.dktechin.ticket.sales.model;

import com.dktechin.ticket.sales.enums.DiscountConditionType;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * 할인 조건 정보 정관 클래스
 */
public class DiscountCondition {
    private DiscountConditionType type;

    /** * 할인 대상 상영 회차 */
    private int sequence;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public DiscountConditionType getType() { return type; }
    public void setType(DiscountConditionType type) { this.type = type; }

    public int getSequence() { return sequence; }
    public void setSequence(int sequence) { this.sequence = sequence; }

    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    /**
     * 기간을 기준으로 할인이 가능한지 판단하여 반환한다.
     * @param dayOfWeek 상영 요일
     * @param time 상영 시간
     * @return 할인 가능 여부
     */
    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
        if (this.type != DiscountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }

        return this.dayOfWeek.equals(dayOfWeek)
            && this.startTime.compareTo(time) <= 0
            && this.endTime.compareTo(time) >= 0;
    }

    /**
     * 상영 회차를 기준으로 할인이 가능한지 판단하여 반환한다.
     * @param sequence 상영 회차I
     * @return 할인 가능 여부
     */
    public boolean isDiscountable(int sequence) {
        if (this.type != DiscountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }

        return this.sequence == sequence;
    }
}
