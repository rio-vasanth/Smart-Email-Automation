package com.Smart.Email.Automation.Project.Repository;

import com.Smart.Email.Automation.Project.Model.Email;
import org.hibernate.JDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Long> {
}
