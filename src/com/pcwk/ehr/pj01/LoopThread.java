package com.pcwk.ehr.pj01;

import java.lang.Thread.State;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.pcwk.ehr.cmn.PLog;

public class LoopThread extends Thread implements PLog {
	
	HospitalManagement<Patient> hospital;

	LoopThread(HospitalManagement<Patient> hospital) {
		this.hospital = hospital;
	}
    
	public static void printMenu() {

		System.out.println("\n───────────────────────────────────────────");
		System.out.println("메뉴를 선택하세요.(0번 입력시 프로그램 종료)");
		System.out.println("1.환자 등록, 2.환자 정보 변경, 3.환자 삭제, 4.환자명단 확인, ");
		System.out.println("5.개별 환자정보 조회, 6.개별 환자 바이탈 이력 조회, 7.입원 환자 상태 보고서, 8.환자 위험도 평가 실행");
		System.out.println("───────────────────────────────────────────");
		System.out.print("입력:");
	}

	@Override
	public void run() {

		CancelableScanner cancelableScanner=null;
		CancelThread can = null;
		VitalThread th = null;

		String answer = "";
		boolean inOperation = true;
        
		while (inOperation) {
			LocalTime now = LocalTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm");
            
			if (now.format(formatter).equals("00")) {
				try {
					th = new VitalThread(hospital);
					th.start();
					th.join();
					if(cancelableScanner!=null) {
						while(!th.getState().equals(State.TERMINATED)) {
							CancelThread.sleep(2000);
						}
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			printMenu();
			cancelableScanner=new CancelableScanner();
			can = new CancelThread(cancelableScanner);
			can.start();
			try {
				answer = cancelableScanner.readLine();
			} catch (Exception e) {
				answer="cancel";
			}
			if (!answer.equals("cancel")) {
				switch (answer) {

				case "1":
					can.interrupt();
					hospital.registerPatient(); // 1. 환자 등록
					hospital.evaluatePatientStatus();
					break;
				case "2":
					can.interrupt();
					hospital.modifyPatient(); // 2. 환자 정보 변경
					break;
				case "3":
					can.interrupt();
					hospital.deletePatient(); // 3. 환자 삭제
					break;
				case "4":
					can.interrupt();
					hospital.patientList(); // 4. 환자 명단 확인
					break;
				case "5":
					can.interrupt();
					hospital.patientReport(); // 5. 개별 환자 정보 조회
					break;
				case "6":
					can.interrupt();
					hospital.patientVitalReport(); // 6. 개별 환자 바이탈 이력 조회
					break;
				case "7":
					can.interrupt();
					hospital.hospitalReport(); // 7. 입원 환자 상태 보고서
					break;
				case "8":
					can.interrupt();
					hospital.evaluatePatientStatus(); // 8. 환자 위험도 평가 실행
					break;
				case "9":
					hospital.vitalCheck(); // 9. 바이탈 체크
					break;
				case "0":
					LOG.debug("0이 입력되었습니다.");
					LOG.debug("프로그램 종료");
					inOperation = false;
					// 프로그램이 종료되기 전에 JSON 파일에 환자 정보 저장
				 HospitalManagement.savePatientListToJson(hospital.patients);
				 HospitalManagement.saveVitalListToJson(hospital.patients);
				 System.exit(0);
				default:
					System.out.println("잘못된 선택입니다. 0 ~ 9의 숫자를 입력해주세요.");
				}
			}else {
				continue;
			}
			
		} // while

	} // run

} // class
