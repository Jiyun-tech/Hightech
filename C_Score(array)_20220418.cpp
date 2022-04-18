// practice using two dimentional array.
// input scores of each student/subject and print total & average scores.

#include <stdio.h>

int main() {
	
	int subjectCount;
	int studentCount;
	
	// �Է� 
	printf("���� �� �Է� : ");
	scanf("%d", &subjectCount);
	printf("�л� �� �Է� : ");
	scanf("%d", &studentCount); 
	
	int score[studentCount][subjectCount] = {0};
	int studentSum[studentCount] = {0};
	float studentAvg[studentCount] = {0};
	int subjectSum[subjectCount] = {0};
	float subjectAvg[subjectCount] = {0};
	
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d �л��� ���� %2d���� ���ʴ�� �Է��ϼ���. : \n"
				, indexStd+1, subjectCount);
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
		studentAvg[indexStd] = studentSum[indexStd] / (float)subjectCount;
	}
	
	for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
		subjectAvg[indexSub] = subjectSum[indexSub] / (float)studentCount;
	}
	
	// �μ� 
	for (int indexStd = 0; indexStd < studentCount; indexStd++) {
		printf("Koposw%2d �л��� ���� ������ %d��, ����� %0.2f���Դϴ�.\n"
				, indexStd+1, studentSum[indexStd], studentAvg[indexStd]);
	}
	
	for (int indexSub = 0; indexSub < subjectCount; indexSub++) {
		printf("����%2d ��ü ��� : %0.2f��\n", indexSub+1, subjectAvg[indexSub]);
	}
	
	return 0;
}
