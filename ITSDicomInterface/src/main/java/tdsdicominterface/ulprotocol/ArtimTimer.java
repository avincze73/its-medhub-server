package tdsdicominterface.ulprotocol;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ArtimTimer extends TimerTask
{
	private ULManager ulManager;
	private Timer timer;
	private int timerType;
	private boolean running;

	private BufferedWriter diagOut;

	public ArtimTimer(ULManager ulManager, int timerType, BufferedWriter diagOut)
	{
		this.ulManager = ulManager;
		this.timerType = timerType;
		running = false;

		this.diagOut = diagOut;
	}

	public void startTimer(long timeout)
	{
		timer = new Timer();
		timer.schedule(this, timeout);
		running = true;
		printDiag(">>>> Type " + timerType + " timer is started.");
	}

	/**
	 * A futo timer-t leallitja. Ha nem fut a timer, akkor nincs hatasa.
	 */
	public void stopTimer()
	{
		if(isRunning())
		{
			timer.cancel();
//			timer.purge();
			printDiag("<<<< Type " + timerType + " timer is stopped.");
		}
		running = false;
	}

	@Override
	public void run()
	{
		try
		{
			printDiag("!!!!!! Type " + timerType + " timer is expired.");

			// timer tipus beallitasa az ULManager-ben, valamint a socket input stream-jenek bezarasa (ezzel SocketException eloidezese)
			ulManager.setTimerType(timerType);
			ulManager.getBis().close();
		}
		catch (IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	//---------------------------------------------------------------------------------------------------------

	public void printDiag(String text)
	{
		if(diagOut != null)
		{
			try
			{
				Calendar cal = Calendar.getInstance();
				String s = String.format("%02d:%02d:%02d.%03d  ArtimTimer: %s", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
						cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND), text);
				diagOut.write(s);
				diagOut.newLine();
			}
			catch(IOException ex)
			{
				throw new RuntimeException("I/O exception occured while writing to diagOut.", ex);
			}
		}
	}

	//-----------------------------------------------------------------------------------

	/**
	 * @return the running
	 */ public boolean isRunning() {
		return running;
	}
}
