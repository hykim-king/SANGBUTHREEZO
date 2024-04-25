package com.pcwk.ehr.pj01;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.pcwk.ehr.cmn.PLog;

public class VitalThread extends Thread implements PLog {
		int vitalCheck=0;
		int evaluate =0;
		boolean loop=true;
		HospitalManagement<Patient> hospital;
		Scanner scanner =new Scanner(System.in);

		public VitalThread(HospitalManagement<Patient> hospital) {
			this.hospital=hospital;
		}
		

		@Override
		public void run() {
					String msg ="바이탈 체크를 진행해야합니다 몇분후 진행하시겠습니까( 0 : 즉시)";
					
					try{
						String delay;
						System.out.println("\n"+msg);
						Scanner scanner = new Scanner(System.in);
						try{
							delay=scanner.nextLine();
							sleep(Integer.parseInt(delay)*1000*50);
						}catch(Exception e) {
							LOG.debug("숫자를 입력하세요");
						}
						
						System.out.println("\n바이탈 체크를 시작합니다.");
						System.out.println("3");
						sleep(1000);
						System.out.println("2");
						sleep(1000);
						System.out.println("1");
						sleep(1000);
						vitalCheck=this.hospital.vitalCheck();
						evaluate=this.hospital.evaluatePatientStatus();
						
					} catch (NullPointerException e) {
						LOG.debug("시간을 선택하지 않고 창을 종료");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
					
				}	
		
}
