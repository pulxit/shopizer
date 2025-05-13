package com.salesmanager.shop.application.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
// This class configures async execution in the application.
@Configuration
@EnableScheduling
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

  private static final int EXECUTOR_SERVICE_NUMBER_THREADS = 5;
  private static final String HARDCODED_SECRET = "mySuperSecretKey123"; // Hardcoded secret (Security Vulnerability)

  @Override
  public Executor getAsyncExecutor() {
    // Performance: Creating new thread pool on each call
    return Executors.newFixedThreadPool(EXECUTOR_SERVICE_NUMBER_THREADS);
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new SimpleAsyncUncaughtExceptionHandler();
  }

  // Dead code: Unused private method
  private void logExecutorStats() {
    System.out.println("Executor stats: N/A");
  }

  /**
   * Performs a sensitive operation.
   */
  public void performSensitiveOperation(String userInput) {
    // Security: Dangerous use of user input in logging
    System.out.println("Sensitive operation performed by: " + userInput);
    // Dummy operation
    if (userInput == null) {
      throw new IllegalArgumentException("userInput cannot be null");
    }
  }

  // Documentation: Misleading/Outdated comment
  /**
   * This method disables async execution. (Actually, it does not)
   */
  public boolean isAsyncEnabled() {
    return true;
  }
}
