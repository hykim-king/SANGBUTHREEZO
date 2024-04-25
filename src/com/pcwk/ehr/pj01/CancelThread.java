package com.pcwk.ehr.pj01;

public class CancelThread extends Thread {
	 CancelableScanner cancelableScanner = new CancelableScanner();
	 @Override
	 
     public void run() {
         try {
             // 30초동안 취소 스레드를 재운다.
             Thread.sleep(1000*20);
             // cancelableScanner 의 입력 받기를 취소함
             cancelableScanner.cancel();
         } catch (Exception e) {
             System.out.println("cancelThread 스레드 중단(interrupted) : " +  e);
         }
     }
	public CancelThread(CancelableScanner cancelableScanner) {
		super();
		this.cancelableScanner = cancelableScanner;
	}

}
