#include <stdio.h>

int main() {
	
	// ���� ���� 
	int score[20][10];			// [�л� ��][���� ��] 
	int inputStudent;			// �л� �� �Է� 
	int inputSubject;			// ���� �� �Է�
	int StudentSum[20];			// �л��� �� ����
	float StudentAverage[20]; 	// �л��� ��� ����
	int SubjectSum[10];			// ���� �� ����
	float SubjectAveage[10];	// ���� ������� 
	
	// �Է� 
	printf("���� �� �Է� : ");
	scanf("%d", &inputSubject);
	printf("�л� �� �Է� : ");
	scanf("%d", &inputStudent);
	
	// ���� �Է� & �迭�� ���� ����
	for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
		printf("Koposw%02d �л��� ���� %d���� ���ʴ�� �Է��ϼ���. : \n", (indexStudent+1), inputSubject);
		for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
			scanf("%d", &score[indexStudent][indexSubject]);
		}
	}
	
	//���
	for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
		for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
			StudentSum[indexStudent] += score[indexStudent][indexSubject];
		}
		StudentAverage[indexStudent] =  StudentSum[indexStudent] / (float)inputSubject;
	}
	
	for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
		for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
			SubjectSum[indexSubject] += score[indexStudent][indexSubject];
		}
		SubjectAveage[indexSubject] = SubjectSum[indexSubject] / (float)inputStudent;
	}
	
	// ���
	for (int indexStudent = 0; indexStudent < inputStudent; indexStudent++) {
		printf("Koposw%2d �л��� ���� ������ %d��, ����� %f���Դϴ�.\n", (indexStudent+1), StudentSum[indexStudent], StudentAverage[indexStudent]);
	}
	printf("\n");
	for (int indexSubject = 0; indexSubject < inputSubject; indexSubject++) {
		printf("����%2d ��ü ��� : %f��\n", (indexSubject+1), SubjectAveage[indexSubject]);
	}
	
	
	return 0;
}
