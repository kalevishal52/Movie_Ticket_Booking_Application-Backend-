package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer> {

}
