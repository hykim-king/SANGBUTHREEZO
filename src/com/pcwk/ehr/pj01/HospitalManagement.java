package com.pcwk.ehr.pj01;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.pcwk.ehr.cmn.PLog;

/*
 * 
 * 정상적으로 사용자에게 보여지는 메세지를 출력할땐 System.out 으로 출력한다.
 * 오류가 발생했을때는 LOG.debug 메세지를 남긴다.
 */

public class HospitalManagement<T> implements PLog {
	// 환자 객체들을 저장하는 리스트
	List<T> patients = new ArrayList<T>();

	// 병원 이름
	String hospitalName;

	// 생성자
	public HospitalManagement(String hospitalName) {
		super();
		this.hospitalName = hospitalName;
		this.patients = new LinkedList<>();
	}

	/*
	 * 환자의 정보를 환자 "이름+등록일.txt" 파일로 기록. 정상적으로 저장 읽었을경우 0 리턴, 문제가생겼을경우 -1 리턴(Exception
	 * 이 발생했을경우)
	 * 
	 * try catch 문으로 처리. LOG.debug(e.getMessage)를 통해 문제를 기록하도록함.
	 */
	public int saveWholeFile() {

		Iterator<T> iterator = this.patients.iterator();
		while (iterator.hasNext()) {
			saveInfoFile(iterator.next());
			Iterator<VitalInfo> viterator = (Iterator<VitalInfo>) iterator;

			/*
			 * 순서대로 모든 환자를 순회하며 정보중 vital 정보 만을 입력 받아 환자의 vital에 정보를 입력 등록할때 생성된
			 * "환자이름+등록날짜.txt"로 파일 이름을 정해서 저장한다. 저장은 saveInfofile 메소드를 통해 구현한다.
			 * 
			 */
		}
		return 0;

	}

	public int saveInfoFile(T patient) {
		return 0;

	}

	public int saveVitalInfoFile(T patient, VitalInfo vitalinfo) {
		return 0;
	}

	public int deleteInfo(T patient) {
		return 0;
	}

	public int deleteInfoFile(String path) {
		return 0;
	}

	public T readInfoFile(String path) {
		T patient = null;
		return patient;
	}

	public VitalInfo readVitalInfoFile(String path) {
		VitalInfo info = null;
		return info;
	}

	public Patient search(String name) {
		return new Patient();
	}

	/*
	 * 경로에 저장된 모든 파일들을 읽어서 환자 객체를 생성해서 list에 추가한다.
	 */
	public int initHospital() {
		// 경로에 있는 모든 파일에 접근해서 정보를 읽은후 리스트에 추가
		/*
		 * patients.add(readInfoFile(""));
		 * 
		 * for (int i =0;i<patients.size();i++) { Patient p =(Patient) patients.get(i);
		 * p.getVitalinfo().add(readVitalInfoFile("")); }
		 * 
		 */

		return 0;

	}

	// 새로운 환자 정보를 입력,
	public int registerPatient() {
		T patient = null;

		patients.add(patient);
		// list에 환자객체를 추가한다.
		return 0;
	}

	// 새로운 환자 정보를 입력,
	public void modifyPatient() {

		// 환자 객체를 찾아 해당 객체의 정보를 수정합니다. "환자이름+등록날짜.txt" 또한 수정합니다.
	}

	// 기존 환자 정보를 입력받아 리스트에서 삭제
	public void deletePatient() {
		T patient = null;

		patients.remove(patient);
		// 환자의 이름을 입력받아 list 에서 equals 를 이용하여 이름이 같은 환자를 찾아,"환자이름+등록날짜.txt"파일을 삭제

	}

	// 정상 종료시 0 리턴 문제가 발생시 -1 리턴
	public int vitalCheck() {

		Scanner scanner = new Scanner(System.in);
		Iterator<T> iterator = this.patients.iterator();
		while (iterator.hasNext()) {
			VitalInfo info = null;
			Patient p = (Patient) iterator.next();
			int BPM;// 심박수
			int SBP;// 수축기 혈압
			int DBP;// 이완기 혈압
			int bloodSugar;// 혈당

			System.out.println(p.getName() + " 환자의 바이탈 체크를 시작합니다.");

			System.out.print("심박수:");
			BPM = Integer.parseInt(scanner.nextLine());
			System.out.print("수축기혈압:");
			SBP = Integer.parseInt(scanner.nextLine());
			System.out.print("이완기혈압:");
			DBP = Integer.parseInt(scanner.nextLine());
			System.out.print("혈당:");
			bloodSugar = Integer.parseInt(scanner.nextLine());
			info = new VitalInfo(BPM, SBP, DBP, bloodSugar);
			p.vitalinfo.add(info);

			/*
			 * 순서대로 모든 환자를 순회하며 정보중 vital 정보 만을 입력 받아 환자의 vital에 정보를 입력
			 * 
			 */
		}
		scanner.close();
		System.out.println("바이탈 체크를 완료하였습니다.\n\n");
		return 0;
	}

	// 환자 목록을 출력
	public void patientList() {

		int num = 0;
		Iterator<T> iterator = this.patients.iterator();
		if (iterator.hasNext() == false) {
			System.out.println("환자 목록이 비었습니다.");
		}
		while (iterator.hasNext()) {

			Patient p = (Patient) iterator.next();
			System.out.println((num + 1) + "." + p.getName());
			num++;
			/*
			 * 번호를 매겨 환자의 이름을 출력해주고 총 인원수를 출력해준다.
			 */
		}
		System.out.println("총 환자의 수는 " + num + "명입니다.");
		System.out.println();
		System.out.println();
	}

	// 환자 위험도 평가
	// 정상 종료시 0 리턴 문제가 발생시 -1 리턴
	public int evaluatePatientStatus() {
		Iterator<T> iterator = this.patients.iterator();
		while (iterator.hasNext()) {
			Patient patient = (Patient) iterator.next();
			List<VitalInfo> vitalInfos = patient.getVitalinfo();

			VitalInfo latestVitalInfo = vitalInfos.get(vitalInfos.size() - 1);

			int bpm = latestVitalInfo.getBpm();
			int sbp = latestVitalInfo.getSbp();
			int dbp = latestVitalInfo.getDbp();
			int bloodSugar = latestVitalInfo.getBloodSugar();
			PatientStatus status=PatientStatus.Safe;
	
			// Safe
			if (bpm <= 80 && bloodSugar <= 120 && sbp <= 140 && dbp <= 90) {
				status = PatientStatus.Safe;
			}
			// Dangerous
			else if (bpm > 100 && bloodSugar > 150 && sbp > 140 && dbp > 90) {
				status =PatientStatus.Dangerous;
				patient.setIsNotified(false);
			}
			// Very_Dangerous\
			else if ((bpm > 100) && (countTrue(bloodSugar > 120, sbp > 140, dbp > 90) >= 2)
					|| (bloodSugar > 120) && (countTrue(bpm > 100, sbp > 140, dbp > 90) >= 2)
					|| (sbp > 140) && (countTrue(bpm > 100, bloodSugar > 120, dbp > 90) >= 2)
					|| (dbp > 90) && (countTrue(bpm > 100, bloodSugar > 120, sbp > 140) >= 2)) {
				status = PatientStatus.Very_Dangerous;
				patient.setIsNotified(false);
			}

			else if (countTrue(bpm > 80, bloodSugar > 120, sbp > 140, dbp > 90) >= 2) {
				status =PatientStatus.Dangerous;
				patient.setIsNotified(false);
			}

			else {
				status =PatientStatus.Dangerous;
				patient.setIsNotified(false);

				patient.setStatus(status);
				if (status!=PatientStatus.Safe) {
					patient.setIsNotified(false);
				}
			}
			if(!patient.isNotified()) {
				notifyToDoctor(patient);
			}
		}
		return 0;
	}//evaulate 끝

	private int countTrue(boolean... conditions) {
		int count = 0;
		for (boolean condition : conditions) {
			if (condition) {
				count++;
			}
		}
		return count;
	}

	public void notifyToDoctor(Patient p) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("의사를 호출 하시겠습니까? (Y/N)");
		String decision = scanner.nextLine().trim().toUpperCase();
		if(decision.equals("Y")) {
			p.setIsNotified(true);
			System.out.println("의사에게 알림을 보냈습니다.");
		}
		scanner.close();
	}
    
	
	
	//환자의 바이탈 정보를 시간별로 보고
	public void patientVitalReport() {
		
	}
	
	
	
	public void patientReport() {

		/*
		 * 환자 한명의 이름을 입력받아 해당 환자를 검색, 해당 환자의 정보를 출력해준다. name;// 이름 gender;// 성별
		 * bloodType;// 혈액형 birthDay;// 생년월일 YYYY.MM.DD height;// 키 weight;// 몸무게
		 * registerdDate;// 등록일 isNotified; PatientStatus status;
		 * 
		 * //바이탈 이력을 출력할지 선택하게 한후 선택시 바이탈 정보도 시간을 포함해 같이 출력. Vital history; -리스트에 있는 바이탈
		 * 정보들을 전부 출력
		 * 
		 */

	}

	public void hospitalReport() {
		/*
		 * 모든 환자에 대해서 환자의 이름과 현재 위험상태를 출력하고, 총 환자수, 각각 상태(Safe, Dangerous등)의 환자의 수, 의사에게
		 * notify되지 않은 환자의 여부를 출력해주고, "현재시간+report.txt" 에 내용을 기록한다.
		 * 
		 */

		FileWriter writer = null;

		try {
			// report 이름에 들어갈 현재 시간 포맷
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH-mm");
			String formattedTime = formatter.format(currentTime);

			// report.txt 파일을 쓰기 모드로 열기
			String fileName = formattedTime + "report.txt";
			writer = new FileWriter(fileName);

			Iterator<T> iterator = this.patients.iterator();
			int totalPatients = 0;
			int[] statusCounts = new int[PatientStatus.values().length];
			int notNotifiedCount = 0; // notify되지 않은 환자 수 세기 위한 변수
			StringBuilder notNotifiedPatients = new StringBuilder();

			while (iterator.hasNext()) {

				Patient patient = (Patient) iterator.next();
				System.out.println("환자 이름 : " + patient.getName() + " , 위험 상태 : " + patient.getStatus());
				totalPatients++;

				// 각 상태별 환자 수 계산
				switch (patient.getStatus()) {
				case Safe:
					statusCounts[PatientStatus.Safe.ordinal()]++;
					break;
				case Dangerous:
					statusCounts[PatientStatus.Dangerous.ordinal()]++;
					if (!patient.isNotified()) {
						notNotifiedCount++;
						notNotifiedPatients.append(patient.getName()).append(", ");
					}
					break;
				case Very_Dangerous:
					statusCounts[PatientStatus.Very_Dangerous.ordinal()]++;
					if (!patient.isNotified()) {
						notNotifiedCount++;
						notNotifiedPatients.append(patient.getName()).append(", ");
					}
					break;
				}

			}

			System.out.println("--------------------------------");
			System.out.println("총 환자 수 : " + totalPatients + "명");
			System.out.println("--------------------------------");
			writer.write("--------------------------------\n");
			writer.write("총 환자 수 : " + totalPatients + "명\n");
			writer.write("--------------------------------\n");

			// 환자 각각 상태에 따른 환자 수 출력
			for (PatientStatus status : PatientStatus.values()) {
				System.out.println(status + " 상태의 환자 수 : " + statusCounts[status.ordinal()] + "명");
				writer.write(status + " 상태의 환자 수 : " + statusCounts[status.ordinal()] + "명\n");
			}

			// notify되지 않은 환자 여부 출력
			if (notNotifiedCount > 0) {
				System.out.println("--------------------------------");
				System.out.println("의사에게 상태를 알리지 않은 위험한 환자 수 : " + notNotifiedCount + "명");
				System.out.println("의사에게 상태를 알리지 않은 환자 목록: " + notNotifiedPatients.toString());
				System.out.println("--------------------------------");
				writer.write("--------------------------------\n");
				writer.write("의사에게 상태를 알리지 않은 위험한 환자 수 : " + notNotifiedCount + "명\n");
				writer.write("의사에게 상태를 알리지 않은 환자 목록: " + notNotifiedPatients.toString() + "\n");
			} else {
				System.out.println("의사에게 상태를 알리지 않은 위험한 환자가 없습니다.");
			}

			writer.write("--------------------------------\n");
			writer.write("파일 저장시간 : " + formattedTime);

			System.out.println("\n" + fileName + "파일에 저장되었습니다.");

		} catch (IOException e) {
			// IOException 처리
			LOG.debug("IOException:" + e.getMessage());
		} finally {
			try {
				// writer 닫기
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				// IOException 처리
				LOG.debug("IOException:" + e.getMessage());
			}
		}

	}

}
