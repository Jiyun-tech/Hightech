// 2022.04.14 -> 2022.04.15 ���� 
// Ticket Box program for a theme park.
// Ư�̻��� 
// (1) 12���� �̸� : ����
// (2) 12���� �̻�~36���� �̸� : ����� ����. ���ƿ� ���̱ⱸ ž�� ���� ���, ���ƿ� �����̿�� ���� ����.
// (3) ��� ��� & ��� ��� : �ߺ� ���� �Ұ�. ���� ���ϴ� ��� �׸� ���� �ʿ�. 
// => ��� ���� 

#include <stdio.h>
#include <time.h> 

int main () {
	
	// ���� ��¥ �ҷ����� 
	time_t t = time(NULL);
	struct tm today = *localtime(&t);
	int todayYear = today.tm_year + 1900;
	int todayMonth = today.tm_mon + 1;
	int todayDay = today.tm_mday;
	
	// ��� ���/���� 
	int orderList[10][9] = {0}; // �迭�� ���� ��, 0 �ϳ��� �Է��ص� �迭 ��� �ڸ��� 0�� �� ������ �ʱ�ȭ ����. 
	const int SAVE_TICKET_TYPE = 0, SAVE_AGE_TYPE = 1, SAVE_TICKET_PRICE = 2, SAVE_TICKET_NUBMER = 3, SAVE_TOTAL_PRICE = 4, SAVE_BENEFIT_TYPE = 5, SAVE_BENEFIT_NUMBER = 6, SAVE_FINAL_PRICE = 7, SAVE_FINAL_PAYMENT = 8;
	const int TICKET_TYPE_NAME_ALL_1DAY = 1, TICKET_TYPE_NAME_ALL_AFTER4 = 2, TICKET_TYPE_NAME_PARK_1DAY = 3, TICKET_TYPE_NAME_PARK_AFTER4 = 4;
	int ticketTypeName;
	const int AGE_TYPE_NAME_ELDER = 1, AGE_TYPE_NAME_ADULT = 2, AGE_TYPE_NAME_JUVENILE = 3, AGE_TYPE_NAME_CHILD = 4, AGE_TYPE_NAME_BABY = 5, AGE_TYPE_NAME_NEWBORN = 6;
	int ageTypeName;
	const int BENEFIT_TYPE_NAME_NON = 1, BENEFIT_TYPE_NAME_DISABLED = 2, BENEFIT_TYPE_NAME_NATIONAL_MERIT = 3, BENEFIT_TYPE_NAME_PREGNANT = 4, BENEFIT_TYPE_NAME_ARMY = 5, BENEFIT_TYPE_NAME_MULTICHILD = 6;
	int benefitType;
	int finalPayment; 
	
	// ��� ��� & ���� ����
	const int PRICE_ALL_1DAY_ADULT = 62000, PRICE_ALL_1DAY_JUVENILE = 54000, PRICE_ALL_1DAY_CHILD = 47000, PRICE_ALL_1DAY_BABY=15000, PRICE_ALL_1DAY_NEWBORN = 0;
	const int PRICE_ALL_AFTER4_ADULT = 50000, PRICE_ALL_AFTER4_JUVENILE = 43000, PRICE_ALL_AFTER4_CHILD = 36000, PRICE_ALL_AFTER4_BABY = 15000, PRICE_ALL_AFTER4_NEWBORN = 0;  
	const int PRICE_PARK_1DAY_ADULT = 59000, PRICE_PARK_1DAY_JUVENILE = 52000, PRICE_PARK_1DAY_CHILD = 46000, PRICE_PARK_1DAY_BABY = 15000, PRICE_PARK_1DAY_NEWBORN = 0;
	const int PRICE_PARK_AFTER4_ADULT = 47000, PRICE_PARK_AFTER4_JUVENILE = 41000, PRICE_PARK_AFTER4_CHILD = 35000, PRICE_PARK_AFTER4_BABY = 15000, PRICE_PARK_AFTER4_NEWBORN = 0;
	int priceArray[9]; // ���� ���ÿ� ���� ��� �Է��� �迭 
	
	// ���� ��� & ���� ���� 
	const int ELDER_AGE_MIN = 65;
	const int ADULT_AGE_MAX = 64, ADULT_AGE_MIN = 19;
	const int JUVENILE_AGE_MAX = 18, JUVERNILE_AGE_MIN = 13;
	const int CHILD_AGE_MAX = 12, CHILD_AGE_MIN = 3;
	const int BABY_AGE_MAX = 2, BABY_AGE_MIN = 1; 
	const int NEWBORN_AGE = 0;
	const int INDEX_MALE_OLD = 1, INDEX_FEMALE_OLD = 2, INDEX_MALE_NEW = 3, INDEX_FEMALE_NEW = 4; 
	const int BIRTH_YEAR_ADD_OLD = 1900, BIRTH_YEAR_ADD_NEW = 2000;
	
	// ������  ��� ����
	const float BENEFIT_NON = 0, BENEFIT_DISABLED = 0.5, BENEFIT_NATIONAL_MERIT = 0.5, BENEFIT_PREGNANT = 0.5, BENEFIT_ARMY = 0.49, BENEFIT_MULTICHILD = 0.3;
	const int  BENEFIT_NON_MAX = 0, BENEFIT_DISABLED_MAX = 2, BENEFIT_NATIONAL_MERIT_MAX = 2, BENEFIT_PREGNANT_MAX = 1, BENEFIT_ARMY_MAX = 2, BENEFIT_MULTICHILD_MAX = 1;
	
	// �Է� ���� ����
	int inputTicketSelect; 		// ���� ���� 
	int inputTicketSelect_Baby; // ���ƿ� ���� ���� 
	char inputSocialID[7]; 		// ����� �ֹε�Ϲ�ȣ 
	int inputNumberOfTicket;	// Ƽ�� ���� 
	int inputBenefit; 			// ������ 
	int inputBenefit_Elder;		// ��� ��� ����� �߰� ���� (��� ��� vs ��� ���)
	
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
		 
	
	do { // ~ while => Begin a new purchasing process after payment of a prior process. 
	
		// ���� ������ ���� ����
		int orderCount = 0;
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
			printf("Ƽ�� ������� �ֹε�Ϲ�ȣ �� 7�ڸ��� �Է��ϼ���.(�� 6�ڸ� & ���ڸ� ù ��° ����))\n\t => ");
			scanf("%s", &inputSocialID);
			} while ( ((inputSocialID[2]-48)*10 + (inputSocialID[3]-48)) > 12 
					|| ((inputSocialID[4]-48)*10 + (inputSocialID[5]-48)) <= 0 || ((inputSocialID[4]-48)*10 + (inputSocialID[5]-48)) > 31 
					|| inputSocialID[6]-48 <= 0 || inputSocialID[6]-48 > 4 );
			
			do {
				printf("�ֹ� ������ �Է��ϼ���. (�ִ� 10��)\n\t => ");
				scanf("%d", &inputNumberOfTicket);
			} while ( inputNumberOfTicket < 1 || inputNumberOfTicket > 10);
			
			do {
				printf("�������� �����ϼ���.\n\t1. ���� (���� ���� �ڵ� ó��)\n\t2. �����\n\t3. ����������\n\t4. �ӻ�� (�����̿�Ǹ� �ش�)\n\t5. �ް��庴 (�����̿�Ǹ� �ش�)\n\t6. �ٵ��� �ູī�� (�����̿�Ǹ� �ش�)\n\t => ");
				scanf("%d", &inputBenefit);
			} while (inputBenefit < 1 || inputBenefit > 6);
			// 1~6 �� ���� �� ����â �����. 
			
			// ���� ���ÿ� ���� Price List  
			switch (inputTicketSelect) {
				case 1:
					priceArray[0] = PRICE_ALL_1DAY_CHILD;
					priceArray[1] = PRICE_ALL_1DAY_ADULT;
					priceArray[2] = PRICE_ALL_1DAY_JUVENILE;
					priceArray[3] = PRICE_ALL_1DAY_CHILD;
					priceArray[4] = PRICE_ALL_1DAY_BABY;
					priceArray[5] = PRICE_ALL_1DAY_NEWBORN;
					ticketTypeName = TICKET_TYPE_NAME_ALL_1DAY;
					break;
				case 2:
					priceArray[1] = PRICE_ALL_AFTER4_CHILD;
					priceArray[2] = PRICE_ALL_AFTER4_ADULT;
					priceArray[2] = PRICE_ALL_AFTER4_JUVENILE;
					priceArray[3] = PRICE_ALL_AFTER4_CHILD;
					priceArray[4] = PRICE_ALL_AFTER4_BABY;
					priceArray[5] = PRICE_ALL_AFTER4_NEWBORN;
					ticketTypeName = TICKET_TYPE_NAME_ALL_AFTER4;
					break;
				case 3:
					priceArray[0] = PRICE_PARK_1DAY_CHILD;
					priceArray[1] = PRICE_PARK_1DAY_ADULT;
					priceArray[2] = PRICE_PARK_1DAY_JUVENILE;
					priceArray[3] = PRICE_PARK_1DAY_CHILD;
					priceArray[4] = PRICE_PARK_1DAY_BABY;
					priceArray[5] = PRICE_PARK_1DAY_NEWBORN;
					ticketTypeName = TICKET_TYPE_NAME_PARK_1DAY;
					break;
				case 4:
					priceArray[0] = PRICE_PARK_AFTER4_CHILD;
					priceArray[1] = PRICE_PARK_AFTER4_ADULT;
					priceArray[2] = PRICE_PARK_AFTER4_JUVENILE;
					priceArray[3] = PRICE_PARK_AFTER4_CHILD;
					priceArray[4] = PRICE_PARK_AFTER4_BABY;
					priceArray[5] = PRICE_PARK_AFTER4_NEWBORN;
					ticketTypeName = TICKET_TYPE_NAME_PARK_AFTER4;
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
			
			if (userAge >= ELDER_AGE_MIN && inputBenefit != 1) {											// ���� - ��� (��� ��� ����) 
				do {
					printf("��� ���� �� ������ ���� �����մϴ�.\n��� ���� ��� ��� �� ������ ���Ͻô� �׸��� �����ϼ���.\n\t1. ��� ���\n\t2. ��� ���(�����, ����������, �ӻ��, �ް��庴,�ٵ��� �ູī��)\n\t=> ");
					scanf("%d", &inputBenefit_Elder);
				} while (inputBenefit_Elder < 1 || inputBenefit_Elder > 2);
				
				if (inputBenefit_Elder == 1) {
					ticketPrice = priceArray[0];
					ageTypeName = AGE_TYPE_NAME_ELDER;
				} else if (inputBenefit_Elder == 2) {
					ticketPrice = priceArray[1];
					ageTypeName = AGE_TYPE_NAME_ADULT;
				} 	
			} if (userAge >= ELDER_AGE_MIN && inputBenefit == 1) {
				ticketPrice = priceArray[0];
				ageTypeName = AGE_TYPE_NAME_ELDER;									// ���� - ��� (0) 
			} if (userAge >= ADULT_AGE_MIN  && userAge <= ADULT_AGE_MAX) { 			// ���� (1)
				ticketPrice = priceArray[1];
				ageTypeName = AGE_TYPE_NAME_ADULT;	
			} if (userAge >= JUVERNILE_AGE_MIN && userAge <= JUVENILE_AGE_MAX) {	// û�ҳ� (2) 
				ticketPrice = priceArray[2];
				ageTypeName = AGE_TYPE_NAME_JUVENILE;	
			} if (userAge >= CHILD_AGE_MIN && userAge < CHILD_AGE_MAX) {			// ��� (3)
				ticketPrice = priceArray[3];
				ageTypeName = AGE_TYPE_NAME_CHILD;	
			} if ((userAge >= BABY_AGE_MIN && userAge <= BABY_AGE_MAX )) {			// ���̺� (4)
				do {
					printf("�� 12���� �̻� ~ 36���� �̸� ������ ��� ������ �����Դϴ�.\n���ƿ� ���̽ü� �����̿���� �����Ͻðڽ��ϱ�?\n\t1. ���ƿ� �����̿�� ����\n\t2. ����Ǹ� ����\n\t=> ");
					scanf("%d", &inputTicketSelect_Baby);
				} while (inputTicketSelect_Baby < 1 || inputTicketSelect_Baby > 2);
				if (inputTicketSelect_Baby == 1) {									// ���̺� �����̿�� -> 15,000��(4) 
					ticketPrice = priceArray[4];
					ageTypeName = AGE_TYPE_NAME_BABY;
				} else if (inputTicketSelect_Baby == 2)  {							// ���̺� ����� -> ���� (= newborn) (5) 
					ticketPrice = priceArray[5];
					ageTypeName = AGE_TYPE_NAME_NEWBORN;
				}	
			} if (userAge == NEWBORN_AGE) {											// ���� = 12���� �̸� (����) (5)
				ticketPrice = priceArray[5];
				ageTypeName = AGE_TYPE_NAME_NEWBORN;
			} 
			
			// Ticket price before applying discount benefit. 
			totalTicketPrice = ticketPrice * inputNumberOfTicket;
	
			// Check the benefit and discounted amount. 
			if (userAge >= ELDER_AGE_MIN && inputBenefit_Elder == 1) { 	// ��� ��� ���� ����� ��� -> ��� ��� �������� ����. 
				appliedBenefit = BENEFIT_NON;
				discountedTicketNumber = BENEFIT_NON_MAX;
				benefitType = BENEFIT_TYPE_NAME_NON;
			} else {
				if (inputTicketSelect == 1 || inputTicketSelect == 2) { 	// ��� ��� (1) ���� �̿�� ������ ��� 
						if (inputBenefit == 1) { 	// ������ ���� (0)
						appliedBenefit = BENEFIT_NON;
						discountedTicketNumber = BENEFIT_NON_MAX;
						benefitType = BENEFIT_TYPE_NAME_NON;
					}
					if (inputBenefit == 2) { 		// ����� (1)
						appliedBenefit = BENEFIT_DISABLED;
						if (inputNumberOfTicket <= BENEFIT_DISABLED_MAX) {
							discountedTicketNumber = inputNumberOfTicket;
						} else {
							discountedTicketNumber = BENEFIT_DISABLED_MAX;
						}
						benefitType = BENEFIT_TYPE_NAME_DISABLED;
					}
					if (inputBenefit == 3) { 		// ���������� (2) 
						appliedBenefit = BENEFIT_NATIONAL_MERIT;
						if (inputNumberOfTicket <= BENEFIT_NATIONAL_MERIT_MAX) {
							discountedTicketNumber = inputNumberOfTicket;
						} else {
							discountedTicketNumber = BENEFIT_NATIONAL_MERIT_MAX;
						}
						benefitType = BENEFIT_TYPE_NAME_NATIONAL_MERIT;
					} 
					if (inputBenefit == 4) { 		// �ӻ�� (3)
						appliedBenefit = BENEFIT_PREGNANT;
						if (inputNumberOfTicket <= BENEFIT_PREGNANT_MAX) {
							discountedTicketNumber = inputNumberOfTicket;
						} else {
							discountedTicketNumber = BENEFIT_PREGNANT_MAX;
						}
						benefitType = BENEFIT_TYPE_NAME_PREGNANT;
					}
					if (inputBenefit == 5) { 		// �ް��庴 (4)
						appliedBenefit = BENEFIT_ARMY;
						if (inputNumberOfTicket <= BENEFIT_ARMY_MAX) {
							discountedTicketNumber = inputNumberOfTicket;
						} else {
							discountedTicketNumber =  BENEFIT_ARMY_MAX;
						}
						benefitType = BENEFIT_TYPE_NAME_ARMY;
					}
					if (inputBenefit == 6) { 		//���ڳ� (5)
						appliedBenefit = BENEFIT_MULTICHILD;
						if (inputNumberOfTicket <= BENEFIT_MULTICHILD_MAX) {
							discountedTicketNumber = inputNumberOfTicket;
						} else {
							discountedTicketNumber = BENEFIT_MULTICHILD_MAX;
						}
						benefitType = BENEFIT_TYPE_NAME_MULTICHILD;
					}	
				} else if (inputTicketSelect == 3 || inputTicketSelect == 4) { // ��� ��� (2) ��ũ �̿�� ������ ��� 
					if (inputBenefit == 1 || inputBenefit == 4 || inputBenefit == 5 || inputBenefit == 6) { 
						appliedBenefit = BENEFIT_NON;
						discountedTicketNumber = BENEFIT_NON_MAX;
						benefitType = BENEFIT_TYPE_NAME_NON;
					}
					if (inputBenefit == 2) { 		// ����� (����� & ���������� ��� �� ������ ��ũ�̿�ǿ� ���� ����)
						appliedBenefit = BENEFIT_DISABLED;
						if (inputNumberOfTicket <= BENEFIT_DISABLED_MAX) {
							discountedTicketNumber = inputNumberOfTicket;
						} else {
							discountedTicketNumber = BENEFIT_DISABLED_MAX;
						}
						benefitType = BENEFIT_TYPE_NAME_DISABLED;
					}
					if (inputBenefit == 3) { 		// ���������� (���������� ��� �� ������ ��ũ�̿�ǿ� ���� ����) 
						appliedBenefit = BENEFIT_NATIONAL_MERIT;
						if (inputNumberOfTicket <= BENEFIT_NATIONAL_MERIT_MAX) {
							discountedTicketNumber = inputNumberOfTicket;
						} else {
							discountedTicketNumber = BENEFIT_NATIONAL_MERIT_MAX;
						}
						benefitType = BENEFIT_TYPE_NAME_NATIONAL_MERIT;
					} 
				}	
			}
			discountedTicketPrice = (ticketPrice * appliedBenefit * discountedTicketNumber);
			
			// final ticket price after the benefit is applied.
			finalTicketPrice = totalTicketPrice - discountedTicketPrice;
			// total ticket price & number(accumulation)
			accumulatedFinalPrice = accumulatedFinalPrice + finalTicketPrice;
			accumulatedTicketNumber = accumulatedTicketNumber + inputNumberOfTicket;

			// �μ��� �迭�� �� �ֱ� 
			orderList[orderCount][SAVE_TICKET_TYPE] = ticketTypeName; 				// 0.�̿�� ���� 
			orderList[orderCount][SAVE_AGE_TYPE] = ageTypeName; 					// 1.���̿� ���� ���� 
			orderList[orderCount][SAVE_TICKET_PRICE] = ticketPrice; 				// 2.Ƽ��  �ݾ� (1���) 
			orderList[orderCount][SAVE_TICKET_NUBMER] = inputNumberOfTicket; 		// 3.Ƽ�� ���� 
			orderList[orderCount][SAVE_TOTAL_PRICE] = totalTicketPrice; 			// 4.��Ż  �ݾ� (�ݾ� x ����) 
			orderList[orderCount][SAVE_BENEFIT_TYPE] = benefitType; 				// 5.������ 
			orderList[orderCount][SAVE_BENEFIT_NUMBER] = discountedTicketNumber; 	// 6.��� ���� ���� 
			orderList[orderCount][SAVE_FINAL_PRICE] = finalTicketPrice; 			// 7.���� �ݾ�
			orderList[orderCount][SAVE_FINAL_PAYMENT] = accumulatedFinalPrice; 		// 8.��Ż ���� �ݾ�
						
			// print the final receipt.
			printf("\n****************************************\n");
			printf("���� Ƽ�� ����: %d ��\n", inputNumberOfTicket);
			printf("���� ����: %d �� (%d �� x %d ��)\n", totalTicketPrice, ticketPrice, inputNumberOfTicket);
			printf("\t=> ���� ����: ");
			if (orderList[orderCount][SAVE_TICKET_TYPE] == 1) {
				printf("%s","�����̿�� - �ְ���(1DAY)\n");
			} else if (orderList[orderCount][SAVE_TICKET_TYPE] == 2) {
				printf("%s","�����̿�� - �߰���(AFTER4)\n");
			} else if (orderList[orderCount][SAVE_TICKET_TYPE] == 3) {
				printf("%s","��ũ�̿�� - �ְ���(1DAY)\n");
			}
			else if (orderList[orderCount][SAVE_TICKET_TYPE] == 4) {
				printf("%s","��ũ�̿�� - �߰���(AFTER4)\n");
			}
			printf("��� ����: %d �� (%d �� x %d ��)\n", discountedTicketPrice, (int)(ticketPrice * appliedBenefit), discountedTicketNumber);
			printf("\t=> ���� ���: ");
			if (orderList[orderCount][SAVE_BENEFIT_TYPE] == 1) {
				printf("%s","*������ ����\n");
			} else if (orderList[orderCount][SAVE_BENEFIT_TYPE] == 2) {
				printf("%s","*����� ���\n");
			} else if (orderList[orderCount][SAVE_BENEFIT_TYPE] == 3) {
				printf("%s","*���������� ���\n");
			} else if (orderList[orderCount][SAVE_BENEFIT_TYPE] == 4) {
				printf("%s","*�ӻ�� ���\n");
			} else if (orderList[orderCount][SAVE_BENEFIT_TYPE] == 5) {
				printf("%s","*�ް��庴 ���\n");
			}
			else if (orderList[orderCount][SAVE_BENEFIT_TYPE] == 6) {
				printf("%s","*�ٵ��� �ູī�� ���");
			}
			printf("��� ���� ���� �� Ƽ�� ���� : �� %d ��\n", finalTicketPrice); 
			printf("\n������ ���� �ݾ�:  �� %d ��\n", accumulatedFinalPrice);
			printf("****************************************\n\n");
			
			orderCount++;	
			
			printf("Ƽ���� �߰� �����Ͻðڽ��ϱ�?\n\t1. �߰� ����\n\t2. ���� �� ����\n\t ==> ");
			scanf("%d", &continueTicketPurchase);
			
		} while (continueTicketPurchase == 1); 
		
		printf("****************************************\n");
		printf("%30s%20s%10s%10s%10s%25s%10s%10s\n","Ƽ�� ����","����","Ƽ�� ����","Ƽ�� ����","�� ����","��� ����","��� ����","���� ����");
		// �̿�� ���� ���
		for (int index = 0; index < orderCount; index++) {
			
			if (orderList[index][SAVE_TICKET_TYPE] == 1) {
				printf("%30s","�����̿�� - �ְ���(1DAY)");
			} else if (orderList[index][SAVE_TICKET_TYPE] == 2) {
				printf("%30s","�����̿�� - �߰���(AFTER4)");
			} else if (orderList[index][SAVE_TICKET_TYPE] == 3) {
				printf("%30s","��ũ�̿�� - �ְ���(1DAY)");
			}
			else if (orderList[index][SAVE_TICKET_TYPE] == 4) {
				printf("%30s","��ũ�̿�� - �߰���(AFTER4)");
			}

			if (orderList[index][SAVE_AGE_TYPE] == 1) {
				printf("%20s","����(��ο��)");
			} else if (orderList[index][SAVE_AGE_TYPE] == 2) {
				printf("%20s","����");
			} else if (orderList[index][SAVE_AGE_TYPE] == 3) {
				printf("%20s","û�ҳ�");
			} else if (orderList[index][SAVE_AGE_TYPE] == 4) {
				printf("%20s","���");
			} else if (orderList[index][SAVE_AGE_TYPE] == 5) {
				printf("%20s","���̺�(�����̿��)");
			}
			else if (orderList[index][SAVE_AGE_TYPE] == 6) {
				printf("%20s","���̺�(�����)");
			}
			// Ƽ�� ���� ��� 
			printf("%10d",orderList[index][SAVE_TICKET_PRICE]);
			// Ƽ�� ���� ��� 
			printf("%10d",orderList[index][SAVE_TICKET_NUBMER]);
			// Ƽ�� ��Ż ���� (���� x ����) ��� 
			printf("%10d",orderList[index][SAVE_TOTAL_PRICE]);
			//������ ��� 
			if (orderList[index][SAVE_BENEFIT_TYPE] == 1) {
				printf("%25s","*������ ����");
			} else if (orderList[index][SAVE_BENEFIT_TYPE] == 2) {
				printf("%25s","*����� ���");
			} else if (orderList[index][SAVE_BENEFIT_TYPE] == 3) {
				printf("%25s","*���������� ���");
			} else if (orderList[index][SAVE_BENEFIT_TYPE] == 4) {
				printf("%25s","*�ӻ�� ���");
			} else if (orderList[index][SAVE_BENEFIT_TYPE] == 5) {
				printf("%25s","*�ް��庴 ���");
			}
			else if (orderList[index][SAVE_BENEFIT_TYPE] == 6) {
				printf("%25s","*�ٵ��� �ູī�� ���");
			}
			//��� ���� ���� ���� ��� 
			printf("%10d",orderList[index][SAVE_BENEFIT_NUMBER]);	
			//���� ���� ��� 
			printf("%10d\n",orderList[index][SAVE_FINAL_PRICE]);	
		}
		
		// Total ���� ��� 
		printf("****************************************\n");
		printf("�� ���� �ݾ� : %d ��\n", orderList[orderCount-1][SAVE_FINAL_PAYMENT]);
		printf("****************************************\n");
		printf("========================================\n");
			
			printf("��� ���� ���θ� �����Ͻʽÿ�.\n\t1. �ű� �߱� ����\n\t2. �߱� ����\n\t ==> ");
			scanf("%d", &NewPurchase);
		
	} while (NewPurchase == 1);
	
		printf("Ƽ�� �߱��� �����մϴ�.");

	return 0;
}
