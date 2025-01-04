#include <iostream>
#include <unordered_map>
#include <chrono>
#include <thread>
#include <mutex>

class SlidingWindowCounter {
private:
    int maxRequests;                                  // Maximum requests allowed
    int windowSizeSeconds;                            // Window size in seconds
    int subWindowSizeSeconds;                         // Size of each sub-window
    std::unordered_map<int, int> subWindowCounts;     // Counts for each sub-window
    std::mutex mtx;                                   // Mutex for thread safety

    int getCurrentWindowIndex() {
        auto now = std::chrono::steady_clock::now();
        return std::chrono::duration_cast<std::chrono::seconds>(now.time_since_epoch()).count() / subWindowSizeSeconds;
    }

public:
    SlidingWindowCounter(int maxRequests, int windowSizeSeconds, int subWindowSizeSeconds)
        : maxRequests(maxRequests), windowSizeSeconds(windowSizeSeconds), subWindowSizeSeconds(subWindowSizeSeconds) {}

    bool allowRequest() {
        std::lock_guard<std::mutex> lock(mtx);

        int currentWindow = getCurrentWindowIndex();

        // Clean up old sub-windows
        for (auto it = subWindowCounts.begin(); it != subWindowCounts.end();) {
            if (currentWindow - it->first >= windowSizeSeconds / subWindowSizeSeconds) {
                it = subWindowCounts.erase(it);
            } else {
                ++it;
            }
        }

        // Calculate total requests in the sliding window
        int totalRequests = 0;
        for (const auto &entry : subWindowCounts) {
            totalRequests += entry.second;
        }

        if (totalRequests < maxRequests) {
            subWindowCounts[currentWindow]++;
            return true;
        }

        return false;
    }
};

int main() {
    SlidingWindowCounter counter(5, 10, 2); // Allow 5 requests in a 10-second sliding window, 2-second sub-windows

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