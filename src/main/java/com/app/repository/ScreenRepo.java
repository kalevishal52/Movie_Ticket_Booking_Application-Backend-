package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Screen;

public interface ScreenRepo extends JpaRepository<Screen, Integer> {

}
