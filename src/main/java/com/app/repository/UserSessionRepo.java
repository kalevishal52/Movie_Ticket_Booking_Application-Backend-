package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.UserSession;

public interface UserSessionRepo  extends JpaRepository<UserSession, Integer>  {

}
