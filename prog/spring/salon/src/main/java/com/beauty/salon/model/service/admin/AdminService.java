package com.beauty.salon.model.service.admin;

import com.beauty.salon.model.entity.Feedback;

import java.util.List;

public interface AdminService {
    List<Feedback> findFeedbackMasterById(Integer masterId, String locale);
}
