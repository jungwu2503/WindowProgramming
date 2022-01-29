import java.io.*;

import javax.sound.sampled.*;

public class Audio {
	private Clip clip;
	private File audioFile;
	private AudioInputStream audioInputStream;
	private boolean isLoop;
	
	public Audio(String pathName, boolean isLoop) { // �Ű����� - ���� ���, ���ѹݺ� ����
		try {
			clip = AudioSystem.getClip(); // ����� ����� ����� �� �ִ� Ŭ�� �޾ƿ���
			audioFile = new File(pathName);
			audioInputStream = AudioSystem.getAudioInputStream(audioFile); // ��θ��� ���Ϸκ��� ����� �Է� ��Ʈ�� ��������
			clip.open(audioInputStream); // Ŭ���� ����� �Է� ��Ʈ���� �޾ƿ����� ���� ��� �غ� �Ϸ�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() { // clip ����� ���� �޼ҵ�
		clip.setFramePosition(0);
		clip.start(); // Ŭ���� ������ ó���� ����Ű�� �ϰ� ���
		if (isLoop) clip.loop(Clip.LOOP_CONTINUOUSLY); // ���ѹݺ��� ���
	}

	public void stop() { // ������� ���� ���ߴ� �޼ҵ�
		clip.stop();
	}
	
}
