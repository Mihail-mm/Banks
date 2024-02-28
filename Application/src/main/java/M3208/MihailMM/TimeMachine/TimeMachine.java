package M3208.MihailMM.TimeMachine;

import M3208.MihailMM.Banks.CentralBank;

import java.time.LocalDate;

public class TimeMachine {
    private CentralBank _centralBank;
    private LocalDate _currentTime;

    public TimeMachine(CentralBank centralBank)
    {
        _centralBank = centralBank;
        _currentTime = LocalDate.now();
    }

    public void RewindTime(int years, int months, int days)
    {
        LocalDate tepmTime = _currentTime;
        _currentTime = LocalDate.of(years, months, days);
        while (tepmTime.isBefore(_currentTime))
        {
            _centralBank.NotifySubscribers(tepmTime);
            tepmTime = tepmTime.plusDays(1);
        }
    }
}
