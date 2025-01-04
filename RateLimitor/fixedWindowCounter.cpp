#include <iostream>
#include <chrono>
#include <thread>
#include <mutex>

class FixedWindowCounter {
private:
    int maxRequests;                        // Maximum requests allowed per window
    int currentCount;                       // Current request count
    std::chrono::steady_clock::time_point windowStart; // Start of the current window
    int windowSizeSeconds;                  // Window size in seconds
    std::mutex mtx;                         // Mutex for thread safety

public:
    FixedWindowCounter(int maxRequests, int windowSizeSeconds)
        : maxRequests(maxRequests), currentCount(0),
          windowStart(std::chrono::steady_clock::now()), windowSizeSeconds(windowSizeSeconds) {}

    bool allowRequest() {
        std::lock_guard<std::mutex> lock(mtx);

        auto now = std::chrono::steady_clock::now();
        auto elapsedTime = std::chrono::duration_cast<std::chrono::seconds>(now - windowStart).count();

        if (elapsedTime >= windowSizeSeconds) {
            // Reset the window
            currentCount = 0;
            windowStart = now;
        }

        if (currentCount < maxRequests) {
            currentCount++;
            return true;
        }

        return false;
    }
};

int main() {
    FixedWindowCounter counter(5, 10); // Allow 5 requests per 10-second window

    for (int i = 0; i < 10; ++i) {
        if (counter.allowRequest()) {
            std::cout << "Request " << i + 1 << " allowed.\n";
        } else {
            std::cout << "Request " << i + 1 << " denied (rate limit exceeded).\n";
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(1500)); // Simulate request interval
    }

    return 0;
}