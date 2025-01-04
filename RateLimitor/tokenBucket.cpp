#include <iostream>
#include <chrono>
#include <thread>
#include <mutex>

class TokenBucket {
private:
    double maxTokens;            // Maximum number of tokens
    double refillRate;           // Rate at which tokens are added (tokens/second)
    double currentTokens;        // Current number of tokens available
    std::chrono::steady_clock::time_point lastRefill; // Last time the bucket was refilled
    std::mutex mtx;              // Mutex for thread-safety

    // Refill the tokens based on elapsed time
    void refill() {
        auto now = std::chrono::steady_clock::now();
        double elapsedSeconds = std::chrono::duration<double>(now - lastRefill).count();

        currentTokens = std::min(maxTokens, currentTokens + elapsedSeconds * refillRate);
        lastRefill = now;
    }

public:
    TokenBucket(double maxTokens, double refillRate)
        : maxTokens(maxTokens), refillRate(refillRate), currentTokens(maxTokens),
          lastRefill(std::chrono::steady_clock::now()) {}

    // Try to consume `tokens` tokens
    bool consume(double tokens) {
        std::lock_guard<std::mutex> lock(mtx); // Ensure thread-safety
        refill(); // Refill tokens before consumption

        if (currentTokens >= tokens) {
            currentTokens -= tokens;
            return true; // Allowed
        }

        return false; // Not allowed
    }
};

int main() {
    // Create a Token Bucket with max 10 tokens and a refill rate of 2 tokens/second
    TokenBucket bucket(10, 2);

    for (int i = 0; i < 20; ++i) {
        if (bucket.consume(1)) {
            std::cout << "Request " << i + 1 << " allowed.\n";
        } else {
            std::cout << "Request " << i + 1 << " denied (rate limit exceeded).\n";
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(500)); // Simulate request interval
    }

    return 0;
}