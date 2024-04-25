package com.pcwk.ehr.pj01;

import com.pcwk.ehr.cmn.PLog;



public class Main implements PLog{
	
	
	public static void main(String[] args) {
		
		
		HospitalManagement<Patient> hospital = new HospitalManagement<Patient>("myHospital");
		hospital.initHospital();
		System.out.println(hospital.hospitalName+" 환자 관리를 시작합니다.");
		LoopThread loop = new LoopThread(hospital);
		

        /*
		//테스트용코드
		Patient patient01=new Patient("이성연", "남성", "O", 19881003, 183, 100);
		Patient patient02=new Patient("김성연", "여성", "A", 19881003, 163, 60);
		Patient patient03=new Patient("박성연", "여성", "B", 19881003, 163, 60);
		Patient patient04=new Patient("장성연", "여성", "B", 19881003, 163, 60);
		VitalInfo vitalinfo01= new VitalInfo(70, 70, 70, 100);
		VitalInfo vitalinfo02= new VitalInfo(200, 200, 200, 220);
		VitalInfo vitalinfo03= new VitalInfo(90, 150, 230, 120);
		VitalInfo vitalinfo04= new VitalInfo(90, 60, 180, 320);
		hospital.patients.add(patient01);
		hospital.patients.add(patient02);
		hospital.patients.add(patient03);
		hospital.patients.add(patient04);
		hospital.patients.get(0).vitalinfo.add(vitalinfo01);
		hospital.patients.get(1).vitalinfo.add(vitalinfo02);
		hospital.patients.get(2).vitalinfo.add(vitalinfo03);
		hospital.patients.get(3).vitalinfo.add(vitalinfo04);
		hospital.patients.get(0).status=PatientStatus.Safe;
		hospital.patients.get(1).status=PatientStatus.Dangerous;
		hospital.patients.get(2).status=PatientStatus.Safe;
		hospital.patients.get(3).status=PatientStatus.Very_Dangerous;
		hospital.patients.get(3).setIsNotified(false);
		*/
		
		loop.start();
		try {
			loop.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOG.debug("프로그램 종료");
		
	}

}
