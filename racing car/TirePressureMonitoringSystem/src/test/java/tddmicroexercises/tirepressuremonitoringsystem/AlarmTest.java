package tddmicroexercises.tirepressuremonitoringsystem;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlarmTest {

    @Mock
    private Sensor sensor;

    @Test
    public void shouldNotAlarmWhenWithinThresholds() {
        Alarm alarm = new Alarm(sensor, 10, 20);
        Mockito.lenient().when(sensor.popNextPressurePsiValue()).thenReturn(10.0);
        alarm.check();
        Assertions.assertFalse(alarm.isAlarmOn());
        Mockito.lenient().when(sensor.popNextPressurePsiValue()).thenReturn(15.0);
        alarm.check();
        Assertions.assertFalse(alarm.isAlarmOn());
        Mockito.lenient().when(sensor.popNextPressurePsiValue()).thenReturn(20.0);
        alarm.check();
        Assertions.assertFalse(alarm.isAlarmOn());
    }

    @Test
    public void shouldAlarmWhenAboveThresholds() {
        Alarm alarm = new Alarm(sensor, 10, 20);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(22.0);
        alarm.check();
        Assertions.assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void shouldAlarmWhenBelowThresholds() {
        Alarm alarm = new Alarm(sensor, 10, 20);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(2.0);
        alarm.check();
        Assertions.assertTrue(alarm.isAlarmOn());
    }
}
