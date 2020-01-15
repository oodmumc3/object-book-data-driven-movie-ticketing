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

    public Movie getMovie() { return movie; }
    public int getSequence() { return sequence; }

    public LocalDateTime getWhenScreened() { return whenScreened; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public void setSequence(int sequence) { this.sequence = sequence; }
    public void setWhenScreened(LocalDateTime whenScreened) { this.whenScreened = whenScreened; }
}

