import java.io.*;

import javax.sound.sampled.*;

public class Audio {
	private Clip clip;
	private File audioFile;
	private AudioInputStream audioInputStream;
	private boolean isLoop;
	
	public Audio(String pathName, boolean isLoop) { // 매개변수 - 파일 경로, 무한반복 여부
		try {
			clip = AudioSystem.getClip(); // 오디오 재생에 사용할 수 있는 클립 받아오기
			audioFile = new File(pathName);
			audioInputStream = AudioSystem.getAudioInputStream(audioFile); // 경로명의 파일로부터 오디오 입력 스트림 가져오기
			clip.open(audioInputStream); // 클립에 오디오 입력 스트림을 받아옴으로 파일 재생 준비 완료
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() { // clip 재생을 위한 메소드
		clip.setFramePosition(0);
		clip.start(); // 클립을 파일의 처음을 가리키게 하고 재생
		if (isLoop) clip.loop(Clip.LOOP_CONTINUOUSLY); // 무한반복일 경우
	}

	public void stop() { // 재생중인 파일 멈추는 메소드
		clip.stop();
	}
	
}
