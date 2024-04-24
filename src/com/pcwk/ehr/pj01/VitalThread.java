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
			        LOG.info("바이탈쓰레드 실행");
					String[] minute = {"0","5","10","30"};
					String msg ="바이탈 체크를 진행해야합니다 몇분후 진행하시겠습니까( 0 : 즉시)";
					
					try{
						int delay;
						try{
							String input=(String)JOptionPane.showInputDialog(null,msg,"바이탈체크",0,null,minute,minute[0]);
							if(input==null) {
								delay =0;
							}else {
								delay = Integer.parseInt(input);
							}
							if(delay!=0) {
								sleep(delay*1000*60);
							}
						
						}catch(Exception e) {
							LOG.debug("JOptionPane");
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
