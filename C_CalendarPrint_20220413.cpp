// 2022.04.13 -> 2022.04.14 
// 연&월 입력받아 달력 출력하기
// 참고: 1900년 1월 1일 = 월요일 

#include <stdio.h>

int main() {
	
	int inputYear;			// 입력 받은 연도 값. 
	int inputMonth;			// 입력 받은 월 값. 
	
	int leapYearNumber; 		// 1900년~(inputYear-1)까지 윤년 개수 
	int daysFrom1900; 		// 1900년~(inputYear-1)까지 총 날짜 개수 (1900.01.01 = 1) 
	int daysFromThisYear;		// inputYear의 첫 날 ~  (inputMonth-1)까지 총 일수. 
	int firstDayOfinputMonth; 	// 1900년~(inputYear inputMonth.01) 까지 총 일수. => inputYear-Month의 첫번째 날이 무슨 요일인지 계산 시 사용. 
	int daysOfinputMonth;		// 입력 받은 월의 날짜 개수. 
	
	int inputYearDays[12];		// inputYear의 월별 일수 입력할 배열. 
	int normalYearDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 	// 평년 
	int leapYearDays[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};		// 윤년 
	
	printf("연도 입력 : ");
	scanf("%d", &inputYear);
	printf("월 입력 : ");
	scanf("%d", &inputMonth);
	
	// ************************************************************
	// <inputYear, inputMonth의 첫 번째 요일 계산하기 시작!>
	
	// 1900년~ (inputYear-1) 까지 총 날짜 개수 (참고 : 1900.01.01 => 1, 1900.01.02 => 2) 
	// *참고) 1900년은 4의 배수이지만, 100의 배수이기도 하므로 & 400의 배수가 아니므로 윤년이 아님. 
	// 1. 1900년 ~  (inputYear-1) 까지 총 윤년 개수 구하기 
	leapYearNumber = 0;
	for (int index = 1900; index < inputYear; index++) {
		if ((index % 4 == 0 && index % 100 != 0) || index % 400 == 0) {
			leapYearNumber++;
		}
	}
	
//////////////////////////////////////////////////////////////////////	
//1. 1900년 ~  (inputYear-1) 까지 총 윤년 개수 구하기 (for문 쓰기 전 방법)
//if (inputYear > 2000) {
//	leapYearNumber = ((inputYear - 1900 - 1)/4 + 1) - ((inputYear - 1900 - 1)/100+1) + ((inputYear - 2000 - 1)/400+1);
//} else {
//	leapYearNumber = ((inputYear - 1900 - 1)/4 + 1) - ((inputYear - 1900 - 1)/100+1);
//}
//////////////////////////////////////////////////////////////////////
	
	// 2. 1900년 ~  (inputYear-1) 까지 총 날짜 수 구하기 (윤년 고려하여 윤년 수만큼 날짜 더해주기) 
	daysFrom1900 = 365*(inputYear - 1899 - 1) + (leapYearNumber * 1);
	
	// 3. inputYear 1월 1일 ~ (inputMonth-1)까지 날짜 개수 구하기 
	// 3-1. inputYear 윤년 or 평년 여부 확인하여 inputYear의 월별 날짜 수 넣기 
	if ( (inputYear%4 == 0 && inputYear%100 != 0) || inputYear%400 == 0) {
		for (int indexA = 1; indexA <= 12; indexA++) {
			inputYearDays[indexA-1] = leapYearDays[indexA-1];
		}
	} else {
		for (int indexB = 1; indexB <= 12; indexB++) {
			inputYearDays[indexB-1] = normalYearDays[indexB-1];
		}
	}
	// 3-2.  inputYear의 1월부터 ~ inputMonth 전 월까지 일수 더하기.
	if (inputMonth > 1) { 		// 1월이 아닌 경우 -> 1월 ~ (inputMonth-1)까지 월별 일수 더함. 
		daysFromThisYear = 0;
		for (int indexC = 1; indexC <= inputMonth-1; indexC++) {
			daysFromThisYear = daysFromThisYear + inputYearDays[indexC-1];
		}
	} else { 			// 1월인 경우 -> 0 
		daysFromThisYear = 0;
	}
	
	// 4. 1900년부터 inputYear의 inputMonth 첫 번째 날까지 총 날짜 수 (=> firstDayOfinputMonth)
	// (1900.01.01 = 1, 1900.01.2 = 2, ...) 
	firstDayOfinputMonth = daysFrom1900 + daysFromThisYear + 1; 
	// *참고) 1900.01.01 = 1 (월), 1900.01.2 = 2 (화), ...
	// ==> if (firstDayOfinputMonth % 7 == 1) ==> firstDayOfinputMonth가 월요일!
	// ==> 0일 1월 2화 3수 4목 5금 6토 
	
	// <inputYear, inputMonth의 첫 번째 요일 계산하기 끝!>
	// ************************************************************
	
	// inputYear의 inputMonth가 며칠까지 있는지 확인 ==> daysOfinputMonth
	// 윤년인 경우 & 평년인 경우 나누어서 확인 
	if ( (inputYear%4 == 0 && inputYear%100 != 0) || inputYear%400 == 0) {
		daysOfinputMonth = leapYearDays[inputMonth-1];
	} else {
		daysOfinputMonth = normalYearDays[inputMonth-1];
	}
	
	// **********달력 프린트 시작**********
	printf("\n======= %d년 %d월 =======\n", inputYear, inputMonth);
	printf("%5s%5s%5s%5s%5s%5s%5s\n","일","월","화","수","목","금","토");
	// 해당 월 시작 요일에 따라 공란 (' ') 출력.
	// (firstDayOfinputMonth % 7) => 0일 1월 2화 3수 4목 5금 6토 
	for (int indexD = 0; indexD < (firstDayOfinputMonth % 7); indexD++) {
		printf("%5c", ' ');
	}
	
	// 날짜 인쇄 => 토요일은 날짜 인쇄 후 개행
	// (indexE % 7) => 0일 1월 2화 3수 4목 5금 6토 
	for (int indexE = firstDayOfinputMonth; indexE < firstDayOfinputMonth+daysOfinputMonth; indexE++) {
		printf("%5d", indexE-firstDayOfinputMonth+1);
		if (indexE%7 == 6) { 	// 토요일
			printf("\n");
		}
	}
	// **********달력 프린트 종료********** 
	
	return 0;
} 
