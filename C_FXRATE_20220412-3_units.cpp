// Exchange Korean Won for foreign currency. (FXRate as of April 11th, 2022)
// foreign currency : USD, JPY, EUR, CNY, GBP
// print out the exchange result (exchanged foreing currency & refunded Korean Won changes)
// print out the Korean Won changes in currency units (1,000won / 500won / 100won/ 50won/ 10won)
// print out the exchanged Foreign Currency in currency units (e.g. 1000USD X 2unit, 500USD X 1unit, ...)
// use "if" to select the foreign currency to exchange
// use "do while" to return to select currency process when the user choose the wrong number

#include <stdio.h>
#include <string.h>

int main () {
	const float FXRATE_USD = 1233.5, FXRATE_JPY = 9.87, FXRATE_EUR = 1342.42, 
				FXRATE_CNY = 193.21, FXRATE_GBP = 1604.97; // 각 외화의 환율 선언 
	float appliedFXRATE = 0; 			// if 조건문 활용하여 선택하는 외화의 환율을 해당 변수에 할당 예정 
	int inputKRW = 0; 					// scanf로 받을 원화 
	int inputNumber = 0;  				// scanf로 받을 외화 선택 
	float outputForeignCurrency = 0; 	// 계산하여 나올 외화 환전 값 
	int outputForeignCurrencyFinal = 0; // 계산하여 나올 외화 환전 값 (10 or 100 단위 아래 절삭) 
	int returnKRW = 0; 					// 원화 거스름돈 
	int returnKRWFinal = 0; 			// 원화 거스름돈 (10원 단위 아래 절삭) 
	int returnKRW1000 = 0, returnKRW500 = 0, returnKRW100 = 0, returnKRW50 = 0, returnKRW10 = 0;
										// 원화 거스름돈 출력 단위 
	int changeForeignCurrency10000 = 0, changeForeignCurrency5000 = 0, changeForeignCurrency1000 = 0,
		changeForeignCurrency500 = 0, changeForeignCurrency200 = 0, changeForeignCurrency100 = 0,
		changeForeignCurrency50 = 0, changeForeignCurrency20 = 0, changeForeignCurrency10 = 0, 
		changeForeignCurrency5 = 0; 	// 외화 거스름돈 출력 단위 
	char *currencyName; 				// if 조건문 활용하여 선택하는 외화의 이름 해당 변수에 할당 예정 

	while(true) { // 원화 입력 & 환전 계속 반복 
		printf("환전을 원하는 금액을 입력하세요(원화) : ");
		scanf("%d", &inputKRW);
		do {
			printf("환전을 원하는 외화 번호를 선택하세요. (1:USD, 2:JPY, 3:EURO, 4.CNY, 5:GBP) : ");
			scanf("%d", &inputNumber);
		} while (inputNumber < 1 || inputNumber > 5);
		printf("============================================================\n");
		
		if (inputNumber == 1) {
			appliedFXRATE = FXRATE_USD;
			currencyName = "달러";
		} else if (inputNumber == 2) {
			appliedFXRATE = FXRATE_JPY;
			currencyName = "엔"; 
		} else if (inputNumber == 3) {
			appliedFXRATE = FXRATE_EUR;
			currencyName = "유로";
		} else if (inputNumber == 4) {
			appliedFXRATE = FXRATE_CNY;
			currencyName = "위안";
		} else if (inputNumber == 5) {
			appliedFXRATE = FXRATE_GBP;
			currencyName = "파운드";
		}
		
		outputForeignCurrency = inputKRW / appliedFXRATE;
		if (inputNumber == 1 || inputNumber == 3 || inputNumber == 5) {
			outputForeignCurrencyFinal = ((int)outputForeignCurrency)/5*5;
		} else if (inputNumber == 2) {
			outputForeignCurrencyFinal = ((int)outputForeignCurrency)/100*100;
		} else if (inputNumber == 4) {
			outputForeignCurrencyFinal = ((int)outputForeignCurrency)/10*10;
		}	
		returnKRW = (int)(inputKRW - outputForeignCurrencyFinal * appliedFXRATE); 
		returnKRWFinal = returnKRW - returnKRW%10;
		
		returnKRW1000 = returnKRWFinal/1000;
		returnKRW500 = returnKRWFinal%1000/500;
		returnKRW100 = returnKRWFinal%1000%500/100;
		returnKRW50 = returnKRWFinal%1000%500%100/50;
		returnKRW10 = returnKRWFinal%1000%500%100%50/10;
		
		printf("기준 환율 : %10.2f\n", appliedFXRATE);
		printf("원금 : %d 원\n", inputKRW);
		printf("*환전 결과*\n");
		printf("환전 :  %d %s\n", outputForeignCurrencyFinal, currencyName);
	
		if (inputNumber ==1) {
			changeForeignCurrency1000 = outputForeignCurrencyFinal/1000;
			changeForeignCurrency500 = outputForeignCurrencyFinal%1000/500;
			changeForeignCurrency100 = outputForeignCurrencyFinal%1000%500/100;
			changeForeignCurrency50 = outputForeignCurrencyFinal%1000%500%100/50;
			changeForeignCurrency10 = outputForeignCurrencyFinal%1000%500%100%50/10;
			changeForeignCurrency5 = outputForeignCurrencyFinal%1000%500%100%50%10/5;
			printf(" ==> 1000%s X %d개, 500%s X %d개, 100%s X %d개, 50%s X %d개, 10%s X %d개\n, 5%s X %d\n"
					, currencyName, changeForeignCurrency1000 
					, currencyName, changeForeignCurrency500 
					, currencyName, changeForeignCurrency100
					, currencyName, changeForeignCurrency50
					, currencyName, changeForeignCurrency10
					, currencyName, changeForeignCurrency5);
		} else if (inputNumber == 2) {
			changeForeignCurrency10000 = outputForeignCurrencyFinal/10000;
			changeForeignCurrency5000 = outputForeignCurrencyFinal%10000/5000;
			changeForeignCurrency1000 = outputForeignCurrencyFinal%10000%5000/1000;
			changeForeignCurrency500 = outputForeignCurrencyFinal%10000%5000%1000/500;
			changeForeignCurrency100 = outputForeignCurrencyFinal%10000%5000%1000%500/100;
			printf(" ==> 10000%s X %d개, 5000%s X %d개, 1000%s X %d개, 500%s X %d개, 100%s X %d개\n"
					, currencyName, changeForeignCurrency10000
					, currencyName, changeForeignCurrency5000
					, currencyName, changeForeignCurrency1000
					, currencyName, changeForeignCurrency500
					, currencyName, changeForeignCurrency100);
		} else if (inputNumber == 3 ) {
			changeForeignCurrency500 = outputForeignCurrencyFinal/500;
			changeForeignCurrency200 = outputForeignCurrencyFinal%500/200;
			changeForeignCurrency100 = outputForeignCurrencyFinal%500%200/100;
			changeForeignCurrency50 = outputForeignCurrencyFinal%500%200%100/50;
			changeForeignCurrency20 = outputForeignCurrencyFinal%500%200%100%50/20;
			changeForeignCurrency10 = outputForeignCurrencyFinal%500%200%100%50%20/10;
			changeForeignCurrency5 = outputForeignCurrencyFinal%500%200%100%50%20%10/5;
			printf(" ==> 500%s X %d개, 200%s X %d개, 100%s X %d개, 50%s X %d개, 20%s x %d개, 10%s X %d개, 5%s X %d개\n"
					, currencyName, changeForeignCurrency500
					, currencyName, changeForeignCurrency200
					, currencyName, changeForeignCurrency100
					, currencyName, changeForeignCurrency50
					, currencyName, changeForeignCurrency20
					, currencyName, changeForeignCurrency10
					, currencyName, changeForeignCurrency5);
		} else if (inputNumber == 4) {
			changeForeignCurrency100 = outputForeignCurrencyFinal/100;
			changeForeignCurrency50 = outputForeignCurrencyFinal%100/50;
			changeForeignCurrency20 = outputForeignCurrencyFinal%100%50/20;
			changeForeignCurrency10 = outputForeignCurrencyFinal%100%50%20/10;
			printf(" ==> 100%s X %d개, 50%s X %d개, 20%s x %d개, 10%s X %d개\n"
					, currencyName, changeForeignCurrency100
					, currencyName, changeForeignCurrency50
					, currencyName, changeForeignCurrency20
					, currencyName, changeForeignCurrency10);
		} else if (inputNumber == 5) {
			changeForeignCurrency50 = outputForeignCurrencyFinal/50;
			changeForeignCurrency20 = outputForeignCurrencyFinal%50/20;
			changeForeignCurrency10 = outputForeignCurrencyFinal%50%20/10;
			changeForeignCurrency5 = outputForeignCurrencyFinal%50%20%10/5;
			printf(" ==> 50%s X %d개, 20%s x %d개, 10%s X %d개, 5%s X %d개\n"
					, currencyName, changeForeignCurrency50
					, currencyName, changeForeignCurrency20
					, currencyName, changeForeignCurrency10
					, currencyName, changeForeignCurrency5);
		}
	
		printf("거스름돈 : %d 원\n", returnKRWFinal);
		printf(" ==> 1000 원 X %d개, 500원 X %d개, 100원 X %d개, 50원 X %d개, 10원 X %d개\n"
					, returnKRW1000, returnKRW500, returnKRW100, returnKRW50, returnKRW10);
		printf("============================================================\n");
	}
		
	return 0;
}
