import java.text.DecimalFormat;		// 숫자 출력 형식에 콤마를 추가하기 위한 클래스
import java.text.SimpleDateFormat;	// 현재 날짜,시간 출력 양식 지정 위한 클래스
import java.util.ArrayList;
import java.util.Calendar;			// 현재 날짜,시간 받아오기 위한 클래스
//KOPO18 서지윤 / 2022.04.26
//5강_리포팅 연습_#3-2. 영수증 (2) 다이소 (한글 상품명 자르기 유의)
public class k18_receipt02 {

	public static void main(String[] args) {
		// #1. 구입 상품 정보 (상품명, 코드, 소비자가격, 수량)
		String k18_itemname1 = "퓨어에어 비말차단용마스크(최고급형)";
		String k18_itemcode1 = "1031615";
		int k18_price1 = 3000;
		int k18_amount1 = 1;
		
		String k18_itemname2 = "슬라이드식명찰(가로형)(100호)";
		String k18_itemcode2 = "11008152";
		int k18_price2 = 1000;
		int k18_amount2 = 1;
		
		String k18_itemname3 = "매직흡착 인테리어후크(알루미늄타입)";
		String k18_itemcode3 = "1020800";
		int k18_price3 = 1000;
		int k18_amount3 = 1;
		
		// #2. 상품 정보 어레이 리스트에 저장하기
		ArrayList<String> k18_itemnameAll = new ArrayList<String>(); // 상품명
		k18_itemnameAll.add(k18_itemname1);
		k18_itemnameAll.add(k18_itemname2);
		k18_itemnameAll.add(k18_itemname3);
		ArrayList<String> k18_itemcodeAll = new ArrayList<String>(); // 상품 코드
		k18_itemcodeAll.add(k18_itemcode1);
		k18_itemcodeAll.add(k18_itemcode2);
		k18_itemcodeAll.add(k18_itemcode3);
		ArrayList<Integer> k18_itempriceAll = new ArrayList<Integer>(); // 상품 단가
		k18_itempriceAll.add(k18_price1);
		k18_itempriceAll.add(k18_price2);
		k18_itempriceAll.add(k18_price3);
		ArrayList<Integer> k18_itemamountAll = new ArrayList<Integer>(); // 상품 수량
		k18_itemamountAll.add(k18_amount1);
		k18_itemamountAll.add(k18_amount2);
		k18_itemamountAll.add(k18_amount3);
		
		// #3. 상품 총 소비자 가격, 부가세, 과세 전 가격 계산
		int k18_totalPrice = 0;			// 정수 변수 k18_totalPrice (총 소비자 가격) 선언 및 0으로 초기화.
		int k18_tax;					// 정수 변수 k18_tax 선언 (총 부과세)
		int k18_netPrice;				// 정수 변수 k18_netPrice 선언 (총 세전 가격)
		float k18_TaxRate = (float)0.1;	// 실수 변수 k18_TaxRate 선언 및 0.1 (10%)로 초기화.
		
		// #3-1. 상품 총 소비자 가격 계산
		for (int k18_i = 0; k18_i < k18_itempriceAll.size(); k18_i++) {
			k18_totalPrice += (k18_itempriceAll.get(k18_i)*k18_itemamountAll.get(k18_i));
		}
		
		// #3-2. 세전 가격 계산 ( 참고 : 부가세 10%)
		k18_netPrice = (int)(k18_totalPrice/(1+k18_TaxRate)); 
		// 세전 가격 = 세후가격/(1+부가세율) => 소수점 아래 절삭하여 정수형으로 변환;
		
		// #3-3. 부가세 계산
		k18_tax = k18_totalPrice - k18_netPrice;		
		// 부가세 = 소비자가격 - 세전 가격;
		// 세전 가격 계산 후 소수점 아래 절삭(버림)하였으므로, 부가세는 반대로 소수점 아래에서 올림 처리한 것이 됨.		
		
		// #4. 출력 상품명 글자수 지정하기
		ArrayList<String> k18_itemnamePrinteAll = new ArrayList<String>();			// 출력용 상품명 저장할 ArrayList 선언.
		int k18_printLength = 22;													// 출력할 상품명 길이(byte) 지정
		for (int k18_i = 0; k18_i < k18_itemnameAll.size(); k18_i++) { 				// 상품 종류만큼 for문 반복
			byte[] k18_byteString  = k18_itemnameAll.get(k18_i).getBytes(); 		// 상품명을 16진수로 변환하여 byte배열에 저장.
			if (k18_byteString.length == k18_printLength) {							// 조건(1) : 상품명 길이 == 출력 지정 길이
				k18_itemnamePrinteAll.add(k18_itemnameAll.get(k18_i));				// 실행 : 상품명 그대로 출력용 ArrayList에 저장
			} else if (k18_byteString.length < k18_printLength) { 					// 조건(2) : 상품명 길이 < 출력 지정 길이
				int k18_diff = k18_printLength - k18_byteString.length;				// 실행 : 출력할 상품명에 byte차이만큼 공백 추가 (공백 = 1byte)
				String k18_blank = "";
				for (int k18_j = 0; k18_j < k18_diff; k18_j++) {
					k18_blank = k18_blank + " ";
				}
				k18_itemnamePrinteAll.add(k18_itemnameAll.get(k18_i)+k18_blank);
			} else {																// 조건(3) : 상품명 길이 > 출력 지정 길이
				int k18_minusByteCount = 0;											// 실행 : 상품명에서 잘라야하는 길이(byte) 계산
				for (int k18_j=0; k18_j < k18_printLength; k18_j++) {
					if ( k18_byteString[k18_j] < 0) {
						k18_minusByteCount += 1;
					} else {
						k18_minusByteCount += 0;
					}
				}
				if (k18_minusByteCount%2 != 0) { // k18_minusByteCount가 홀수인 경우 
					k18_itemnamePrinteAll.add(new String(k18_byteString, 0, k18_printLength-1)+" ");
					// 출력 지정 길이보다 1byte 작게 출력용 상품명 길이 지정 (출력 지정 byte - 1;)
					// 지정 길이(Byte)에 맞추어 상품명 자른 후, String으로 변환하고 공백(1byte) 더하기
					// -> 출력용 이름 배열(k18_itemnamePrint)에 저장
				} else {						// k18_minusByteCount가 짝수인 경우 
					k18_itemnamePrinteAll.add(new String(k18_byteString, 0, k18_printLength));
					// 출력 지정 길이(Byte)에 맞추어 상품명 자른 후, String으로 변환하고 공백(1byte) 더하기
					// -> 출력용 이름 배열(k18_itemnamePrint)에 저장
				}
			}
		}
			

		
		// #5. 숫자 세 개 단위로 콤마 찍기 위해 DecimalFormat 클래스 객체화 -> k18_Comma
		DecimalFormat k18_comma = new DecimalFormat("###,###,###");
		
		// #6. 현재 날짜&시간 찍기 -> Calendar & SimpleDateFormat 사용
		// Calendar형 객체 선언 및 인스턴스화.
		Calendar k18_cal = Calendar.getInstance();
		
		SimpleDateFormat k18_sdf = new SimpleDateFormat ("YYYY.MM.dd HH:mm:ss");
		SimpleDateFormat k18_sdf2 = new SimpleDateFormat ("MM월dd일");
		// SimpleDateFormat 형 객체 선언 및 형식 초기화 ("MM월dd일")
		
		System.out.printf("%26s\n","\"국민가게, 다이소\"");
		System.out.printf("%s\n", "(주)아성다이소_분당서현점");
		System.out.printf("%-3s%-44s\n", "전화:","031-702-6016");
		System.out.printf("%-3s%-44s\n", "본사:","서울 강남구 남부순환로 2748 (도곡동)");
		System.out.printf("%-3s%-44s\n", "대표:","박정부,신호섭 213-81-52063");
		System.out.printf("%-3s%-44s\n", "매장:","경기도 성남시 분당구 분당로53번길 11 (서현");
		System.out.printf("%s\n", "동)");
		System.out.printf("================================================\n"); // 기호 : 48개
		System.out.printf("%27s\n","소비자중심경영(CCM) 인증기업");
		System.out.printf("%29s\n","ISO 9001 품질경영시스템 인증기업");
		System.out.printf("================================================\n"); // 기호 : 48개
		System.out.printf("%19s%4s%3s\n","교환/환불 14일(",k18_sdf2.format(k18_cal.getTime()),")이내,");
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// !!!!환불 가능 일자 계산식 고민해보기!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.printf("%28s\n","(전자)영수증, 결제카드 지참 후 구입매장에서 가능");
		System.out.printf("%27s\n","포장/가격 택 훼손시 교환/환불 불가");
		System.out.printf("%27s\n","체크카드 취소 시 최대 7일 소요");
		System.out.printf("================================================\n"); // 기호 : 48개
		// 현재 날짜 및 시간 출력 시, SimpleDateFormat 사용하며 Calendar 함수를 통해 현재 날짜&시간 받아옴.
		System.out.printf("%-15s%33s\n", "[POS 1058231]", k18_sdf.format(k18_cal.getTime()));
		// 금액 출력 시, DecimalFormat 사용하여 숫자에 콤마 찍음 => 문자열로 형 변환되어 %s 형식으로 출력
		System.out.printf("================================================\n"); // 기호 : 48개
		for (int k18_i = 0; k18_i < k18_itempriceAll.size(); k18_i++) {
			System.out.printf("%-13s%11s%3s%11s\n", k18_itemnamePrinteAll.get(k18_i),
								k18_comma.format(k18_itempriceAll.get(k18_i)),
								k18_comma.format(k18_itemamountAll.get(k18_i)),
								k18_comma.format(k18_itempriceAll.get(k18_i) * k18_itemamountAll.get(k18_i)));
			System.out.printf("[%s]\n",k18_itemcodeAll.get(k18_i));
		}
		System.out.printf("%15s%29s\n", "과세합계", k18_comma.format(k18_netPrice));
		System.out.printf("%15s%29s\n", "　부가세", k18_comma.format(k18_tax));		
		System.out.printf("------------------------------------------------\n"); // 기호 : 48개
		System.out.printf("%-33s%11s\n", "판매합계", k18_comma.format(k18_totalPrice));
		System.out.printf("================================================\n"); // 기호 : 48개
		System.out.printf("%-33s%11s\n", "신용카드", k18_comma.format(k18_totalPrice));
		System.out.printf("------------------------------------------------\n"); // 기호 : 48개
		System.out.printf("%-15s%29s\n", "우리카드", "538720**********");
		System.out.printf("%-4s%1s%-12s%17s%s%s\n", "승인번호"," ","77982843(0)","승인금액"," ",k18_comma.format(k18_totalPrice));
		System.out.printf("================================================\n"); // 기호 : 48개
		// 현재 날짜 및 시간 출력 시, SimpleDateFormat 사용하며 Calendar 함수를 통해 현재 날짜&시간 받아옴.
		System.out.printf("%28s%1s%5s\n", k18_sdf.format(k18_cal.getTime())," ","분당서현점");
		System.out.printf("%-8s%-20s\n", "상품 및 기타 문의 : ","1522-4400");
		System.out.printf("%-8s%-20s\n", "멤버십 및 샵다이소 관련 문의 : ","1599-2211");
		System.out.printf("%25s\n", "영수증 바코드 생략");
		System.out.printf("%32s\n", "2112820610158231");
		System.out.printf("------------------------------------------------\n"); // 기호 : 48개
		System.out.printf("%27s\n","◈ 다이소 멤버십 앱 또는 홈페이지에 접속하셔서");
		System.out.printf("%27s\n","회원가입 후 다양한 혜택을 누려보세요! ◈");
			
	}
}
