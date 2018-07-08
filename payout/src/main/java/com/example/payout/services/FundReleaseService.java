package com.example.payout.services;

import com.example.payout.domain.FundRelease;

public interface FundReleaseService {
    void releaseFunds(FundRelease release);
}
