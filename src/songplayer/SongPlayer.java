package songplayer;

/**
 * SongPlayer has two static methods that allow an audio file to be played
 * through the output device. The first one
 * 
 * This small class was added as a bridge to a more complicated type in an
 * effort to make it easier to use. It also exists so 335 can postpone coverage
 * of concurrency with Threads. It has an interface similar to import
 * javax.swing.Timer in that you create a listener first than call the one
 * method playFile with that listener as the first argument.
 */

public class SongPlayer {
	/**
	 * Play the song stored in filename in a new thread where waiter will be
	 * sent
	 * 
	 * @param waiter
	 *            A reference to the EndOfSongEvent object that becomes
	 *            registered as a listener waiting for the song to end.
	 * @param audioFileName
	 *            The name of the file to be written to your output device.
	 */

	private Thread player;

	private EndOfSongListener waiter;
	private String fileName;
	private boolean isPlaying;

	public SongPlayer(EndOfSongListener waiter, String audioFileName) {
		this.waiter = waiter;
		this.fileName = audioFileName;
		isPlaying = false;
	}

	public void play() {
		isPlaying = true;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				player = new AudioFilePlayer(fileName);
				((AudioFilePlayer) player).addEndOfSongListener(waiter);

				// AudioFilePlayer extends Thread. When start is called,
				// the overridden run method in AudioFilePlayer executes.
				// If the song is not played in a separate thread, your GUI
				// stops working
				player.start();
			}
		});
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	public void endOfSOng(){
		isPlaying = false;
	}

	@SuppressWarnings("deprecation")
	public void stopPlaying() {
		if (player != null)
			player.stop();
		isPlaying = false;
	}

	public void pause() {
		if (player != null) {
			try {
				player.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isPlaying = false;
	}

	public void resume() {
		if (player != null)
			player.notify();
		isPlaying = true;
	}
}