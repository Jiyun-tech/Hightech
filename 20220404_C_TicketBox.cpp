// 2022.04.14
// Ticket Box program for a theme park.
// Ư�̻��� : 12���� �̸��� ��� �̿�ǿ� ���Ͽ� ���� & 12���� �̻�~36���� �̸��� ��� �̿�ǿ� ���Ͽ� 1,5000��

#include <stdio.h>
#include <time.h> 

int main () {
	
	// ���� ��¥ �ҷ����� 
	time_t t = time(NULL);
	struct tm today = *localtime(&t);
	int todayYear = today.tm_year + 1900;
	int todayMonth = today.tm_mon + 1;
	int todayDay = today.tm_mday;
	
	// ��� ��� ���� ����
	const int PRICE_ALL_1DAY_ADULT = 62000, PRICE_ALL_1DAY_JUVENILE = 54000, PRICE_ALL_1DAY_CHILD = 47000, PRICE_ALL_1DAY_BABY=15000, PRICE_ALL_1DAY_NEWBORN = 0;
	const int PRICE_ALL_AFTER4_ADULT = 50000, PRICE_ALL_AFTER4_JUVENILE = 43000, PRICE_ALL_AFTER4_CHILD = 36000, PRICE_ALL_AFTER4_BABY = 15000, PRICE_ALL_AFTER4_NEWBORN = 0;  
	const int PRICE_PARK_1DAY_ADULT = 59000, PRICE_PARK_1DAY_JUVENILE = 52000, PRICE_PARK_1DAY_CHILD = 46000, PRICE_PARK_1DAY_BABY = 15000, PRICE_PARK_1DAY_NEWBORN = 0;
	const int PRICE_PARK_AFTER4_ADULT = 47000, PRICE_PARK_AFTER4_JUVENILE = 41000, PRICE_PARK_AFTER4_CHILD = 35000, PRICE_PARK_AFTER4_BABY = 15000, PRICE_PARK_AFTER4_NEWBORN = 0; 
	int priceArray[4]; // ���� ���ÿ� ���� ��� �Է��� �迭 
	
	// ���� ��� ���� ���� 
	const int ELDER_AGE_MIN = 65;
	const int ADULT_AGE_MAX = 64, ADULT_AGE_MIN = 19;
	const int JUVENILE_AGE_MAX = 18, JUVERNILE_AGE_MIN = 13;
	const int CHILD_AGE_MAX = 12, CHILD_AGE_MIN = 3;
	const int BABY_AGE_MAX = 2, BABY_AGE_MIN = 1; 
	const int NEWBORN_AGE = 0;
	const int INDEX_MALE_OLD = 1, INDEX_FEMALE_OLD = 2, INDEX_MALE_NEW = 3, INDEX_FEMALE_NEW = 4; 
	const int BIRTH_YEAR_ADD_OLD = 1900, BIRTH_YEAR_ADD_NEW = 2000;
	
	// ������ 
	const float BENEFIT_NON = 0, BENEFIT_DISABLED = 0.5, BENEFIT_NATIONAL_MERIT = 0.5, BENEFIT_PREGNANT = 0.5, BENEFIT_ARMY = 0.49, BENEFIT_MULTICHILD = 0.3;
	const int  BENEFIT_NON_MAX = 0, BENEFIT_DISABLED_MAX = 2, BENEFIT_NATIONAL_MERIT_MAX = 2, BENEFIT_PREGNANT_MAX = 1, BENEFIT_ARMY_MAX = 2, BENEFIT_MULTICHILD_MAX = 1;

	// �Է� ���� ����
	int inputTicketSelect; 		// ���� ���� 
	char inputSocialID[7]; 		// ����� �ֹε�Ϲ�ȣ 
	int inputNumberOfTicket;	// Ƽ�� ���� 
	int inputBenefit; 			// ������ 
	
	// ��� ���� ���� 
	int birthYearIndex; 		// 1900��� & 2000���� ����
	int userBirthYear;			// ����� ���� 
	int userBirthMonth;			// ����� ���� 
	int userBirthDay;			// ����� ���� 
	int userAge;				// **����� ���� 
	int ticketPrice;			// **���� & ���̿� ���� Ƽ�� ���� 
	int totalTicketPrice;		// **Ƽ�� ���� X ���� 
	float appliedBenefit;		// ����� ������ �ݾ� (������ ���� �ݾ� ��� ������ �޾ƿ� ����) 
	int discountedTicketPrice;	// **������ �������� ���� ���� �ݾ�
	int discountedTicketNumber; // **������ ����� Ƽ�� ���� 
	int finalTicketPrice;		// **totalTicketPrice - discountedTicketPrice
	int continueTicketPurchase; // Ƽ�� �߱� ���μ��� �ݺ� ���� ���� 
	int NewPurchase;			// ���ο� �ֹ� ���� ���� ���� 
	
	
	do {
	// ���� ������ ���� ����
	int accumulatedFinalPrice = 0; // �� �ֹ� �ݾ� 
	int accumulatedTicketNumber = 0; // �� �ֹ� Ƽ�� ���� 
	
	
	// Ƽ�� �ֹ� ���� 
	do { // ~ while => return to beginning of the process if the user select "continue purchasing ticket" at the end of the process. 
	
		do {
			printf("������ �����ϼ���.\n\t1. �����̿�� - �ְ���(1DAY)\n\t2. �����̿�� - �߰���(AFTER4)\n\t3. ��ũ�̿�� - �ְ���(1DAY)\n\t4. ��ũ�̿�� - �߰���(AFTER4)\n\t => ");
			scanf("%d", &inputTicketSelect);
		} while (inputTicketSelect < 1 || inputTicketSelect > 4); 
		// 1~4 �� ���� �� ����â �����. 
		
		do {
		printf("Ƽ�� ������� �ֹε�Ϲ�ȣ 7�ڸ��� �Է��ϼ���.(�� 6�ڸ� & �� 1�ڸ�)\n\t => ");
		scanf("%s", &inputSocialID);
		} while ( ((inputSocialID[2]-48)*10 + (inputSocialID[3]-48)) > 12 || ((inputSocialID[4]-48)*10 + (inputSocialID[5]-48)) > 31 || inputSocialID[6]-48 > 4 );
		
		printf("�ֹ� ������ �Է��ϼ���.\n\t => ");
		scanf("%d", &inputNumberOfTicket);
		
		do {
			printf("�������� �����ϼ���.\n\t1. ���� (���� ���� �ڵ� ó��)\n\t2. �����\n\t3. ����������\n\t4. �ӻ�� (�����̿�Ǹ� �ش�)\n\t5. �ް��庴 (�����̿�Ǹ� �ش�)\n\t6. ���ڳ� (�����̿�Ǹ� �ش�)\n\t => ");
			scanf("%d", &inputBenefit);
		} while (inputBenefit < 1 || inputBenefit > 6);
		// 1~6 �� ���� �� ����â �����. 
		
		// ���� ���ÿ� ���� Price List  
		switch (inputTicketSelect) {
			case 1:
				priceArray[0] = PRICE_ALL_1DAY_ADULT;
				priceArray[1] = PRICE_ALL_1DAY_JUVENILE;
				priceArray[2] = PRICE_ALL_1DAY_CHILD;
				priceArray[3] = PRICE_ALL_1DAY_BABY;
				priceArray[4] = PRICE_ALL_1DAY_NEWBORN;
				break;
			case 2:
				priceArray[0] = PRICE_ALL_AFTER4_ADULT;
				priceArray[1] = PRICE_ALL_AFTER4_JUVENILE;
				priceArray[2] = PRICE_ALL_AFTER4_CHILD;
				priceArray[3] = PRICE_ALL_AFTER4_BABY;
				priceArray[4] = PRICE_ALL_AFTER4_NEWBORN;
				break;
			case 3:
				priceArray[0] = PRICE_PARK_1DAY_ADULT;
				priceArray[1] = PRICE_PARK_1DAY_JUVENILE;
				priceArray[2] = PRICE_PARK_1DAY_CHILD;
				priceArray[3] = PRICE_PARK_1DAY_BABY;
				priceArray[4] = PRICE_PARK_1DAY_NEWBORN;
				break;
			case 4:
				priceArray[0] = PRICE_PARK_AFTER4_ADULT;
				priceArray[1] = PRICE_PARK_AFTER4_JUVENILE;
				priceArray[2] = PRICE_PARK_AFTER4_CHILD;
				priceArray[4] = PRICE_PARK_AFTER4_NEWBORN;
				break;
			default :
				break;
		}
		
		// International Age of the user. (-48 ==> change ASCII code to Decimal number)
		birthYearIndex = inputSocialID[6]-48;
		if (birthYearIndex == INDEX_MALE_OLD || birthYearIndex == INDEX_FEMALE_OLD) {
			userBirthYear = (inputSocialID[0]-48)*10 + (inputSocialID[1]-48) + BIRTH_YEAR_ADD_OLD;
		} else if (birthYearIndex == INDEX_MALE_NEW || birthYearIndex == INDEX_FEMALE_NEW) {
			userBirthYear = (inputSocialID[0]-48)*10 + (inputSocialID[1]-48) + BIRTH_YEAR_ADD_NEW;
		} 
		userBirthMonth = (inputSocialID[2]-48)*10 + (inputSocialID[3]-48);
		userBirthDay = (inputSocialID[4]-48)*10 + (inputSocialID[5]-48);
		if (userBirthMonth < todayMonth || (userBirthMonth == todayMonth && userBirthDay <= todayDay)) {
			userAge = (todayYear) - userBirthYear;
		} else {
			userAge = (todayYear) - userBirthYear - 1;
		}
		
		// Confirm the price ticket considering age of the user.
		if (userAge >= ADULT_AGE_MIN  && userAge <= ADULT_AGE_MAX) {
			ticketPrice = priceArray[0];
		} if (userAge >= JUVERNILE_AGE_MIN && userAge <= JUVENILE_AGE_MAX) {
			ticketPrice = priceArray[1];
		} if ((userAge >= CHILD_AGE_MIN && userAge < CHILD_AGE_MAX) || userAge >= ELDER_AGE_MIN) {
			ticketPrice = priceArray[2];
		} if ((userAge >= BABY_AGE_MIN && userAge <= BABY_AGE_MAX )) {
			ticketPrice = priceArray[3];
		} if (userAge <= NEWBORN_AGE) {
			ticketPrice = priceArray[4];
		}
		
		// Ticket price before applying discount benefit. 
		totalTicketPrice = ticketPrice * inputNumberOfTicket;
		
		// Check the benefit and discounted amount.
		if (userAge >= ELDER_AGE_MIN || userAge <= NEWBORN_AGE) {		// in case the user is older than 64. (above adult age range) => no other benebits can be applied.
			appliedBenefit = BENEFIT_NON;
			discountedTicketNumber = BENEFIT_NON_MAX;
		} else {
			if (inputTicketSelect == 1 || inputTicketSelect == 2) { // ���� �̿�� ������ ��� 
					if (inputBenefit == 1) { 	// ������ ���� 
					appliedBenefit = BENEFIT_NON;
					discountedTicketNumber = BENEFIT_NON_MAX;
				}
				if (inputBenefit == 2) { 		// ����� 
					appliedBenefit = BENEFIT_DISABLED;
					if (inputNumberOfTicket <= BENEFIT_DISABLED_MAX) {
						discountedTicketNumber = inputNumberOfTicket;
					} else {
						discountedTicketNumber = BENEFIT_DISABLED_MAX;
					}
				}
				if (inputBenefit == 3) { 		// ���������� 
					appliedBenefit = BENEFIT_NATIONAL_MERIT;
					if (inputNumberOfTicket <= BENEFIT_NATIONAL_MERIT_MAX) {
						discountedTicketNumber = inputNumberOfTicket;
					} else {
						discountedTicketNumber = BENEFIT_NATIONAL_MERIT_MAX;
					}
				} 
				if (inputBenefit == 4) { 		// �ӻ�� 
					appliedBenefit = BENEFIT_PREGNANT;
					if (inputNumberOfTicket <= BENEFIT_PREGNANT_MAX) {
						discountedTicketNumber = inputNumberOfTicket;
					} else {
						discountedTicketNumber = BENEFIT_PREGNANT_MAX;
					}
				}
				if (inputBenefit == 5) { 		// �ް��庴 
					appliedBenefit = BENEFIT_ARMY;
					if (inputNumberOfTicket <= BENEFIT_ARMY_MAX) {
						discountedTicketNumber = inputNumberOfTicket;
					} else {
						discountedTicketNumber =  BENEFIT_ARMY_MAX;
					}
				}
				if (inputBenefit == 6) { 		//���ڳ� 
					appliedBenefit = BENEFIT_MULTICHILD;
					if (inputNumberOfTicket <= BENEFIT_MULTICHILD_MAX) {
						discountedTicketNumber = inputNumberOfTicket;
					} else {
						discountedTicketNumber = BENEFIT_MULTICHILD_MAX;
					}
				}	
			} else if (inputTicketSelect == 3 || inputTicketSelect == 4) { // ��ũ �̿�� ������ ��� 
				if (inputBenefit == 1 || inputBenefit == 4 || inputBenefit == 5 || inputBenefit == 6) { 
					appliedBenefit = BENEFIT_NON;
					discountedTicketNumber = BENEFIT_NON_MAX;
				}
				if (inputBenefit == 2) { 		// ����� & ���������� ��� �� ������ ��ũ�̿�ǿ� ���� ����. 
					appliedBenefit = BENEFIT_DISABLED;
					if (inputNumberOfTicket <= BENEFIT_DISABLED_MAX) {
						discountedTicketNumber = inputNumberOfTicket;
					} else {
						discountedTicketNumber = BENEFIT_DISABLED_MAX;
					}
				}
				if (inputBenefit == 3) { 		// ����� & ���������� ��� �� ������ ��ũ�̿�ǿ� ���� ����. 
					appliedBenefit = BENEFIT_NATIONAL_MERIT;
					if (inputNumberOfTicket <= BENEFIT_NATIONAL_MERIT_MAX) {
						discountedTicketNumber = inputNumberOfTicket;
					} else {
						discountedTicketNumber = BENEFIT_NATIONAL_MERIT_MAX;
					}
				} 
			}
		}	
		discountedTicketPrice = (ticketPrice * appliedBenefit * discountedTicketNumber);
		
		// final ticket price after the benefit is applied.
		finalTicketPrice = totalTicketPrice - discountedTicketPrice;
		// total ticket price & number(accumulation)
		accumulatedFinalPrice = accumulatedFinalPrice + finalTicketPrice;
		accumulatedTicketNumber = accumulatedTicketNumber + inputNumberOfTicket;
		
		// print the final receipt.
		printf("\n****************************************\n");
		printf("����� ���� : %d ��\n", userAge);
		printf("�� Ƽ�� ���� : %d ��\n", inputNumberOfTicket);
		printf("���� ���� : %d �� (%d �� x %d ��)\n", totalTicketPrice, ticketPrice, inputNumberOfTicket);
		printf("��� �ݾ� : %d �� (%d �� x %d ��)\n", discountedTicketPrice, (int)(ticketPrice * appliedBenefit), discountedTicketNumber);
		printf("�����Ͻ� Ƽ�� ������ �� %d ���Դϴ�.\n", finalTicketPrice); 
		printf("����Ͻ� ���� �ݾ��� �� %d ���Դϴ�.\n", accumulatedFinalPrice);
		printf("****************************************\n\n");
		
		printf("��� �߱��Ͻðڽ��ϱ�?\n\t1.Ƽ�� �߱�\n\t2.����\n\t ==> ");
		scanf("%d", &continueTicketPurchase);
		
	} while (continueTicketPurchase == 1); 
		
		printf("****************************************\n");
		printf("�����Ͻ� Ƽ�� ������ �� %d ���Դϴ�.\n", accumulatedTicketNumber);
		printf("����Ͻ� �ݾ��� �� %d ���Դϴ�.\n", accumulatedFinalPrice);
		printf("****************************************\n");
		printf("\n========================================\n");
		
		printf("��� ���� ���θ� �����Ͻʽÿ�. (1. ���ο� �ֹ�, 2. ���α׷� ����)\n\t ==> ");
		scanf("%d", &NewPurchase);
		
	} while (NewPurchase == 1);
	
		printf("Ƽ�� �߱��� �����մϴ�.");

	return 0;
}
