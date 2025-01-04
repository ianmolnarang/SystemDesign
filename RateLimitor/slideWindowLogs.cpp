#include <iostream>
#include <deque>
#include <chrono>
#include <thread>
#include <mutex>

class SlidingWindowLogs {
private:
    int maxRequests;                              // Maximum requests allowed
    int windowSizeSeconds;                        // Window size in seconds
    std::deque<std::chrono::steady_clock::time_point> requestTimestamps; // Store timestamps of requests
    std::mutex mtx;                               // Mutex for thread safety

public:
    SlidingWindowLogs(int maxRequests, int windowSizeSeconds)
        : maxRequests(maxRequests), windowSizeSeconds(windowSizeSeconds) {}

    bool allowRequest() {
        std::lock_guard<std::mutex> lock(mtx);

        auto now = std::chrono::steady_clock::now();

        // Remove timestamps that are outside the window
        while (!requestTimestamps.empty() &&
               std::chrono::duration_cast<std::chrono::seconds>(now - requestTimestamps.front()).count() >= windowSizeSeconds) {
            requestTimestamps.pop_front();
        }

        if (requestTimestamps.size() < maxRequests) {
            requestTimestamps.push_back(now);
            return true;
        }

        return false;
    }
};

int main() {
    SlidingWindowLogs limiter(5, 10); // Allow 5 requests in a 10-second sliding window

    for (int i = 0; i < 10; ++i) {
        if (limiter.allowRequest()) {
            std::cout << "Request " << i + 1 << " allowed.\n";
        } else {
            std::cout << "Request " << i + 1 << " denied (rate limit exceeded).\n";
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(1500)); // Simulate request interval
    }

    return 0;
}