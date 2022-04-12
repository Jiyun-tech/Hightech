//kopo18 ������
//�ǽ� ���� : �ѱ� ����, �� ���� ���

#include <stdio.h>
#include <time.h>

int main() {
	
	// ���� ��¥ �ҷ����� 
	time_t t = time(NULL);
	struct tm today = *localtime(&t);
	
	// ���� ���� 
	int inputBirthday = 0;					//�Է� ������� (�ֹε�Ϲ�ȣ ���ڸ�) 
	int birthdayYear = 0;					//�Է� ���� 
	int birthdayMonth = 0;					//�Է� ���� 
	int birthdayDay = 0;					//�Է� ���� 
	int koreanAge = 0;						//�ѱ� ���� 
	int internationalAge = 0;				//�� ���� 
	int yearToday = today.tm_year + 1900;	// ���� ��¥�� ���� 
	int monthToday = today.tm_mon + 1;		// ���� ��¥�� �� 
	int dayToday = today.tm_yday + 1;		// ���� ��¥�� �� (������ �� ��° ������) 
	int dayTodayArray [12];					// ���� �� ���� �ϼ� ���� �迭 
	int normalYear[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	// ��� 
	int leapYear[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};		// ���� 
	
	// ������� �Է� 
	do {
		printf("�ֹε�Ϲ�ȣ ���ڸ��� �Է��Ͻÿ�(6�ڸ� ����) : ");
		scanf("%d", &inputBirthday);
	} while (inputBirthday > 999999 || inputBirthday%10000/100 > 12 || inputBirthday%10000%100 > 31);
	
	// ����,��� ���ο� ���� ���� ������ �� Ȯ���Ͽ� dayTodayArray�� �־��ֱ� 
	if ( (yearToday%4 == 0 && yearToday%100 != 0) || yearToday%400==0) {
		for (int i = 0; i < 12; i++) {
			dayTodayArray[i] = leapYear[i];
		}
	} else {
		for (int i = 0; i < 12; i++) {
			dayTodayArray[i] = normalYear[i];
		}
	}
	
	// ������ �̹� �� �� ��° ������ Ȯ���ϱ� 
	for (int i = 0; i < monthToday-1; i++) {
		dayToday = dayToday - dayTodayArray[i];	
	}
	
	// ���� ��, ��, �� �и�. 
	// ������ 2000��� ������ 1900��� ������ �з�.
	birthdayYear = inputBirthday/10000;
	if (birthdayYear < 23) {
		birthdayYear = birthdayYear+2000;
	} else {
		birthdayYear = birthdayYear+1900;
	}
	birthdayMonth = inputBirthday%10000/100;
	birthdayDay = inputBirthday%100;
	
	// �ѱ� ���� ��� 
	koreanAge = yearToday - birthdayYear + 1;
	// �� ���� ��� 
	if (birthdayMonth < monthToday) {
		internationalAge = yearToday - birthdayYear;
	} else if (birthdayMonth == monthToday && birthdayDay <= dayToday) {
		internationalAge = yearToday - birthdayYear;
	} else {
		internationalAge = yearToday - birthdayYear - 1;
	}
	
	// ���� �� ��� 
	printf("����(%d�� %d�� %d��) ���� ���̴� %d��, �� ���̴� %d�� �Դϴ�."
		,yearToday, monthToday, dayToday, koreanAge, internationalAge);
	
	return 0;
}
