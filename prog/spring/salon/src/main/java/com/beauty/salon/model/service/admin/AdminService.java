package com.beauty.salon.model.service.admin;

import com.beauty.salon.model.entity.Feedback;
import com.beauty.salon.model.entity.User;

import java.util.List;

public interface AdminService {
    List<Feedback> findFeedbacksByMasterId(Integer masterId, String locale);
}
