import java.text.DecimalFormat;		// 숫자 출력 형식에 콤마를 추가하기 위한 클래스
import java.text.SimpleDateFormat;	// 현재 날짜,시간 출력 양식 지정 위한 클래스
import java.util.ArrayList;			// ArrayList -> 상품별 면세 여부 표시 및 출력용 상품명 저장용.
import java.util.Calendar;			// 현재 날짜,시간 받아오기 위한 클래스
//KOPO18 서지윤 / 2022.04.26
//5강_리포팅 연습_#3-3. 영수증 (3) 이마트 (면세,과세 구분)
public class k18_receipt03 {

	public static void main(String[] args) {
		// #1. 구입 상품 정보 (상품명, 수량, 소비자 가격, 면세여부)
		// 상품명
		String[] k18_itemname = {"제주 한라봉 3kg 선물세트","아욱","애호박","당근","감자",
								"햇무","킹스베리딸기","동물복지란10구박스","저지방우유2L","찌개용두부(200g)",
								"배동홍비빔면 4입","농심 해물안성탕면 5개입","롯데 허쉬너겟크리미아몬드","진라면(순)120g*20입","오리온 오징어 땅콩 900g",
								"마미손 고무장갑 6입","면스판기본팬츠 DARK NAVY","은박풍선해피버스데이투유","노브랜드 표준형밴드 10매","봄봄 페이퍼모빌",
								"체리부로닭볶음용800g","새송이(봉)","이맛쌀20kg","도라지","후디스 그릭요거볼 플레인",
								"후디스 순곡물바 치즈맛","후디스 순곡물바 당근맛","앙팡치즈까요까요딸기맛(5개입)","패밀리 밀푀유 소고기 샤브샤브(조리 필요)","솔티드몽블랑"};
		ArrayList<String> k18_itemnameTaxFree = new ArrayList<String>(); 	// 면세 상품 상품명 앞에 *표시 추가할 배열 선언. 동적 활용 위해 ArrayList 사용.
		ArrayList<String> k18_itemnamePrint = new ArrayList<String>(); 		// 상품명 출력용으로 저장할 배열 선언. (Byte 한계 지정하여 출력용으로 절삭 예정)
		int k18_printLength = 14;											// 출력할 상품명 길이(byte) 지정 => 14byte (한글 기준 7글자)
		// 상품 가격 (개당 단가)
		int[] k18_itemprice = {10000000,1000,990,1200,500,25000,15000,8000,3000,4000,
								2960,2980,4400,4580,6580,8980,29900,5900,1980,4800,
								5580,3280,51990,4770,4480,3900,3900,3950,20800,3980};
		// 상품별 구매 수량
		int[] k18_itemnumber = {1,10,1,5,5,1,2,1,1,1,
								1,1,1,1,1,1,1,1,2,1,
								1,1,1,1,2,2,2,1,1,1};
		// 상품별 면세/과세 여부 (true = 면세, false = 과세)
		boolean[] k18_taxfree = {true,true,true,true,true,true,true,true,true,true,
								false,false,false,false,false,false,false,false,false,false,
								true,true,true,true,false,false,false,false,true,false};
		
		
		// #3. 상품 총 소비자 가격, 부가세, 과세 전 가격 계산
		int k18_totalPrice = 0;					// 정수 변수 (총 소비자 가격) 선언 및 0으로 초기화.
		int k18_totalNumber = 0;				// 정수 변수 (총 상품 수량) 선언 및 0으로 초기화.
		int k18_taxfreeItemTotalPrice = 0;		// 정수 변수 (면세 상품 가격 총액) 선언 및 0으로 초기화.
		int k18_taxItemTotalPrice = 0;			// 정수 변수 (과세 상품 소비자가격 총액) 선언 및 0으로 초기화.
		int k18_taxItemTotalNetPrice = 0;		// 정수 변수 (과세 상품 세전 가격 총액) 선언 및 0으로 초기화.
		int k18_tax = 0;						// 정수 변수 (총 부과세) 선언 및 0으로 초기화.
		float k18_TaxRate = (float)0.1;			// 실수 변수 (과세 세율) 선언 및 0.1 (10%)로 초기화.
		
		// #3-1. 상품 총 소비자 가격  & 총 수량 계산
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {	// 상품 종류만큼 for문 반복
			k18_totalPrice += (k18_itemprice[k18_i]*k18_itemnumber[k18_i]); // 총 가격 = 총 가격 + (상품별 개당 가격 * 수량)
		}
		for (int k18_i = 0; k18_i < k18_itemnumber.length; k18_i++) {	// 상품 종류만큼 for문 반복
			k18_totalNumber += (k18_itemnumber[k18_i]);					// 총 수량 = 총 수량 + 상품별 수량
		}
		
		// #3-2. 면세 상품 총액 & 과세 상품 (세후/세전) 총액 계산
		// (1) 면세 상품 총액 (세율 0%이므로, 세전 가격 = 세후 가격)
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {	// 상품 종류만큼 for문 반복
			if (k18_taxfree[k18_i] == true) { 							// 면세 상품인 경우,
				k18_taxfreeItemTotalPrice += k18_itemprice[k18_i] * k18_itemnumber[k18_i];	// 면세 상품 총 net price에 가격 더하기
			} 
		}
		// (2) 과세 상품 총액 (세금 포함 가격) = 소비자가격 총액 - 면세상품 가격 총액;
		k18_taxItemTotalPrice = k18_totalPrice - k18_taxfreeItemTotalPrice;
		// (3) 과세 상품 총 세전금액 (세금 제외) = 면세상품 총액/(1+부가세율) => 소수점 아래 절삭 (버림)
		k18_taxItemTotalNetPrice = (int)(k18_taxItemTotalPrice/(1+k18_TaxRate)/1*1);

		// #3-3. 부가세 가격 계산 = 소비자가격 총액 - 면세상품 가격 총액 - 과세상품 세전가격 총액;
		// *참고) 과세상품 세전 가격 계산 후 소수점 아래 절삭(버림)하였으므로, 부가세는 반대로 소수점 아래에서 올림 처리한 것이 됨.		
		k18_tax = k18_totalPrice - k18_taxfreeItemTotalPrice - k18_taxItemTotalNetPrice;		
		
		// #3-4. 면세 상품 *표시하기 (배열에 입력)
		for (int k18_i = 0; k18_i < k18_taxfree.length; k18_i++) { // 상품 종류만큼 for문 반복
			if (k18_taxfree[k18_i] == true) { 			// 면세 상품인 경우,
				k18_itemnameTaxFree.add("*"); 			// 배열에 * 추가.
			} else if (k18_taxfree[k18_i] == false) {	// 과세 상품인 경우,
				k18_itemnameTaxFree.add("");			// 배열에 공백("") 추가.
			}
		}
		
		// #4. 출력 상품명 글자수 지정하기 => 출력부 하단에 메서드(k18_printName) 생성하여 사용.
		// 현재 클래스 객체화 (static 아닌 메서드 사용 위해)
		k18_receipt03 k18 = new k18_receipt03();
		// 메서드 호출 및 인수 지정하여 출력용 상품명 ArrayList에 값 저장 
		// (필요 인수 : 상품명 배열, 길이(바이트), 상품명 저장 ArrayList)
		k18.k18_printName(k18_itemname, k18_printLength, k18_itemnamePrint);

		// #5. 숫자 세 개 단위로 콤마 찍기 위해 DecimalFormat 클래스 객체화 및 형식 지정
		DecimalFormat k18_comma = new DecimalFormat("###,###,###");
		
		// #6. 현재 날짜&시간 찍기 -> Calendar & SimpleDateFormat 사용
		// (1) Calendar형 객체 선언 및 인스턴스화.
		Calendar k18_cal = Calendar.getInstance();
		// (2) SimpleDateFormat 형 객체 선언 및 형식 초기화 ("연연연연.월월.일일 시시:분분")
		SimpleDateFormat k18_sdf1 = new SimpleDateFormat ("YYYY-MM-dd HH:mm");
		// (3) SimpleDateFormat 형 객체 선언 및 형식 초기화 ("연연연연-월월-일일 시시:분분:초초")
		SimpleDateFormat k18_sdf2 = new SimpleDateFormat ("YYYY.MM.dd HH:mm:ss");
		// (4) SimpleDateFormat 형 객체 선언 및 형식 초기화 ("연연연연월월일일")
		SimpleDateFormat k18_sdf3 = new SimpleDateFormat ("YYYYMMdd");

		// #7. 출력 시작
		System.out.printf("%-14s%-10s\n","emart","이마트 죽전점 (031)888-1234");
		System.out.printf("%-14s%-10s\n","","206-86-50913 강희석");
		System.out.printf("%-14s%-10s\n","","용인 수지구 포은대로 552");
		System.out.printf("\n");
		System.out.printf("%s\n", "영수증 미지참시 교환/환불 불가");
		System.out.printf("%s\n", "정상상품에 한함, 30일 이내(신선 7일)");
		System.out.printf("%s\n", "※일부 브랜드 매장 제외(매장 고지물참조)");
		System.out.printf("%s\n", "교환/환불 구매점에서 가능");
		System.out.printf("\n");
		// 현재 날짜 및 시간 출력 시, SimpleDateFormat 사용하며 Calendar 함수를 통해 현재 날짜&시간 받아옴.
		System.out.printf("%-5s%-18s%15s\n", "[구　매]",k18_sdf1.format(k18_cal.getTime()),"POS:0011-9861");
		System.out.printf("-----------------------------------------\n"); // 기호 : 41개
		System.out.printf("%2s%-6s%10s%4s%7s\n"," ","상 품 명","단　가","수량","금　액");
		System.out.printf("-----------------------------------------\n"); // 기호 : 41개
		// 금액 출력 시, DecimalFormat 사용하여 숫자에 콤마 찍음 => 문자열로 형 변환되어 %s 형식으로 출력
		for (int k18_i = 0; k18_i < k18_itemprice.length; k18_i++) {
			System.out.printf("%-2s%-7s%11s%3s%11s\n", 
								k18_itemnameTaxFree.get(k18_i), 			// 면세,과세 여부 (면세 상품 * 출력)
								k18_itemnamePrint.get(k18_i),				// 상품명
								k18_comma.format(k18_itemprice[k18_i]),		// 단가
								k18_comma.format(k18_itemnumber[k18_i]),	// 수량
								k18_comma.format(k18_itemprice[k18_i]*k18_itemnumber[k18_i])); // 금액 (단가 * 수량)
			if (k18_i%5 == 4) {		// 상품 리스트 다섯 줄에 한 번씩 줄 출력
				System.out.printf("-----------------------------------------\n"); // 기호 : 41개
			}
		}
		System.out.printf("%23s%11s\n", "총　품목　수량 ", k18_comma.format(k18_totalNumber));
		System.out.printf("%23s%11s\n", "(*)면　세　물　품 ", k18_comma.format(k18_taxfreeItemTotalPrice));
		System.out.printf("%23s%11s\n", "과　세　물　품 ", k18_comma.format(k18_taxItemTotalNetPrice));
		System.out.printf("%23s%11s\n", "부　　가　　세 ", k18_comma.format(k18_tax));
		System.out.printf("%23s%11s\n", "합　　　　　계 ", k18_comma.format(k18_totalPrice));
		System.out.printf("%-12s%23s\n", "결 제 대 상 금 액", k18_comma.format(k18_totalPrice));
		System.out.printf("-----------------------------------------\n"); // 기호 : 41개
		System.out.printf("%-12s%18s%s%s\n", "0012 KEB 하나","541707**0484","/","35860658");
		// 카드 결제 금액 : 총 소비자가격 ###,### 형태로 출력 (DecimalFormat 사용)
		System.out.printf("%-12s%9s%s%10s\n", "카드결제(IC)","일시불"," / ",k18_comma.format(k18_totalPrice)); 
		System.out.printf("-----------------------------------------\n"); // 기호 : 41개
		System.out.printf("%22s\n", "[신세계포인트 적립]");
		System.out.printf("%-4s%s\n", "홍*두","고객님의 포인트 현황입니다.");
		System.out.printf("%-7s%17s%10s\n", "금회발생포인트","9350**9995","164");
		System.out.printf("%-9s%14s%1s%9s%1s\n", "누계(가용)포인트",k18_comma.format(5637),
													"(",k18_comma.format(5473),")");
		System.out.printf("%23s\n", "*신세계포인트 유효기간은 2년입니다.");
		System.out.printf("-----------------------------------------\n"); // 기호 : 41개
		System.out.printf("%22s\n", "구매금액기준 무료주차시간 자동부여");
		System.out.printf("%-8s%28s\n", "차량번호 : ","34저****");
		// 현재 날짜 및 시간 출력 시, SimpleDateFormat 사용하며 Calendar 함수를 통해 현재 날짜&시간 받아옴.
		System.out.printf("%-8s%29s\n", "입차시간 : ",k18_sdf2.format(k18_cal.getTime()));
		System.out.printf("-----------------------------------------\n"); // 기호 : 41개
		System.out.printf("%-3s%-6s%s%-6s%22s\n", "캐셔:","084599"," ","양OO","1150");
		System.out.printf("%22s\n", "영수증 바코드 생략");
		// 현재 날짜 및 시간 출력 시, SimpleDateFormat 사용하며 Calendar 함수를 통해 현재 날짜&시간 받아옴.
		System.out.printf("%14s%s\n", k18_sdf3.format(k18_cal.getTime()),"/00119861/00164980/31");	
	}
	
	
	// #4-2. 출력 상품명 글자수 지정하기  : 상품명 출력 Byte에 맞게 자르고, ArrayList에 저장하는 함수!
	// 글자수는 Byte로 지정하며, 상품명을 16진수(Byte)로 변환하여 출력 기준과 비교. 
	// *참고: 한글은 한 글자당 2byte이며 모두 음수값임. (영어, 숫자, 기호 등은 1byte & 양수)
	// 필요한 인수 : 상품명 배열, 길이(바이트), 상품명 저장 array
	public ArrayList<String> k18_printName (String[] k18_itemName, int k18_byteLenght, ArrayList<String> k18_printName) {
		for (int k18_i = 0; k18_i < k18_itemName.length; k18_i++) { 	// 상품 종류만큼 for문 반복
			
			byte[] k18_byteString = k18_itemName[k18_i].getBytes(); 	// 상품명을 16진수로 변환하여 byte배열에 저장. (각 글자 BYTE 확인 가능)
			
			if (k18_byteString.length == k18_byteLenght) {				// 조건(1) : 상품명 길이 == 출력 지정 길이
				k18_printName.add(k18_itemName[k18_i]);			// 실행 : 상품명 그대로 출력 -> 출력용 이름 배열에 추가.
			} 
			else if (k18_byteString.length < k18_byteLenght ) {		// 조건(2) : 상품명 길이 < 출력 지정 길이
				int k18_diff = k18_byteLenght - k18_byteString.length; // 실행 : 출력할 상품명에 byte차이만큼 공백 추가 (공백 = 1byte)
				String k18_blank = "";									// 공백 수량 계산하여 추가할 String 변수 선언.
				for (int k18_j = 0; k18_j < k18_diff; k18_j++) {		// 출력 byte와 상품명 byte의 차이 (byte)만큼 반복
					k18_blank = k18_blank + " ";						// 공백(" " = 1byte) 추가 (공백 수 = byte 차이)
				}
				k18_printName.add(k18_itemName[k18_i]+k18_blank);	// -> 출력용 상품명 배열에 "상품명+공백" 저장
			} 
			else if (k18_byteString.length > k18_byteLenght) {			// 조건(3) : 상품명 길이 > 출력 지정 길이
				int k18_minusByteCount = 0;								// 실행 : 상품명에서 잘라야하는 길이(byte) 계산
				for (int k18_j=0; k18_j<k18_byteLenght; k18_j++) {		// printLength(byte)만큼 for문 반복
					if ( k18_byteString[k18_j] < 0) {					// byte 값이 음수인 경우 = 한글인 경우
						k18_minusByteCount += 1;						// minusByteCount +1
					} else {											// byte 값이 0 or 양수인 경우 = 한글이 아닌 경우
						k18_minusByteCount += 0;						// minusByteCount 변경 없음.
					}
				} 	// for문이 다 돌고 난 후, minusByteCount/2 = 한글 개수 
					//-> 만약 minusByteCount가 홀수라면, 한글 한 글자가 잘리게 되는 것이므로, 출력용 상품명 Byte를 -1로 조정한다. 
				if (k18_minusByteCount%2 != 0) {	// 18_minusByteCoun가 홀수인 경우,
					k18_printName.add(new String(k18_byteString, 0, k18_byteLenght-1)+" "); 
					// 출력 지정 길이보다 1byte 작게 출력용 상품명 길이 지정 (출력 지정 byte - 1;)
					// 지정 길이(Byte)에 맞추어 상품명 자른 후, String으로 변환하고 공백(1byte) 더하기
					// -> 출력용 이름 배열(k18_itemnamePrint)에 저장
				} else {							// 18_minusByteCoun가 짝수인 경우,
					k18_printName.add(new String(k18_byteString, 0, k18_byteLenght)); 
					// 출력 길이(Byte)에 맞추어 상품명 자른 후, String으로 변환 
					// -> 출력용 이름 배열(k18_itemnamePrint)에 저장
				}
			}
		}
		return k18_printName;
	}
	
	
}
