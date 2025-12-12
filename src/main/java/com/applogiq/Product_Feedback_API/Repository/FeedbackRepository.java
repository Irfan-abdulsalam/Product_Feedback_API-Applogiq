package com.applogiq.Product_Feedback_API.Repository;

import com.applogiq.Product_Feedback_API.entity.Feedback;
import com.applogiq.Product_Feedback_API.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

}
