// Exchange Korean Won for foreign currency. (FXRate as of April 11th, 2022)
// foreign currency : USD, JPY, EUR, CNY, GBP
// print out the exchange result (exchanged foreing currency & refunded Korean Won changes)
// print out the Korean Won changes in currency units (1,000won / 500won / 100won/ 50won/ 10won)

#include <stdio.h>

int main () {
	const float FXRATE_USD = 1233.5, FXRATE_JPY = 9.87, FXRATE_EUR = 1342.42, 
				FXRATE_CNY = 193.21, FXRATE_GBP = 1604.97;
	int inputKRW = 0; int inputNumber = 0; int returnKRW = 0; int returnKRWFinal = 0;
	int returnKRW1000 = 0;
	int returnKRW500 = 0;
	int returnKRW100 = 0;
	int returnKRW50 = 0;
	int returnKRW10 = 0;
	
	printf("환전을 원하는 금액을 입력하세요(원화) : ");
	scanf("%d", &inputKRW);
	printf("====================\n");
	
	// KRW -> USD
	float outputUSD = inputKRW / FXRATE_USD;
	int outputUSDFinal = outputUSD/1;
	returnKRW = inputKRW - outputUSDFinal * FXRATE_USD; 
	returnKRWFinal = returnKRW- returnKRW%10;
	returnKRW1000 = returnKRWFinal/1000;
	returnKRWFinal = returnKRWFinal - returnKRW1000 * 1000;
	returnKRW500 = returnKRWFinal/500;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 500;
	returnKRW100 = returnKRWFinal/100;
	returnKRWFinal = returnKRWFinal - returnKRW100 * 100;
	returnKRW50 = returnKRWFinal/50;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 50;
	returnKRW10 = returnKRWFinal/10;
	printf("USD 기준 환율 : %10.2f\n", FXRATE_USD);
	printf("환전 결과\n");
	printf("달러 : %d 달러\n", outputUSDFinal);
	printf("거스름돈 : %d원 (1000 원X %d개, 500원X %d개, 100원 X %d개, 50원 X %d개, 10원 X %d개)\n"
			,returnKRWFinal, returnKRW1000, returnKRW500, returnKRW100, returnKRW50, returnKRW10);
	printf("====================\n");

	// KRW -> JPY
	float outputJPY = inputKRW / FXRATE_JPY;
	int outputJPYFinal = outputJPY/1;
	returnKRW = inputKRW - outputJPYFinal * FXRATE_JPY; 
	returnKRWFinal = returnKRW- returnKRW%10;
	returnKRW1000 = returnKRWFinal/1000;
	returnKRWFinal = returnKRWFinal - returnKRW1000 * 1000;
	returnKRW500 = returnKRWFinal/500;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 500;
	returnKRW100 = returnKRWFinal/100;
	returnKRWFinal = returnKRWFinal - returnKRW100 * 100;
	returnKRW50 = returnKRWFinal/50;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 50;
	returnKRW10 = returnKRWFinal/10;
	printf("JPY 기준 환율 : %10.2f\n", FXRATE_JPY);
	printf("환전 결과\n");
	printf("엔 :  %d 엔\n", outputJPYFinal);
	printf("거스름돈 : %d원 (1000 원X %d개, 500원X %d개, 100원 X %d개, 50원 X %d개, 10원 X %d개)\n"
			,returnKRWFinal, returnKRW1000, returnKRW500, returnKRW100, returnKRW50, returnKRW10);
	printf("====================\n");

	// KRW -> EUR
	float outputEUR = inputKRW / FXRATE_EUR;
	int outputEURFinal = outputEUR/1;
	returnKRW = inputKRW - outputEURFinal * FXRATE_EUR; 
	returnKRWFinal = returnKRW- returnKRW%10;
	returnKRW1000 = returnKRWFinal/1000;
	returnKRWFinal = returnKRWFinal - returnKRW1000 * 1000;
	returnKRW500 = returnKRWFinal/500;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 500;
	returnKRW100 = returnKRWFinal/100;
	returnKRWFinal = returnKRWFinal - returnKRW100 * 100;
	returnKRW50 = returnKRWFinal/50;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 50;
	returnKRW10 = returnKRWFinal/10;
	printf("EUR 기준 환율 : %10.2f\n", FXRATE_EUR);
	printf("환전 결\n");
	printf("유로 : %d 유로\n", outputEURFinal);
	printf("거스름돈 : %d원 (1000 원X %d개, 500원X %d개, 100원 X %d개, 50원 X %d개, 10원 X %d개)\n"
			,returnKRWFinal, returnKRW1000, returnKRW500, returnKRW100, returnKRW50, returnKRW10);
	printf("====================\n");	 

	// KRW -> CNY
	float outputCNY = inputKRW / FXRATE_CNY;
	int outputCNYFinal = outputCNY/1;
	returnKRW = inputKRW - outputCNYFinal * FXRATE_CNY; 
	returnKRWFinal = returnKRW- returnKRW%10;
	returnKRW1000 = returnKRWFinal/1000;
	returnKRWFinal = returnKRWFinal - returnKRW1000 * 1000;
	returnKRW500 = returnKRWFinal/500;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 500;
	returnKRW100 = returnKRWFinal/100;
	returnKRWFinal = returnKRWFinal - returnKRW100 * 100;
	returnKRW50 = returnKRWFinal/50;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 50;
	returnKRW10 = returnKRWFinal/10;
	printf("CNY 기준 환율 : %10.2f\n", FXRATE_CNY);
	printf("환전 결과\n");
	printf("위안 : %d 위안\n", outputCNYFinal);
	printf("거스름돈 : %d원 (1000 원X %d개, 500원X %d개, 100원 X %d개, 50원 X %d개, 10원 X %d개)\n"
			,returnKRWFinal, returnKRW1000, returnKRW500, returnKRW100, returnKRW50, returnKRW10);
	printf("====================\n");

	// KRW -> GBP
	float outputGBP = inputKRW / FXRATE_GBP;
	int outputGBPFinal = outputGBP/1;
	returnKRW = inputKRW - outputGBPFinal * FXRATE_GBP; 
	returnKRWFinal = returnKRW - returnKRW%10;
	returnKRW1000 = returnKRWFinal/1000;
	returnKRWFinal = returnKRWFinal - returnKRW1000 * 1000;
	returnKRW500 = returnKRWFinal/500;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 500;
	returnKRW100 = returnKRWFinal/100;
	returnKRWFinal = returnKRWFinal - returnKRW100 * 100;
	returnKRW50 = returnKRWFinal/50;
	returnKRWFinal = returnKRWFinal - returnKRW500 * 50;
	returnKRW10 = returnKRWFinal/10;
	printf("GBP 기준 환율 : %10.2f\n", FXRATE_GBP);
	printf("환전 결과\n");
	printf("파운드 : %d 파운드\n", outputGBPFinal);
	printf("거스름돈 : %d원 (1000 원X %d개, 500원X %d개, 100원 X %d개, 50원 X %d개, 10원 X %d개)\n"
			,returnKRWFinal, returnKRW1000, returnKRW500, returnKRW100, returnKRW50, returnKRW10);
}
