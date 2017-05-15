package jcalero.flightsearch;

import java.time.ZonedDateTime;

public interface TimeProvider {
	ZonedDateTime getCurrentTime();
}
