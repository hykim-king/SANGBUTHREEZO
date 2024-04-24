package com.pcwk.ehr.pj01;

import java.util.Scanner;

import com.pcwk.ehr.cmn.PLog;

public class LoopThread extends Thread implements PLog {
		HospitalManagement<Patient> hospital;
		
		LoopThread(HospitalManagement<Patient> hospital){
			this.hospital=hospital;
		}
		
		public static void printMenu(HospitalManagement<Patient> hospital) {
			
			
			System.out.println("\n───────────────────────────────────────────");
			System.out.println("메뉴를 선택하세요.(0번 입력시 프로그램 종료)");
			System.out.println("1.환자 등록, 2.환자 정보 변경, 3.환자 삭제, 4.환자명단 확인, ");
			System.out.println("5.개별 환자정보 조회, 6.환자 바이탈 이력 조회, 7.입원 환자 상태 보고서");	
			System.out.println("───────────────────────────────────────────");
			System.out.print("입력:");
		}
		
		
		@Override
		public void run() {
			
			Scanner sc = new Scanner(System.in);
			printMenu(this.hospital);
			String answer="";
			boolean inOperation =true;
			VitalThread th =new VitalThread(hospital);
			th.start();
			
			while(inOperation) {
				
				if(th.getState().toString().equals("TERMINATED")) {
					th =new VitalThread(hospital);
					th.start();
				}
				
				printMenu(hospital);
		        answer=sc.nextLine();
				
				if(!answer.equals("a")) {
						switch(answer) {
						
						case "1":
							hospital.registerPatient();
							break;
						case "2":
							hospital.modifyPatient();
							break;
						case "3":
							hospital.deletePatient();
							break;
						case "4":
							hospital.patientList();
							break;
						case "5":
							hospital.patientReport();
							break;
						case "6":
							hospital.hospitalReport();
							break;
							
						case "0":
							LOG.debug("0이 입력되었습니다.");
							inOperation =false;
							this.interrupt();
							sc.close();
							th.interrupt();
							break;
						default :
					
						}
				}
			}
			
			
			
		}
	
}
