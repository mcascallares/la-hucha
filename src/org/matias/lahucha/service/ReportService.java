package org.matias.lahucha.service;

import java.util.Date;

import org.matias.lahucha.dto.Report;

public interface ReportService {

	Report generate(String userEmail, String userName, Date from, Date until);
}
