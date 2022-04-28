import java.text.DecimalFormat;		// 숫자 출력 형식에 콤마를 추가하기 위한 클래스
import java.text.SimpleDateFormat;	// 현재 날짜,시간 출력 양식 지정 위한 클래스
import java.util.Calendar;			// 현재 날짜,시간 받아오기 위한 클래스
//KOPO18 서지윤 / 2022.04.26
//5강_리포팅 연습_#3-1. 영수증 (1) 한양김치찌개 (줄 맞추기 유의)
public class k18_receipt01 {

	public static void main(String[] args) {
		
		int k18_iPrice = 33000;			// 정수 변수 k18_iPrice (소비자 가격) 선언 및 33,000원으로 초기화
		int k18_TaxFinal;				// 정수 변수 k18_TaxFinal 선언 (세금)
		int k18_netPrice;				// 정수 변수 k18_netPrice 선언 (세전 가격)
		float k18_TaxRate = (float)0.1;	// 실수 변수 k18_TaxRate 선언 및 0.1 (10%)로 초기화.
		
		// #1. 세전 가격 계산 ( 참고 : 부가세 10%)
		k18_netPrice = (int)(k18_iPrice/(1+k18_TaxRate)); 
		// 세전 가격 = 세후가격/(1+부가세율) => 소수점 아래 절삭하여 정수형으로 변환;
		
		// #2. 부가세 계산
		k18_TaxFinal = k18_iPrice - k18_netPrice;		
		// 부가세 = 소비자가격 - 세전 가격;
		// 세전 가격 계산 후 소수점 아래 절삭(버림)하였으므로, 부가세는 반대로 소수점 아래에서 올림 처리한 것이 됨.		
		
		// #3. 숫자 세 개 단위로 콤마 찍기 위해 DecimalFormat 클래스 객체화 -> k18_Comma
		DecimalFormat k18_comma = new DecimalFormat("###,###,###");
		
		// #4. 현재 날짜&시간 찍기 -> Calendar & SimpleDateFormat 사용
		Calendar k18_cal = Calendar.getInstance();
		// Calendar형 객체 선언 및 인스턴스화.
		SimpleDateFormat k18_sdf = new SimpleDateFormat ("YYYY/MM/dd HH:mm:ss");
		// SimpleDateFormat 형 객체 선언 및 형식 초기화 ("연연연연/월월/일일 시시:분분:초초:)
		
		// #5. 출력 시작 
		// 출력 형식 지정 시 %와 s,d 등 형식 사이에 숫자를 넣어 출력 칸 조정 (양수 : 우측 정렬, 음수 : 좌측 정렬)
		System.out.printf("%s\n", "신용승인");
		System.out.printf("%-6s%-10s%25s\n", "단말기 : ","2N68665898","전표번호 : 041218");
		System.out.printf("%-6s%-10s\n", "가맹점 : ","한양김치찌개");
		System.out.printf("%-6s%-10s\n", "주　소 : ","경기 성남시 분당구 황새울로351번길 10 ,");
		System.out.printf("%s\n", "1층");
		System.out.printf("%-6s%-10s\n", "대표자 : ","유창신");
		System.out.printf("%-6s%-10s%27s\n", "사업자 : ","752-53-00558", "TEL : 7055695");
		System.out.printf("------------------------------------------------\n"); // 특수문자 : 48개
		// 금액 출력 시, DecimalFormat 사용하여 숫자에 콤마 찍음 => 문자열로 형 변환되어 %s 형식으로 출력
		System.out.printf("%-6s%37s%-2s\n", "금　액", k18_comma.format(k18_netPrice),"원"); 	// 세전 금액
		System.out.printf("%-6s%37s%-2s\n", "부가세", k18_comma.format(k18_TaxFinal),"원");	// 부가세
		System.out.printf("%-6s%37s%-2s\n", "합　계", k18_comma.format(k18_iPrice),"원");		// 세후 금액(소비자가)
		System.out.printf("================================================\n"); // 특수문자 : 48개
		System.out.printf("%s\n", "우리카드");
		System.out.printf("%-6s%-22s%12s\n", "카드번호 : ","5387-20**-****-4613(S)","일시불");
		// 현재 날짜 및 시간 출력 시, SimpleDateFormat 사용하며 Calendar 함수를 통해 현재 날짜&시간 받아옴.
		System.out.printf("%-6s%-20s\n", "거래일시 : ",k18_sdf.format(k18_cal.getTime()));
		System.out.printf("%-6s%-20s\n", "승인번호 : ","70404427");
		System.out.printf("%-6s%-20s\n", "거래번호 : ","357734873739");
		System.out.printf("%-5s%-12s%-5s%-12s\n", "매입 : ","비씨카드사","가맹 : ","720068568");
		System.out.printf("%-5s%-20s\n", "알림 : ","EDC매출표");
		System.out.printf("%-5s%-20s\n", "문의 : ","TEL)1544-4700");
		System.out.printf("================================================\n"); // 특수문자 : 48개
		System.out.printf("%26s\n","* 감사합니다 *");
		System.out.printf("%46s\n","표준V2.08_20200212");
			
	}
}
