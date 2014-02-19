package eu.verdelhan.ta4j.indicators.trackers;

import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.mocks.MockTick;
import eu.verdelhan.ta4j.mocks.MockTimeSeries;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class AccelerationDecelerationIndicatorTest {

	private TimeSeries series;

	@Before
	public void setUp() throws Exception {

		List<Tick> ticks = new ArrayList<Tick>();

		ticks.add(new MockTick(0, 0, 16, 8));
		ticks.add(new MockTick(0, 0, 12, 6));
		ticks.add(new MockTick(0, 0, 18, 14));
		ticks.add(new MockTick(0, 0, 10, 6));
		ticks.add(new MockTick(0, 0, 8, 4));

		series = new MockTimeSeries(ticks);
	}

	@Test
	public void testCalculateWithSma2AndSma3() throws Exception {
		AccelerationDecelerationIndicator acceleration = new AccelerationDecelerationIndicator(series, 2, 3);

		assertThat(acceleration.getValue(0)).isZero();
		assertThat(acceleration.getValue(1)).isZero();
		assertThat(acceleration.getValue(2)).isEqualTo(0.1666666d - 0.08333333d);
		assertThat(acceleration.getValue(3)).isEqualTo(1d - 0.5833333);
		assertThat(acceleration.getValue(4)).isEqualTo(-3d + 1d);
	}

	@Test
	public void testWithSma1AndSma2() throws Exception {
		AccelerationDecelerationIndicator acceleration = new AccelerationDecelerationIndicator(series, 1, 2);

		assertThat(acceleration.getValue(0)).isZero();
		assertThat(acceleration.getValue(1)).isZero();
		assertThat(acceleration.getValue(2)).isZero();
		assertThat(acceleration.getValue(3)).isZero();
		assertThat(acceleration.getValue(4)).isZero();
	}

	@Test
	public void testWithSmaDefault() throws Exception {
		AccelerationDecelerationIndicator acceleration = new AccelerationDecelerationIndicator(series);

		assertThat(acceleration.getValue(0)).isZero();
		assertThat(acceleration.getValue(1)).isZero();
		assertThat(acceleration.getValue(2)).isZero();
		assertThat(acceleration.getValue(3)).isZero();
		assertThat(acceleration.getValue(4)).isZero();
	}
}
