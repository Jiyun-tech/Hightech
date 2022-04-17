#include <stdio.h>

int main() {
	
	int subjectCount;
	int studentCount;
	int score[20][10];
	int studentSum[20];
	float studentAvg[20];
	int subjectSum[10];
	float subjectAvg[10];
	
	// �Է� 
	printf("���� �� �Է� : ");
	scanf("%d", &subjectCount);
	printf("�л� �� �Է� : ");
	scanf("%d", &studentCount); 
	
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d �л��� ���� %2d���� ���ʴ�� �Է��ϼ���. : \n", indexStd+1, subjectCount);
		for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
			scanf("%d", &score[indexStd][indexSub]);
		}
	}
	
	// ��� 
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
			studentSum[indexStd] += score[indexStd][indexSub];
			subjectSum[indexSub] += score[indexStd][indexSub];
		}
		studentAvg[indexStd] = studentSum[indexStd]/(float)subjectCount;
	}
	
	for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
		subjectAvg[indexSub] = subjectSum[indexSub]/(float)studentCount;
	}
	
	// �μ� 
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d �л��� ���� ������ %d��, ����� %0.2f���Դϴ�.\n", indexStd+1, studentSum[indexStd], studentAvg[indexStd]);
	}
	
	for (int indexSub = 0; indexSub < studentCount; indexSub++) {
		printf("����%2d ��ü ��� : %0.2f��\n", indexSub+1, subjectAvg[indexSub]);
	}
	
	return 0;
}
