package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm
{
    private double LowPressureThreshold;
    private double HighPressureThreshold;

    protected Sensor sensor;

    protected boolean alarmOn = false;

    public Alarm(Sensor sensor, double lowThresh, double highThresh) {
        this.sensor = sensor;
        this.LowPressureThreshold = lowThresh;
        this.HighPressureThreshold = highThresh;
    }

    public void check()
    {
        double psiPressureValue = sensor.popNextPressurePsiValue();

        if (psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue)
        {
            alarmOn = true;
        }
    }

    public boolean isAlarmOn()
    {
        return alarmOn; 
    }
}
