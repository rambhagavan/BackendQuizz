package com.quizzapp.demo.config;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
	private final ConcurrentHashMap<String, Bucket> bucketMap = new ConcurrentHashMap<>();

    // Create a bucket with 10 requests per minute
    private Bucket createNewBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofSeconds(1)))) // 10 tokens every 1 minute
                .build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Identify the client based on IP address
        String clientIp = request.getRemoteAddr();
        System.out.println(clientIp);        // Retrieve or create a new bucket for this IP
        Bucket bucket = bucketMap.computeIfAbsent(clientIp, k -> createNewBucket());

        // Try to consume 1 token
        if (bucket.tryConsume(1)) {
            // If successful, proceed with the request
            return true;
        } else {
            // If rate limit exceeded, respond with 429
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            response.getWriter().write("Rate limit exceeded. Please try again later.");
            return false;
        }
    }
}
