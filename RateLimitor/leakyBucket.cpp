#include <iostream>
#include <chrono>
#include <thread>
#include <mutex>

class LeakyBucket {
private:
    double maxCapacity;             // Maximum capacity of the bucket
    double leakRate;                // Rate at which tokens leak (tokens/second)
    double currentLevel;            // Current level of the bucket
    std::chrono::steady_clock::time_point lastUpdate; // Last time the bucket leaked
    std::mutex mtx;                 // Mutex for thread safety

    // Leak tokens based on elapsed time
    void leak() {
        auto now = std::chrono::steady_clock::now();
        double elapsedSeconds = std::chrono::duration<double>(now - lastUpdate).count();

        currentLevel = std::max(0.0, currentLevel - elapsedSeconds * leakRate);
        lastUpdate = now;
    }

public:
    LeakyBucket(double maxCapacity, double leakRate)
        : maxCapacity(maxCapacity), leakRate(leakRate), currentLevel(0),
          lastUpdate(std::chrono::steady_clock::now()) {}

    // Try to add `tokens` to the bucket
    bool addRequest(double tokens) {
        std::lock_guard<std::mutex> lock(mtx); // Ensure thread-safety
        leak(); // Leak tokens before adding new requests

        if (currentLevel + tokens <= maxCapacity) {
            currentLevel += tokens;
            return true; // Allowed
        }

        return false; // Not allowed
    }
};

int main() {
    // Create a Leaky Bucket with a max capacity of 10 tokens and a leak rate of 2 tokens/second
    LeakyBucket bucket(10, 2);

    for (int i = 0; i < 20; ++i) {
        if (bucket.addRequest(1)) {
            std::cout << "Request " << i + 1 << " allowed.\n";
        } else {
            std::cout << "Request " << i + 1 << " denied (rate limit exceeded).\n";
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(500)); // Simulate request interval
    }

    return 0;
}