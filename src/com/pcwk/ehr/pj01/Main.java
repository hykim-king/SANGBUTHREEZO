package com.pcwk.ehr.pj01;

import java.util.Scanner;

import com.pcwk.ehr.cmn.PLog;



public class Main implements PLog{
	
	public static int thState;//작동중 1, 종료시 0,작업 시작시 2
	public static void printMenu(HospitalManagement<Patient> hospital) {
		
		
		System.out.println("\n───────────────────────────────────────────");
		System.out.println("메뉴를 선택하세요.(0번 입력시 프로그램 종료)");
		System.out.println("1.환자 등록, 2.환자 정보 변경, 3.환자 삭제, 4.환자명단 확인, ");
		System.out.println("5.개별 환자정보 조회, 6.환자 바이탈 이력 조회, 7.입원 환자 상태 보고서");	
		System.out.println("───────────────────────────────────────────");
		System.out.print("입력:");
	}
	
	public static void main(String[] args) {
		//git add, commit, push : 20224-04-24
		//2 git add, commit, push
		String answer="";
		boolean inOperation =true;
		HospitalManagement<Patient> hospital = new HospitalManagement<Patient>("myHospital");
		hospital.initHospital();
		System.out.println(hospital.hospitalName+" 환자 관리를 시작합니다.");
		Scanner sc = new Scanner(System.in);


		//테스트용코드
		Patient patient01=new Patient("이성연", "남성", "O", "19881003", 183, 100);
		Patient patient02=new Patient("김성연", "여성", "A", "19881003", 163, 60);
		VitalInfo vitalinfo01= new VitalInfo(80, 80, 120, 120);
		VitalInfo vitalinfo02= new VitalInfo(60, 100, 180, 220);
		hospital.patients.add(patient01);
		hospital.patients.add(patient02);
		hospital.patients.get(0).vitalinfo.add(vitalinfo01);
		hospital.patients.get(1).vitalinfo.add(vitalinfo02);
		VitalThread th =new VitalThread(hospital);
		th.start();
		thState=1;
		
						
		while(inOperation) {
			
			if(th.getState().toString().equals("TERMINATED")) {
				th =new VitalThread(hospital);
				th.start();
				thState=1;
			}
			
			printMenu(hospital);
			
			try {
				answer=sc.nextLine();
			} catch (Exception e) {
				answer="";
			}
			if(!answer.equals("")) {
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
						break;
					default :
				
					}
			}
		}
		LOG.debug("프로그램 종료");
		hospital.saveWholeFile();
		sc.close();
		th.interrupt();
		
	}

}
